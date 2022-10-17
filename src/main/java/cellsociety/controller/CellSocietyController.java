package cellsociety.controller;

import static cellsociety.Main.DATA_FILE_FOLDER;

import cellsociety.SimType;
import cellsociety.model.Cell;
import cellsociety.model.InitialModelImplementation;
import cellsociety.model.Model;
import cellsociety.view.SceneCreator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.text.View;
import org.apache.commons.collections.map.HashedMap;

public class CellSocietyController {
  private static final String INITIAL_STATES = "InitialStates";
  public static final String TITLE = "Title";
  private Map<String, String> simMap;
  private Model myModel;
  private View myView;
  private File simFile;
  private Map<Integer, Cell> backEndCellsbyID;
  private Stage stage;
  private SceneCreator sceneCreator;
  private List<List<String>> currentGrid;
  //TODO create analogous mapping for the frontend
  //private Map<Integer, Cell> backEndCellsbyID;


  public CellSocietyController() throws IOException, CsvValidationException {
    getSimData();
    sceneCreator = new SceneCreator(600.0);
    String csvPath = simMap.get(INITIAL_STATES);
//    currentGrid =
//            loadInitialStates(new FileReader(DATA_FOLDER + csvPath));
    //TODO handle model type selection more elegantly, hardcoded for now
    myModel = new InitialModelImplementation(csvPath, simMap);
  }

  public void loadSimFile(File simFile) throws FileNotFoundException {
    this.simFile = simFile;
    getSimData();
  }

  public void getSimData() throws FileNotFoundException {
    SimParser simParser = new SimParser();
    simMap = simParser.parseData(simFile);
  }
  public void loadSimulation(Stage stage) {
    stage.setTitle(simMap.get(TITLE));
//    stage.setScene(sceneCreator.createScene(stage, gridView.setUpView(currentGrid)));
    stage.show();
  }

  public void updateGrid(){
    //TODO: updateGrid method in controller
  }
}
