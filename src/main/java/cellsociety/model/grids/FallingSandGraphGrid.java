package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.cells.FallingSandCell;
import cellsociety.model.cells.SegregationCell;
import cellsociety.view.GridWrapper;

import java.util.ArrayList;
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
  @Override
  public Map<Integer, Cell> createCells(GridWrapper inputLayout) {
    return super.createCells(inputLayout);
  }

  @Override
  public void computeStates() {
    //Override method with Falling Sand
    // Pass 1: Calculate future cell states and find empty cells
    for (Cell currentCell : myAdjacenyList.keySet()){
      currentCell.setFutureState(myAdjacenyList.get(currentCell));
    }
    
    for (Cell currentCell : myAdjacenyList.keySet()){
      // Pass 2: If a current cell wants to swap, then swap it with the cell in the adjacency list
      FallingSandCell sandWaterCell = (FallingSandCell) currentCell;
      if (sandWaterCell.wantsToSwap()) {
        Cell newCell = findCellToSwap(sandWaterCell.getNeighborToSwap(), myAdjacenyList.keySet());
        currentCell.swapCellStates(newCell);
      }
    }

    for (Cell currentCell : myAdjacenyList.keySet()){
      // Pass 3: Update the state of the cell
      currentCell.updateState();
    }
  }
}
