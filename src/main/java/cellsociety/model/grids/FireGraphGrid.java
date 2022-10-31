package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.GridWrapper;

import java.util.Properties;

public class FireGraphGrid extends GraphGrid {

  /**
   * Constructor for FireGraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public FireGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }

  /**
   * Loops through cells and tells them to update states
   */
  /**
   * Computes and updates the states of the cells
   *
   * @throws IllegalStateException
   */
  @Override
  public void computeStates() throws IllegalStateException {
    for (Cell currentCell : getMyAdjacencyList().getCells()) {
      currentCell.setFutureState(getMyAdjacencyList().getNeighbors(currentCell));
    }
    for (Cell currentCell : getMyAdjacencyList().getCells()) {
      currentCell.updateState();
    }
  }
}
