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

  public CSVParser(){
  }
  @Override
  public List<List<String>> parseData(String filePath) throws IOException, CsvValidationException {
    CSVReader csvReader = new CSVReader(new FileReader(DATA_FOLDER+filePath));
    csvReader.readNext();
    grid = new ArrayList<>();
    String[] states = csvReader.readNext();
    while (states != null) {
      grid.add(Arrays.asList(states));
      states = csvReader.readNext();
    }
    return grid;
  }

  public String[] parseFirstLine(String filePath) throws CsvValidationException, IOException {
    CSVReader csvReader = new CSVReader(new FileReader(DATA_FOLDER+filePath));
    return csvReader.readNext();
  }

  public void saveCurrentGrid(GridWrapper grid, File file) throws IOException {
    CSVWriter csvWriter = new CSVWriter(new FileWriter(file), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
    csvWriter.writeNext(new String[]{String.valueOf(grid.getRow(0).size()), String.valueOf(grid.getColumn(0).size())});
    for(int[] row : grid.toArray()) {
      String[] writeArray = new String[row.length];
      for(int i = 0; i < row.length; i++) {
        writeArray[i] = String.valueOf(row[i]);
      }
      csvWriter.writeNext(writeArray);
    }
    csvWriter.close();
  }
}
