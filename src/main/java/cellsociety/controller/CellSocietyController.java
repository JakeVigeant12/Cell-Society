package cellsociety.controller;

import cellsociety.model.InitialModelImplementation;
import cellsociety.parser.CSVParser;
import cellsociety.model.Cell;
import cellsociety.model.Model;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javafx.stage.Stage;
import javax.swing.text.View;

public class CellSocietyController {
  private static final String INITIAL_STATES = "InitialStates";
  public static final String TITLE = "Title";
  private final int numRows;
  private final int numCols;
  public Properties properties;
  private Model myModel;
  private View myView;
  private File simFile;
  private Map<Integer, Cell> backEndCellsbyID;

  public CellSocietyController(File simFile) throws IOException, CsvValidationException {
    this.simFile = simFile;
    getSimData();
    String csvPath = (String) properties.get(INITIAL_STATES);
    //TODO handle model type selection more elegantly, hardcoded for now
    myModel = new InitialModelImplementation(csvPath, properties);
    backEndCellsbyID = myModel.getCells();

    String[] parseRowCol = new CSVParser(csvPath).parseFirstLine();
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

  public List<List<Integer>> getViewGrid() {
    List<List<Integer>> stateGrid = new ArrayList<>();
    List<Integer> currentList = new ArrayList<>();
    stateGrid.add(currentList);
    for(Integer key : backEndCellsbyID.keySet()) {
      if(currentList.size() < numCols) {
        currentList.add(backEndCellsbyID.get(key).getCurrentState());
      } else {
        currentList = new ArrayList<>();
        stateGrid.add(currentList);
        currentList.add(backEndCellsbyID.get(key).getCurrentState());
      }
    }
    return stateGrid;
  }

  public void setBackEndCellsbyID(Map<Integer, Cell> backEndCellsbyID) {
    this.backEndCellsbyID = backEndCellsbyID;
  }

  public List<List<Integer>> updateGrid() {
    myModel.computeStates();
    return getViewGrid();
  }

  /**
   * Resets the cells to the original file inputed
   * @throws CsvValidationException
   * @throws IOException
   */
  public void resetController() throws CsvValidationException, IOException {
    String csvPath = (String) properties.get(INITIAL_STATES);
    myModel = new InitialModelImplementation(csvPath, properties);
    backEndCellsbyID = myModel.getCells();
  }
}
