package cellsociety.parser;

import cellsociety.parser.Parser;
import cellsociety.view.GridWrapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser extends Parser {

    private static final String DATA_FOLDER = "data/";
    private FileReader myFileReader;
    private List<List<String>> grid;

    @Override
    public GridWrapper parseData(String csvPath) throws IOException, CsvValidationException {
        myFileReader = new FileReader(DATA_FOLDER + csvPath);
        GridWrapper gridWrapper = new GridWrapper();
        //https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/
        CSVReader csvReader = new CSVReader(myFileReader);
        String[] nextRecord;
        //TODO: refactor this (read size of the grid)
        int row = -1;
        while ((nextRecord = csvReader.readNext()) != null) {
            if (row >= 0) {
                gridWrapper.addRow();
                for (String cell : nextRecord) {
                    gridWrapper.addValueToRow(row, Integer.parseInt(cell));
                }
            }
            row++;
        }
        return gridWrapper;
    }

    public String[] parseFirstLine(String csvPath) throws CsvValidationException, IOException {
        myFileReader = new FileReader(DATA_FOLDER + csvPath);
        CSVReader csvReader = new CSVReader(myFileReader);
        return csvReader.readNext();
    }
}
