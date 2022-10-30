package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javax.management.ReflectionException;

//abstraction of grid to allow flexibility in implementation
public abstract class Grid {
  /**
   * Abstract method that computes the new states of the cells
   */
  public void computeStates() {
    return;
  }

  /**
   * Abstract method that creates the cells
   * @param inputLayout
   * @return
   * @throws ReflectionException
   * @throws ClassNotFoundException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public abstract Map<Point, Cell> createCells(GridWrapper inputLayout)
      throws ReflectionException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException;

  /**
   * Abstract method that sets the current cell state
   * @param key
   * @param state
   */
  public void setCellCurrentState (Point key, int state){}

  /**
   * Abstract method that returns the cells
   * @return adjacencyList
   */
  public Map<Point, Cell> getCells(){
    return Map.of(null,null);
  }
}

