package cellsociety.parser;

import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//abstraction of parsing to allow multiple filetypes/parsing methods
public abstract class Parser {

  public abstract Object parseData(File input) throws IOException, CsvValidationException;
}