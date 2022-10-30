package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;
import java.util.List;
import java.util.Properties;

public class GameOfLifeGraphGrid extends GraphGrid{
  private final static int DEAD = 0;
  private final static int ALIVE = 1;
  private final static int LOWERTHRESHOLD = 2;
  private final static int UPPERTHRESHOLD = 3;
  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public GameOfLifeGraphGrid(GridWrapper gridParsing,
      Properties properties) {
    super(gridParsing, properties);
  }
  @Override
  public void setFutureState(Cell target, List<Cell> neighbors) {
    int aliveNeighbors = 0;
    for (Cell neighbor : neighbors) { // Count each neighbor that is alive
      if (neighbor.getCurrentState() == ALIVE) {
        aliveNeighbors++;
      }
    }
    if (target.getCurrentState() == ALIVE) { // If current cell is alive
      if (aliveNeighbors < LOWERTHRESHOLD || aliveNeighbors > UPPERTHRESHOLD) { // If alive cell has less than 2 or more than 3 neighbors
        target.setFutureStateValue(DEAD); // Set current cell to dead
      }
      else {
        target.setFutureStateValue(ALIVE); // Set current cell to alive
      }
    }
    else if (target.getCurrentState() == DEAD) { // If current cell is dead
      if (aliveNeighbors == UPPERTHRESHOLD) { // If current cell has 3 alive neighbors
        target.setFutureStateValue(ALIVE); // Set current cell to alive
      }
      else {
        target.setFutureStateValue(DEAD); // Set current cell to dead
      }
    }
  }
}
