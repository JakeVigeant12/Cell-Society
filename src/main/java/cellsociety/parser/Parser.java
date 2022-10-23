package cellsociety.parser;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;

//abstraction of parsing to allow multiple filetypes/parsing methods
public abstract class Parser {

  public abstract Object parseData(String filePath) throws IOException, CsvValidationException;
}
