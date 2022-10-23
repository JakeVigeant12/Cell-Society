package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.parser.CSVParser;
import cellsociety.parser.Parser;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

//Default implementation of the model
public class InitialModelImplementation extends Model {

  private final Grid myGrid;
  private final SimType simType;

  public InitialModelImplementation(GridWrapper gridWrapper, Properties simParameters) {
    simType = SimType.valueOf((String) simParameters.get("Type"));
    myGrid = new GraphGrid(gridWrapper, simType, simParameters);

  }

  public void computeStates() {
    myGrid.computeStates();
  }

  @Override
  public Map<Integer, Cell> getCells() {
    return myGrid.getCells();
  }
}
