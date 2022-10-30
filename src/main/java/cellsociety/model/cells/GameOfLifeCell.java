package cellsociety.model.cells;

import java.awt.Point;;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GameOfLifeCell extends Cell {

  private final static int DEAD = 0;
  private final static int ALIVE = 1;
  private final static int LOWERTHRESHOLD = 2;
  private final static int UPPERTHRESHOLD = 3;
  private int aliveNeighbors;
  private Map<Integer, String> stateMap;
  // Key States
  // 0 = Dead
  // 1 = Alive

  /**
   * Constructor for GameOfLifeCell class
   *
   * @param state is the state of the cell
   * @param id    is the id of the cell
   */
  public GameOfLifeCell(int state, Point id) {
    super(state, id);
    stateMap = Map.of(DEAD, "DEAD", ALIVE, "ALIVE");
  }

  /**
   * Method that returns the next state of the cell
   *
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) {
    aliveNeighbors = 0;
    for (Cell neighbor : neighbors) { // Count each neighbor that is alive
      if (neighbor.getCurrentState() == ALIVE) {
        aliveNeighbors++;
      }
    }
    // using reflection, call the method that corresponds to the current state
    try {
      this.getClass().getDeclaredMethod("set" + stateMap.get(getCurrentState())).invoke(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void setDEAD() {
    if (aliveNeighbors == UPPERTHRESHOLD) { // If current cell has 3 alive neighbors
      setFutureStateValue(ALIVE); // Set current cell to alive
    } else {
      setFutureStateValue(DEAD); // Set current cell to dead
    }
  }

  private void setALIVE() {
    if (aliveNeighbors < LOWERTHRESHOLD || aliveNeighbors
        > UPPERTHRESHOLD) { // If alive cell has less than 2 or more than 3 neighbors
      setFutureStateValue(DEAD); // Set current cell to dead
    } else {
      setFutureStateValue(ALIVE); // Set current cell to alive
    }
  }
}
