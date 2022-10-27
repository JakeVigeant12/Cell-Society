package cellsociety.controller;

import cellsociety.model.InitialModelImplementation;
import cellsociety.model.SimType;
import cellsociety.parser.CSVParser;
import cellsociety.model.cells.Cell;
import cellsociety.model.Model;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;

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
  private Map<Integer, Cell> backEndCellsByID;

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

  public void getSimData() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(simFile));
  }

  public void loadSimulation(Stage stage) {
    stage.setTitle((String) properties.get(TITLE));
    stage.show();
  }

  public Properties getProperties() {
    return properties;
  }

  public void updateOneCell(int y, int x, int state) {
    myModel.setCellCurrentState(numCols * y + x + 1, state);
  }

  public GridWrapper getViewGrid() {
    GridWrapper stateGrid = new GridWrapper(numRows, numCols);
    for (Integer key : backEndCellsByID.keySet()) {
      stateGrid.setState((key - 1) / numCols, (key - 1) % numCols, backEndCellsByID.get(key).getCurrentState());
    }
    return stateGrid;
  }

  //For test purpose
  public void setBackEndCellsByID(Map<Integer, Cell> backEndCellsByID) {
    this.backEndCellsByID = backEndCellsByID;
  }

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
    SimType simType = SimType.valueOf((String) properties.get("Type"));
    GridWrapper gridWrapper = myGridParser.parseData(csvPath);
    myModel = new InitialModelImplementation(gridWrapper, properties);
    backEndCellsByID = myModel.getCells();
  }

  public void saveGrid(File file) throws IOException {
    myGridParser.saveCurrentGrid(getViewGrid(), file);
  }
}
