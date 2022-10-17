package cellsociety.model;

import cellsociety.parser.Parser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser extends Parser {

  private static final String DATA_FOLDER = "data/";
  private FileReader myFileReader;
  private ArrayList<ArrayList<String>> grid;

  public CSVParser(String csvPath)
      throws FileNotFoundException, CsvValidationException, IOException {
    myFileReader = new FileReader(DATA_FOLDER + csvPath);
  }
  @Override
  public Object parseData(File input) throws IOException, CsvValidationException {
    CSVReader csvReader = new CSVReader(myFileReader);
    csvReader.readNext();
    grid = new ArrayList<>();
    String[] states = csvReader.readNext();
    while (states != null) {
      grid.add((ArrayList<String>) Arrays.asList(states));
      states = csvReader.readNext();
    }
    return grid;
  }
  }
