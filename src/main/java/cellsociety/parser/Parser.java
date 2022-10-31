package cellsociety.parser;

import cellsociety.model.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;

public abstract class Parser {

  /**
   * abstraction of parsing to allow multiple filetypes/parsing methods
   *
   * @param csvPath
   * @return GridWrapper
   * @throws IOException
   * @throws CsvValidationException
   */
  public abstract GridWrapper parseData(String csvPath) throws IllegalStateException;
}
