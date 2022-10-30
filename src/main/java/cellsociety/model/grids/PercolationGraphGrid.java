package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;
import java.util.List;
import java.util.Properties;



public class PercolationGraphGrid extends GraphGrid{
  private final static int EMPTY = 0;
  private final static int PERCOLATED = 1;
  private final static int BLOCKED = 2;

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public PercolationGraphGrid(GridWrapper gridParsing,
      Properties properties) {
    super(gridParsing, properties);
  }

  @Override
  public void setFutureState(Cell target, List<Cell> neighbors) {
    if (target.getCurrentState() == EMPTY){
      for (Cell neighbor : neighbors) {
        if (neighbor.getCurrentState() == PERCOLATED){
          target.setFutureStateValue(PERCOLATED);
        }
      }
    }
    else if (target.getCurrentState() == PERCOLATED){
      target.setFutureStateValue(PERCOLATED);
    }
    else if (target.getCurrentState() == BLOCKED){
      target.setFutureStateValue(BLOCKED);
    }
  }
}
