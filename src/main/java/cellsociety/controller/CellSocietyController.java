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
  private final Map<String, String> simMap;
  private List<List<String>> currentGrid;
  private Model myModel;

  public CellSocietyController(File simFile) throws IOException, CsvValidationException {
    SimParser simParser = new SimParser();
    simMap = simParser.parseData(simFile);
    String csvPath = simMap.get(INITIAL_STATES);
    myModel = new InitialModelImplementation(csvPath);
    currentGrid = loadInitialStates(new FileReader(DATA_FOLDER + csvPath));
  }

  private List<List<String>> loadInitialStates(Reader fileReader) throws CsvValidationException, IOException {
    List<List<String>> grid = new ArrayList<>();
    CSVReader csvReader = new CSVReader(fileReader);
    csvReader.readNext();
    String[] states = csvReader.readNext();
    while(states != null) {
      grid.add(Arrays.asList(states));
      states = csvReader.readNext();
    }
    return grid;
  }

  public void loadSimulation(Stage stage) {
    stage.setTitle(simMap.get(TITLE));
    stage.setScene(new Scene(new Group()));
    stage.show();
  }
}
