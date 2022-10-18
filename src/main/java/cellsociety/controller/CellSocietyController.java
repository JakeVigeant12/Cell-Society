package cellsociety.controller;

import cellsociety.model.InitialModelImplementation;
import cellsociety.parser.CSVParser;
import cellsociety.model.Cell;
import cellsociety.model.Model;
import cellsociety.parser.SimParser;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.stage.Stage;
import javax.swing.text.View;

public class CellSocietyController {
  private static final String INITIAL_STATES = "InitialStates";
  public static final String TITLE = "Title";
  private final int numRows;
  private final int numCols;
  public Map<String, String> simMap;
  private Model myModel;
  private View myView;
  private File simFile;
  private Map<Integer, Cell> backEndCellsbyID;
  //TODO create analogous mapping for the frontend
  //private Map<Integer, Cell> backEndCellsbyID;


  public CellSocietyController(File simFile) throws IOException, CsvValidationException {
    this.simFile = simFile;
    getSimData();
    String csvPath = simMap.get(INITIAL_STATES);
    //TODO handle model type selection more elegantly, hardcoded for now
    myModel = new InitialModelImplementation(csvPath, simMap);
    backEndCellsbyID = myModel.getCells();

    String[] parseRowCol = new CSVParser(csvPath).parseFirstLine();
    numRows = Integer.parseInt(parseRowCol[0]);
    numCols = Integer.parseInt(parseRowCol[1]);
  }
  public void getSimData() throws FileNotFoundException {
    SimParser simParser = new SimParser();
    simMap = simParser.parseData(simFile);
  }
  public void loadSimulation(Stage stage) {
    stage.setTitle(simMap.get(TITLE));
//    stage.setScene(current.createScene(stage, gridView.setUpView(getViewGrid())));
    stage.show();
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

  public void setBackEndCellsbyID(
      Map<Integer, Cell> backEndCellsbyID) {
    this.backEndCellsbyID = backEndCellsbyID;
  }
}
