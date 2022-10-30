package cellsociety.model.cells;

import java.awt.*;
import java.util.List;
import java.util.Properties;

public class GameOfLifeCell extends Cell {
  private final static int DEAD = 0;
  private final static int ALIVE = 1;
  private final static int LOWERTHRESHOLD = 2;
  private final static int UPPERTHRESHOLD = 3;
  // Key States
  // 0 = Dead
  // 1 = Alive

  /**
   * Constructor for GameOfLifeCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public GameOfLifeCell(int state, Point id) {
    super(state, id);
  }

  /**
   * Method that returns the next state of the cell
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) {
    int aliveNeighbors = 0;
    for (Cell neighbor : neighbors) { // Count each neighbor that is alive
      if (neighbor.getCurrentState() == ALIVE) {
        aliveNeighbors++;
      }
    }
    if (getCurrentState() == ALIVE) { // If current cell is alive
      if (aliveNeighbors < LOWERTHRESHOLD || aliveNeighbors > UPPERTHRESHOLD) { // If alive cell has less than 2 or more than 3 neighbors
        setFutureStateValue(DEAD); // Set current cell to dead
      }
      else {
        setFutureStateValue(ALIVE); // Set current cell to alive
      }
    }
    else if (getCurrentState() == DEAD) { // If current cell is dead
      if (aliveNeighbors == UPPERTHRESHOLD) { // If current cell has 3 alive neighbors
        setFutureStateValue(ALIVE); // Set current cell to alive
      }
      else {
        setFutureStateValue(DEAD); // Set current cell to dead
      }
    }
  }
}
