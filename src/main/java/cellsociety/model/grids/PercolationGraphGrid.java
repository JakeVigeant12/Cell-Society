package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;


public class PercolationGraphGrid extends GraphGrid {

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public PercolationGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }

  /**
   * Computes and updates the states of the cells
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
