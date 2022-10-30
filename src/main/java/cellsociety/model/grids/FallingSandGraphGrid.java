package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.cells.FallingSandCell;
import cellsociety.view.GridWrapper;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class FallingSandGraphGrid extends SwappedCellsGraphGrid{

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public FallingSandGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }

  /**
   * Method that creates the cells
   * @param inputLayout
   * @return
   */
  @Override
  public Map<Point, Cell> createCells(GridWrapper inputLayout) {
    return super.createCells(inputLayout);
  }

  /**
   * Method that computes and sets next state of cells
   */
  @Override
  public void computeStates() {
    //Override method with Falling Sand
    // Pass 1: Calculate future cell states and find empty cells
    for (Cell currentCell : myAdjacencyList.getCells()){
      currentCell.setFutureState(myAdjacencyList.getNeighbors(currentCell));
    }

    for (Cell currentCell : myAdjacencyList.getCells()){
      // Pass 2: If a current cell wants to swap, then swap it with the cell in the adjacency list
      FallingSandCell sandWaterCell = (FallingSandCell) currentCell;
      if (sandWaterCell.wantsToSwap()) {
        Cell newCell = findCellToSwap(sandWaterCell.getNeighborToSwap(), myAdjacencyList.getCells());
        currentCell.swapCellStates(newCell);
      }
    }

    for (Cell currentCell : myAdjacencyList.getCells()){
      // Pass 3: Update the state of the cell
      currentCell.updateState();
    }
  }
}
