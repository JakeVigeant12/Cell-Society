package cellsociety.model;

import cellsociety.SimType;
import cellsociety.model.cells.Cell;
import cellsociety.parser.CSVParser;
import cellsociety.parser.Parser;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

//Default implementation of the model
public class InitialModelImplementation extends Model {

  private final Grid myGrid;
  private final Parser gridParser;
  private final SimType simType;

  public InitialModelImplementation(String csvPath, Properties simParameters)
      throws IOException, CsvValidationException {
    simType = SimType.valueOf((String) simParameters.get("Type"));
    gridParser = new CSVParser();
    myGrid = new GraphGrid((List<List<String>>) gridParser.parseData(csvPath), simType, simParameters);

  }

  public void computeStates() {
    myGrid.computeStates();
  }

  @Override
  public Map<Integer, Cell> getCells() {
    return myGrid.getCells();
  }
}
