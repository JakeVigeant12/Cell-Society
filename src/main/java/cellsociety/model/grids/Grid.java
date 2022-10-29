package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javax.management.ReflectionException;

//abstraction of grid to allow flexibility in implementation
public abstract class Grid {
  public void computeStates() {
    return;
  }
  public abstract Map<Integer, Cell> createCells(GridWrapper inputLayout)
      throws ReflectionException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException;
  public abstract Map<Cell, List<Cell>> initializeNeighbors(GridWrapper gridParsing, Map<Integer, Cell> myCells, Neighborhood simulationNeighbors);
  public void setCellCurrentState (int key, int state){}
  public Map<Integer, Cell> getCells(){
    return Map.of(null,null);
  }
}

