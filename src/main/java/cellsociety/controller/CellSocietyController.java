package cellsociety.controller;

import cellsociety.model.InitialModelImplementation;
import cellsociety.parser.CSVParser;
import cellsociety.model.cells.Cell;
import cellsociety.model.Model;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javafx.stage.Stage;
import javax.swing.text.View;

public class CellSocietyController {
  private static final String INITIAL_STATES = "InitialStates";
  public static final String TITLE = "Title";
  private final CSVParser myCSVParser;
  private final int numRows;
  private final int numCols;
  public Properties properties;
  private Model myModel;
  private View myView;
  private File simFile;
  private Map<Integer, Cell> backEndCellsByID;

  public CellSocietyController(File simFile) throws IOException, CsvValidationException {
    this.simFile = simFile;
    getSimData();
    String csvPath = (String) properties.get(INITIAL_STATES);
    //TODO handle model type selection more elegantly, hardcoded for now
    myModel = new InitialModelImplementation(csvPath, properties);
    backEndCellsByID = myModel.getCells();
    myCSVParser = new CSVParser();
    String[] parseRowCol = myCSVParser.parseFirstLine(csvPath);
    numCols = Integer.parseInt(parseRowCol[0]);
    numRows = Integer.parseInt(parseRowCol[1]);
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

  public GridWrapper getViewGrid() {
    GridWrapper stateGrid = new GridWrapper(numRows, numCols);
    for(Integer key : backEndCellsByID.keySet()) {
      stateGrid.set((key -1)/ numCols, (key - 1)  % numCols, backEndCellsByID.get(key).getCurrentState());
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
   * @throws CsvValidationException
   * @throws IOException
   */
  public void resetController() throws CsvValidationException, IOException {
    String csvPath = (String) properties.get(INITIAL_STATES);
    myModel = new InitialModelImplementation(csvPath, properties);
    backEndCellsByID = myModel.getCells();
  }

  public void saveGrid(File file) throws IOException {
    try {
      myCSVParser.saveCurrentGrid(getViewGrid(), file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
