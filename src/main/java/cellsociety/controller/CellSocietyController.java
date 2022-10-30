package cellsociety.controller;

import cellsociety.model.InitialModelImplementation;
import cellsociety.parser.CSVParser;
import cellsociety.model.cells.Cell;
import cellsociety.model.Model;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;

import java.awt.*;
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
   * @throws IOException
   * @throws CsvValidationException
   * @throws ClassNotFoundException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public CellSocietyController(File simFile)
      throws IOException, CsvValidationException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
   * @throws IOException
   */
  public void getSimData() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(simFile));
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
  public GridWrapper updateGrid() {
    myModel.computeStates();
    return getViewGrid();
  }

  /**
   * Resets the cells to the original file inputted
   *
   * @throws CsvValidationException
   * @throws IOException
   */
  public void resetController()
      throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String csvPath = (String) properties.get(INITIAL_STATES);
    GridWrapper gridWrapper = myGridParser.parseData(csvPath);
    myModel = new InitialModelImplementation(gridWrapper, properties);
    backEndCellsByID = myModel.getCells();
  }

  /**
   * Method that saves the grid to a file
   * @param file
   * @throws IOException
   */
  public void saveGrid(File file) throws IOException {
    myGridParser.saveCurrentGrid(getViewGrid(), file);
  }
}
