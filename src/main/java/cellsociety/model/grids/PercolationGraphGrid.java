package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;
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

  @Override
  public void computeStates() {
    for (Cell currentCell : getMyAdjacencyList().getCells()) {
      currentCell.setFutureState(getMyAdjacencyList().getNeighbors(currentCell));
    }
    for (Cell currentCell : getMyAdjacencyList().getCells()) {
      currentCell.updateState();
    }
  }
}
