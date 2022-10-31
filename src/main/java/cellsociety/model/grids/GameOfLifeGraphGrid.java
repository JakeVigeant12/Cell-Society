package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.GridWrapper;

import java.util.Properties;

public class GameOfLifeGraphGrid extends GraphGrid {

  /**
   * Constructor for GameOfLifeGraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public GameOfLifeGraphGrid(GridWrapper gridParsing, Properties properties) {
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
