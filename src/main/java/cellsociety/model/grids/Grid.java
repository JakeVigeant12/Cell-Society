package cellsociety.model.grids;

import cellsociety.model.cells.Cell;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Properties;
import javax.management.ReflectionException;

//abstraction of grid to allow flexibility in implementation
public abstract class Grid {

  /**
   * Abstract method that computes the new states of the cells
   */
  public void computeStates() throws IllegalStateException {
    return;
  }

  /**
   * Abstract method that sets the current cell state
   *
   * @param key
   * @param state
   */
  public void setCellCurrentState(Point key, int state) {
  }

  /**
   * Abstract method that returns the cells
   *
   * @return adjacencyList
   */
  public Map<Point, Cell> getCells() {
    return Map.of(null, null);
  }
}

