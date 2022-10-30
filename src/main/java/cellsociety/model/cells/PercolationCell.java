package cellsociety.model.cells;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class PercolationCell extends Cell {

  private final static int EMPTY = 0;
  private final static int PERCOLATED = 1;
  private final static int BLOCKED = 2;
  private List<Cell> myNeighbors;
  private Map<Integer, String> stateMap;
  // Key States
  // 0 = empty
  // 1 = Percolated
  // 2 = Blocked

  /**
   * Constructor for PercolationCell class
   *
   * @param state is the state of the cell
   * @param id    is the id of the cell
   */
  public PercolationCell(int state, Point id) {
    super(state, id);
    stateMap = Map.of(EMPTY, "EMPTY", PERCOLATED, "PERCOLATED", BLOCKED, "BLOCKED");
  }

  /**
   * Method that sets the next state of the cell
   *
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    myNeighbors = neighbors;
    try {
      this.getClass().getDeclaredMethod("set" + stateMap.get(getCurrentState())).invoke(this);
    } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
      throw e;
    }
  }

  private void setEMPTY() {
    for (Cell neighbor : myNeighbors) {
      if (neighbor.getCurrentState() == PERCOLATED) {
        setFutureStateValue(PERCOLATED);
      }
    }
  }

  private void setPERCOLATED() {
    setFutureStateValue(PERCOLATED);
  }

  private void setBLOCKED() {
    setFutureStateValue(BLOCKED);
  }

}
