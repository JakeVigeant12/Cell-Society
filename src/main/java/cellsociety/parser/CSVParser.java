package cellsociety.parser;

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
  private List<List<String>> grid;

  public CSVParser(String csvPath)
      throws FileNotFoundException, CsvValidationException, IOException {
    myFileReader = new FileReader(DATA_FOLDER + csvPath);
  }
  @Override
  public List<List<String>> parseData(File input) throws IOException, CsvValidationException {
    CSVReader csvReader = new CSVReader(myFileReader);
    csvReader.readNext();
    grid = new ArrayList<>();
    String[] states = csvReader.readNext();
    while (states != null) {
      grid.add(Arrays.asList(states));
      states = csvReader.readNext();
    }
    return grid;
  }

  public String[] parseFirstLine() throws CsvValidationException, IOException {
    CSVReader csvReader = new CSVReader(myFileReader);
    return csvReader.readNext();
  }
}
