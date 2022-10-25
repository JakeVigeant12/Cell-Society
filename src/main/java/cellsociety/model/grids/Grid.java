package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.management.ReflectionException;

//abstraction of grid to allow flexibility in implementation
public abstract class Grid {
  public void computeStates() {
    return;
  }
  public abstract void createCells(GridWrapper inputLayout)
      throws ReflectionException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException;
  public abstract void initializeNeighbors(GridWrapper inputLayout);
  public void setCellCurrentState (int key, int state){}
  public Map<Integer, Cell> getCells() {
    return null;
  }
}

