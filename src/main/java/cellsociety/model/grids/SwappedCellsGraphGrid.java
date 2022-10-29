package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;

import java.util.*;

public class SwappedCellsGraphGrid extends GraphGrid{

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public SwappedCellsGraphGrid(GridWrapper gridParsing,
      Properties properties) {
    super(gridParsing, properties);
  }

  /**
   * Finds a cell to swap with the current cell
   * @param startingCell
   * @param locations
   * @param targetState
   * @return
   */
  public Cell findCellToSwap(Cell startingCell, List<Cell> locations, int targetState) {
    List<Cell> potentialLocations = new ArrayList();
    for (Cell potentialCell : locations) {
      if (potentialCell.getCurrentState() == targetState) {
        potentialLocations.add(potentialCell);
      }
      //No cells found that match the state we need to swap into
      else if (potentialLocations.isEmpty()){
        return startingCell;
      }
      else {
        return startingCell;
      }
    }
    //Of the cells with the correct ending state, select one at random
    Random rand = new Random();
    return potentialLocations.get(rand.nextInt(potentialLocations.size()));
  }

  /**
   * Finds a cell to swap with the current cell if a set of locations is given
   * @param cellToSwap
   * @param locations
   * @return
   */
  public Cell findCellToSwap(Cell cellToSwap, Set<Cell> locations) {
    for (Cell potentialCell : locations) {
      if (potentialCell.getId() == cellToSwap.getId()) {
        return potentialCell;
      }
    }
    return cellToSwap;
  }
}
