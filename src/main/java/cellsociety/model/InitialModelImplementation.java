package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.grids.GraphGrid;
import cellsociety.model.grids.Grid;
import cellsociety.view.GridWrapper;

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
  public void setCellCurrentState (int key, int state){
    myGrid.setCellCurrentState(key, state);
  }

  @Override
  public Map<Integer, Cell> getCells() {
    return myGrid.getCells();
  }
}
