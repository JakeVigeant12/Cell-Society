package cellsociety.controller;

import static cellsociety.Main.DATA_FILE_FOLDER;

import cellsociety.model.InitialModelImplementation;
import cellsociety.model.Model;
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
import org.apache.commons.collections.map.HashedMap;

public class CellSocietyController {
  private static final String DATA_FOLDER = "data/";
  private static final String INITIAL_STATES = "InitialStates";
  public static final String TITLE = "Title";
  private Map<String, String> simMap;
  private Model myModel;
  private File simFile;

  public CellSocietyController(File simFile) throws IOException, CsvValidationException {
    this.simFile = simFile;
    getSimData();
    String csvPath = simMap.get(INITIAL_STATES);
    //Specification of model type
    myModel = new InitialModelImplementation(csvPath, simMap.get("Type"));

  }
  public void getSimData() throws FileNotFoundException {
    SimParser simParser = new SimParser();
    simMap = simParser.parseData(simFile);
  }
  public void loadSimulation(Stage stage) {
    stage.setTitle(simMap.get(TITLE));
    stage.setScene(new Scene(new Group()));
    stage.show();
  }
}
