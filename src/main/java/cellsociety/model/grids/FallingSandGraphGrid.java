package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.cells.FallingSandCell;
import cellsociety.model.GridWrapper;

import java.util.Properties;

public class FallingSandGraphGrid extends SwappedCellsGraphGrid {

  /**
   * Constructor for FallingSandGraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public FallingSandGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }

  /**
   * Method that computes and sets next state of cells
   */
  @Override
  public void computeStates() throws IllegalStateException {
    //Override method with Falling Sand
    // Pass 1: Calculate future cell states and find empty cells
    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      currentCell.setFutureState(super.getMyAdjacencyList().getNeighbors(currentCell));
    }

    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      // Pass 2: If a current cell wants to swap, then swap it with the cell in the adjacency list
      FallingSandCell sandWaterCell = (FallingSandCell) currentCell;
      if (sandWaterCell.wantsToSwap()) {
        Cell newCell = findCellToSwap(sandWaterCell.getNeighborToSwap(),
            super.getMyAdjacencyList().getCells());
        currentCell.swapCellStates(newCell);
      }
    }

    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      // Pass 3: Update the state of the cell
      currentCell.updateState();
    }
  }
}
