package cellsociety.controller;

import static cellsociety.Main.DATA_FILE_FOLDER;

import cellsociety.SimType;
import cellsociety.model.CSVParser;
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
    private int numRows;
    private int numCols;
    public Map<String, String> simMap;
    private Model myModel;
    private View myView;
    private File simFile;
    private Map<Integer, Cell> backEndCellsbyID;
    private Stage stage;
    private List<List<String>> currentGrid;
    //TODO create analogous mapping for the frontend
    //private Map<Integer, Cell> backEndCellsbyID;


    public CellSocietyController() throws IOException, CsvValidationException {
        //TODO handle model type selection more elegantly, hardcoded for now
    }

    public void loadSimFile(File simFile) throws IOException, CsvValidationException {
        this.simFile = simFile;
        getSimData();
    }

    public void getSimData() throws IOException, CsvValidationException {
        SimParser simParser = new SimParser();
        simMap = simParser.parseData(simFile);
        String csvPath = simMap.get(INITIAL_STATES);
        String[] parseRowCol = new CSVParser(csvPath).parseFirstLine();
        numRows = Integer.parseInt(parseRowCol[0]);
        numCols = Integer.parseInt(parseRowCol[1]);
        myModel = new InitialModelImplementation(csvPath, simMap, simFile);
    }

    public void loadSimulation(Stage stage) {
        stage.setTitle(simMap.get(TITLE));
        stage.show();
    }

    public List<List<Integer>> getViewGrid() {
        List<List<Integer>> stateGrid = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        stateGrid.add(currentList);
        for (Integer key : backEndCellsbyID.keySet()) {
            if (currentList.size() < numCols) {
                currentList.add(backEndCellsbyID.get(key).getCurrentState());
            } else {
                currentList = new ArrayList<>();
                stateGrid.add(currentList);
                currentList.add(backEndCellsbyID.get(key).getCurrentState());
            }
        }
        return stateGrid;
    }
}
