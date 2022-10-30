package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.cells.FireCell;
import cellsociety.view.GridWrapper;
import java.util.List;
import java.util.Properties;

public class FireGraphGrid extends GraphGrid{
  private static final int EMPTY = 0;
  private static final int TREE = 1;
  private static final int BURNING = 2;
  private static final int BURNING_TIME = 3;

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public FireGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }
  @Override
  public void computeStates() {

    for (Cell currentCell : getMyAdjacencyList().getCells()){
      setFutureState((FireCell) currentCell, getMyAdjacencyList().getNeighbors(currentCell));
    }
    for (Cell currentCell : getMyAdjacencyList().getCells()){
      currentCell.updateState();
    }
  }
  public void setFutureState(FireCell target, List<Cell> neighbors) {
    if (target.getCurrentState() == TREE && target.getNeighborStates(neighbors).contains(BURNING)) {
      double burnVal = Math.random();// If current cell is a tree and has a burning neighbor
      if ( burnVal < target.getMyProbCatch()) { // If random number is less than probability of catching fire
        target.setFutureStateValue(BURNING); // Set current cell to burning
      }
      else {
        target.setFutureStateValue(TREE); // Set current cell to tree
      }
    }
    else if (target.getCurrentState() == BURNING) { // If current cell is burning
      if (target.getTurns() == BURNING_TIME) { // If current cell has been burning for BURNING_TIME turns
        target.setFutureStateValue(EMPTY); // Set current cell to empty
      }
      else {
        target.updateTurns();
        target.setFutureStateValue(BURNING); // Keep current cell burning
      }
    }
    else {
      target.setFutureStateValue(target.getCurrentState()); // Keep current cell empty or tree
    }
  }

}
