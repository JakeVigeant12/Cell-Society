package cellsociety.model;

import cellsociety.parser.Parser;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//Default implementation of the model
public class InitialModelImplementation extends Model{
  GraphGrid myGrid;
  Parser gridParser;
  public InitialModelImplementation(String csvPath) throws IOException, CsvValidationException {
    gridParser = new CSVParser(csvPath);
    myGrid = new GraphGrid((ArrayList<ArrayList<String>>)gridParser.parseData(null));

  }
  public void computeStates(){
    myGrid.computeStates();
  }
  public Grid constructGrid(){
    return null;
  }

}
