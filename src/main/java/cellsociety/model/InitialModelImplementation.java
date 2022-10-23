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

  public InitialModelImplementation(GridWrapper gridWrapper, Properties simParameters) {
    myGrid = new GraphGrid(gridWrapper, simParameters);

  }

  public void computeStates() {
    myGrid.computeStates();
  }

  @Override
  public Map<Integer, Cell> getCells() {
    return myGrid.getCells();
  }
}
