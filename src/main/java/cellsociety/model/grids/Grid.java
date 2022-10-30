package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;

import java.lang.reflect.InvocationTargetException;
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

  /**
   * Abstract method that sets the current cell state
   * @param key
   * @param state
   */
  public void setCellCurrentState (int key, int state){}

  /**
   * Abstract method that returns the cells
   * @return adjacencyList
   */
  public Map<Integer, Cell> getCells(){
    return Map.of(null,null);
  }
}

