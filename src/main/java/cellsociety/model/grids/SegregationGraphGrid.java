package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.cells.SegregationCell;
import cellsociety.view.GridWrapper;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class SegregationGraphGrid extends SwappedCellsGraphGrid{

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public SegregationGraphGrid(GridWrapper gridParsing,
      Properties properties) {
    super(gridParsing, properties);
  }
  @Override
  public Map<Integer, Cell> createCells(GridWrapper inputLayout) {
    return super.createCells(inputLayout);
  }

  @Override
  public void computeStates() {
    //Override method with segregation rules
    emptyCells = new ArrayList<>();

    // Pass 1: Calculate future cell states and find empty cells
    for (Cell currentCell : myAdjacenyList.keySet()){
      currentCell.setFutureState(myAdjacenyList.get(currentCell));
      if (currentCell.getCurrentState() == 0) { // creates a list of empty cells so that the game knows where a cell can move to
        emptyCells.add(currentCell);
      }
    }


    for (Cell currentCell : myAdjacenyList.keySet()){
      // Pass 2: If a current cell wants to move, then swap it with an empty cell in the list of empty cells
        SegregationCell segregationCell = (SegregationCell) currentCell;
        if (!emptyCells.isEmpty() && currentCell.getCurrentState() != 0 && segregationCell.getWantsToMove()) {
          Cell newCell = findCellToSwap(currentCell, emptyCells, 0);
          currentCell.swapCellStates(newCell);
          emptyCells.remove(newCell);
        }
    }

    for (Cell currentCell : myAdjacenyList.keySet()){
      currentCell.updateState();
    }
  }
}
