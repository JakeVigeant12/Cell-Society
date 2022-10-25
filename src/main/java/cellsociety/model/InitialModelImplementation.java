package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.parser.CSVParser;
import cellsociety.parser.Parser;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.management.ReflectionException;

//Default implementation of the model
public class InitialModelImplementation extends Model {

  private final Grid myGrid;

  public InitialModelImplementation(GridWrapper gridWrapper, Properties simParameters)
      throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
    myGrid = new GraphGrid(gridWrapper, simParameters);

  }

  public void computeStates() {
    myGrid.computeStates();
  }

  @Override
  public void setCellCurrentState (int key, int state){
    myGrid.setCellCurrentState(key, state);
  }

  @Override
  public Map<Integer, Cell> getCells() {
    return myGrid.getCells();
  }
}
