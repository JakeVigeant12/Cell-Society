package cellsociety.controller;

import cellsociety.model.InitialModelImplementation;
import cellsociety.parser.CSVParser;
import cellsociety.model.cells.Cell;
import cellsociety.model.Model;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;

import java.awt.Point;;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
   * @param simFile
   */
  public CellSocietyController(File simFile) throws IllegalStateException {
    this.simFile = simFile;
    getSimData();
    String csvPath = (String) properties.get(INITIAL_STATES);
    myGridParser = new CSVParser();
    GridWrapper gridWrapper = myGridParser.parseData(csvPath);
    myModel = new InitialModelImplementation(gridWrapper, properties);
    backEndCellsByID = myModel.getCells();
    String[] parseRowCol = new CSVParser().parseFirstLine(csvPath);
    numCols = Integer.parseInt(parseRowCol[0].trim());
    numRows = Integer.parseInt(parseRowCol[1].trim());
  }

  /**
   * Method that gets the simulation data
   */
  public void getSimData() throws IllegalStateException{
    properties = new Properties();
    try {
      properties.load(new FileReader(simFile));
    }
    catch (IOException e) {
      throw new IllegalStateException("fileUploadError");
    }
  }

  /**
   * Method that loads the simulation based on the stage
   * @param stage
   */
  public void loadSimulation(Stage stage) {
    stage.setTitle((String) properties.get(TITLE));
    stage.show();
  }

  /**
   * Getter for properties
   * @return
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * Method that updates only one cell
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
   * @return gridWrapper
   */
  public GridWrapper updateGrid() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    try{
      myModel.computeStates();
      return getViewGrid();
    } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
      throw e;
    }
  }

  /**
   * Resets the cells to the original file inputted
   *
   */
  public void resetController() throws IllegalStateException {
    String csvPath = (String) properties.get(INITIAL_STATES);
    GridWrapper gridWrapper = myGridParser.parseData(csvPath);
    myModel = new InitialModelImplementation(gridWrapper, properties);
    backEndCellsByID = myModel.getCells();
  }

  /**
   * Method that saves the grid to a file
   * @param file
   */
  public void saveGrid(File file) throws IllegalStateException{
    myGridParser.saveCurrentGrid(getViewGrid(), file);
  }
}
