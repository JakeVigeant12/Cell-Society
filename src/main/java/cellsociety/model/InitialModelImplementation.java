package cellsociety.model;

import cellsociety.SimType;
import cellsociety.parser.CSVParser;
import cellsociety.parser.Parser;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Default implementation of the model
public class InitialModelImplementation extends Model{
  private final Grid myGrid;
  private final Parser gridParser;
  private final SimType simType;
  public InitialModelImplementation(String csvPath, Map<String,String> simParameters) throws IOException, CsvValidationException {
    simType = SimType.valueOf(simParameters.get("Type"));
    gridParser = new CSVParser(csvPath);
    myGrid = new GraphGrid((List<List<String>>) gridParser.parseData(null), simType);

  }
  public void computeStates(){
    myGrid.computeStates();
  }


}
