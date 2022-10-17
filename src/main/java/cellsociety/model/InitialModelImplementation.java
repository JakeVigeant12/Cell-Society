package cellsociety.model;

import cellsociety.SimType;
import cellsociety.parser.Parser;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//Default implementation of the model
public class InitialModelImplementation extends Model{
  private final GraphGrid myGrid;
  private final Parser gridParser;
  private final SimType simType;
  public InitialModelImplementation(String csvPath, SimType simInput) throws IOException, CsvValidationException {
    simType = simInput;
    gridParser = new CSVParser(csvPath);
    myGrid = new GraphGrid((ArrayList<ArrayList<String>>)gridParser.parseData(null), simType);

  }
  public void computeStates(){
    myGrid.computeStates();
  }


}
