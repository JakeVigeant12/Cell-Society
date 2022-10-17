package cellsociety.model;

import cellsociety.SimType;
import cellsociety.parser.Parser;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Default implementation of the model
public class InitialModelImplementation extends Model{
  private final GraphGrid myGrid;
  private final Parser gridParser;
  private final SimType simType;
  public InitialModelImplementation(String csvPath, Map<String,String> simParameters) throws IOException, CsvValidationException {
    simType = SimType.valueOf(simParameters.get("Type"));
    gridParser = new CSVParser(csvPath);
    myGrid = new GraphGrid((ArrayList<ArrayList<String>>)gridParser.parseData(null), simType);

  }
  public void computeStates(){
    myGrid.computeStates();
  }


}
