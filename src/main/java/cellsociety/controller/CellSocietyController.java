package cellsociety.controller;

import cellsociety.model.InitialModelImplementation;
import cellsociety.model.SimType;
import cellsociety.parser.CSVParser;
import cellsociety.model.cells.Cell;
import cellsociety.model.Model;
import cellsociety.parser.Parser;
import cellsociety.view.GridWrapper;
import cellsociety.view.GridWrapper;
import cellsociety.view.GridWrapperObservable;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javafx.beans.property.IntegerProperty;
import javafx.stage.Stage;

import javax.swing.text.View;

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

    public CellSocietyController(File simFile) throws IOException, CsvValidationException {
        this.simFile = simFile;
        getSimData();
        String csvPath = (String) properties.get(INITIAL_STATES);
        myGridParser = new CSVParser();
        GridWrapper gridWrapper = myGridParser.parseData(csvPath);
        myModel = new InitialModelImplementation(gridWrapper, properties);
        backEndCellsByID = myModel.getCells();

        String[] parseRowCol = new CSVParser().parseFirstLine(csvPath);

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

    public void updateOneCell(int y, int x, int state) {
        myModel.setCellCurrentState(numCols * y + x + 1, state);
    }

    public GridWrapper getViewGrid() {
        GridWrapper stateGrid = new GridWrapper(numRows, numCols);
        for (Integer key : backEndCellsByID.keySet()) {
            stateGrid.set((key - 1) / numCols, (key - 1) % numCols, backEndCellsByID.get(key).getCurrentState());
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
    public void resetController() throws CsvValidationException, IOException {
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
