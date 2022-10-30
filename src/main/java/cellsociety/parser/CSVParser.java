package cellsociety.parser;

import cellsociety.view.GridWrapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.List;

public class CSVParser extends Parser {

  private static final String DATA_FOLDER = "data/";
  private FileReader myFileReader;

  /**
   * Parses the data from the csv file
   *
   * @param csvPath
   * @return
   */
  @Override
  public GridWrapper parseData(String csvPath) throws IllegalStateException {
    try {
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
            gridWrapper.addValueToRow(row, Integer.parseInt(cell.trim()));
          }
        }
        row++;
      }
      return gridWrapper;
    } catch (CsvValidationException | IOException e) {
      throw new IllegalStateException("badCsvFile");
    }
  }

  /**
   * Parses the first line of the csv file (size of the grid)
   *
   * @param csvPath
   * @return
   * @throws CsvValidationException
   * @throws IOException
   */
  public String[] parseFirstLine(String csvPath) throws IllegalStateException {
    try {
      myFileReader = new FileReader(DATA_FOLDER + csvPath);
      CSVReader csvReader = new CSVReader(myFileReader);
      return csvReader.readNext();
    } catch (CsvValidationException | IOException e) {
      throw new IllegalStateException("badCsvFile");
    }
  }

  /**
   * Method that saves the current grid to a csv file
   *
   * @param grid
   * @param file
   * @throws IOException
   */
  public void saveCurrentGrid(GridWrapper grid, File file) throws IllegalStateException {
    CSVWriter csvWriter;
    try {
      csvWriter = new CSVWriter(new FileWriter(file), CSVWriter.DEFAULT_SEPARATOR,
          CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
          CSVWriter.DEFAULT_LINE_END);
      csvWriter.writeNext(
          new String[]{String.valueOf(grid.getRowSize(0)), String.valueOf(grid.getRowCount())});
      for (List<Integer> row : grid.getGrid()) {
        String[] writeArray = new String[row.size()];
        for (int i = 0; i < row.size(); i++) {
          writeArray[i] = String.valueOf(row.get(i));
        }
        csvWriter.writeNext(writeArray);
      }
      csvWriter.close();
    } catch (IOException e) {
      throw new IllegalStateException("saveSimulationError");
    }
  }
}
