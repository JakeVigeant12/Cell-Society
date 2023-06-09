package cellsociety.controller;

import static cellsociety.view.GridScreen.TYPE;
import static cellsociety.view.GridView.CELL_VIEW_RESOURCES;
import static cellsociety.view.GridView.REGEX;

import cellsociety.model.InitialModelImplementation;
import cellsociety.parser.CSVParser;
import cellsociety.model.cells.Cell;
import cellsociety.model.Model;
import cellsociety.model.GridWrapper;

import java.awt.Point;;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javafx.stage.Stage;

public class CellSocietyController {

  private static final String INITIAL_STATES = "InitialStates";
  public static final String TITLE = "Title";
  private final int numRows;
  private final int numCols;
  public Properties properties;
  private Model myModel;
  private final CSVParser myGridParser;
  private File simFile;
  private Map<Point, Cell> backEndCellsByID;

  /**
   * Constructor for CellSocietyController class
   *
   * @param simFile
   */
  public CellSocietyController(File simFile) throws IllegalStateException {
    this.simFile = simFile;
    getSimData();
    String csvPath = (String) properties.get(INITIAL_STATES);
    GridWrapper gridWrapper;
    myGridParser = new CSVParser();
    if (csvPath.equals("Random")) {
      gridWrapper = new GridWrapper(
          Integer.parseInt(CELL_VIEW_RESOURCES.getString((String) properties.get(TYPE))));
      numCols = gridWrapper.getRowSize(0);
      numRows = gridWrapper.getRowCount();
    } else if (csvPath.equals("Proportions")) {
      String[] initialProportions = ((String) properties.get("InitialProportions")).split(REGEX);
      gridWrapper = new GridWrapper(
          Integer.parseInt(CELL_VIEW_RESOURCES.getString((String) properties.get(TYPE))),
          initialProportions);
      numCols = gridWrapper.getRowSize(0);
      numRows = gridWrapper.getRowCount();
    } else {
      gridWrapper = myGridParser.parseData(csvPath);
      String[] parseRowCol = myGridParser.parseFirstLine(csvPath);

      numCols = Integer.parseInt(parseRowCol[0].trim());
      numRows = Integer.parseInt(parseRowCol[1].trim());
    }
    myModel = new InitialModelImplementation(gridWrapper, properties);
    backEndCellsByID = myModel.getCells();
  }

  /**
   * Method that gets the simulation data
   */
  public void getSimData() throws IllegalStateException {
    properties = new Properties();
    try {
      properties.load(new FileReader(simFile));
    } catch (IOException e) {
      throw new IllegalStateException("fileUploadError", e);
    }
  }

  /**
   * Method that loads the simulation based on the stage
   *
   * @param stage
   */
  public void loadSimulation(Stage stage) {
    stage.setTitle((String) properties.get(TITLE));
    stage.show();
  }

  /**
   * Getter for properties
   *
   * @return
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * Method that updates only one cell
   *
   * @param y
   * @param x
   * @param state
   */
  public void updateOneCell(int y, int x, int state) {
    myModel.setCellCurrentState(new Point(x, y), state);
  }

  /**
   * Method gets the view of the grid
   */
  public GridWrapper getViewGrid() {
    GridWrapper stateGrid = new GridWrapper(numRows, numCols);
    for (Point key : backEndCellsByID.keySet()) {
      stateGrid.setState(key.y, key.x, backEndCellsByID.get(key).getCurrentState());
    }
    return stateGrid;
  }

  //For test purpose
  public void setBackEndCellsByID(Map<Point, Cell> backEndCellsByID) {
    this.backEndCellsByID = backEndCellsByID;
  }

  /**
   * Method that updates the grid
   *
   * @return gridWrapper
   */
  public GridWrapper updateGrid() throws IllegalStateException {
    myModel.computeStates();
    return getViewGrid();
  }

  /**
   * Resets the cells to the original file inputted
   */
  public void resetController() throws IllegalStateException {
    String csvPath = (String) properties.get(INITIAL_STATES);
    GridWrapper gridWrapper = myGridParser.parseData(csvPath);
    myModel = new InitialModelImplementation(gridWrapper, properties);
    backEndCellsByID = myModel.getCells();
  }

  /**
   * Method that saves the grid to a file
   *
   * @param file
   */
  public void saveGrid(File file) throws IllegalStateException {
    myGridParser.saveCurrentGrid(getViewGrid(), file);
  }
}
