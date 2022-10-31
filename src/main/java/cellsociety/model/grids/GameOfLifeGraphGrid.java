package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class GameOfLifeGraphGrid extends GraphGrid {

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public GameOfLifeGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }
  /**
   * Loops through cells and tells them to update states
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
