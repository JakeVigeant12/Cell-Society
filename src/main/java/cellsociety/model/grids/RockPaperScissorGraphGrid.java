package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;
import java.util.List;
import java.util.Properties;

public class RockPaperScissorGraphGrid extends GraphGrid{
  private static final int ROCK = 0;
  private static final int PAPER = 1;
  private static final int SCISSOR = 2;
  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public RockPaperScissorGraphGrid(GridWrapper gridParsing,
      Properties properties) {
    super(gridParsing, properties);
  }
  public void setFutureState(Cell target, List<Cell> neighbors) {
    List<Integer> neighborStates = target.getNeighborStates(neighbors);
    if (target.getCurrentState() == ROCK){ // if cell is a rock
      if (neighborStates.contains(PAPER)){ // if neighbor is paper
        target.setFutureStateValue(PAPER); // cell becomes paper
      }
    }
    else {
      if (target.getCurrentState() == PAPER){ // if cell is paper
        if (neighborStates.contains(SCISSOR)){ // if neighbor is scissors
          target.setFutureStateValue(SCISSOR); // cell becomes scissors
        }
      }
      else if (target.getCurrentState() == SCISSOR){ // if cell is scissors
        if (neighborStates.contains(ROCK)){ // if neighbor is rock
          target.setFutureStateValue(ROCK); // cell becomes rock
        }
      }
    }
  }
}
