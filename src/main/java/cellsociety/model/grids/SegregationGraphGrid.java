package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.cells.SegregationCell;
import cellsociety.view.GridWrapper;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class SegregationGraphGrid extends SwappedCellsGraphGrid {

  /**
   * Constructor for SegregationGraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public SegregationGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }

  /**
   * Computes and updates the states of the cells
   * @throws IllegalStateException
   */
  @Override
  public void computeStates() throws IllegalStateException {
    //Override method with segregation rules
    super.setEmptyCells(new ArrayList<>());

    // Pass 1: Calculate future cell states and find empty cells
    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      currentCell.setFutureState(super.getMyAdjacencyList().getNeighbors(currentCell));
      if (currentCell.getCurrentState() == 0) { // creates a list of empty cells so that the game knows where a cell can move to
        super.getEmptyCells().add(currentCell);
      }
    }

    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      // Pass 2: If a current cell wants to move, then swap it with an empty cell in the list of empty cells
      SegregationCell segregationCell = (SegregationCell) currentCell;
      if (!super.getEmptyCells().isEmpty() && currentCell.getCurrentState() != 0 && segregationCell.getWantsToMove()) {
        Cell newCell = findCellToSwap(currentCell, super.getEmptyCells(), 0);
        currentCell.swapCellStates(newCell);
        super.getEmptyCells().remove(newCell);
      }
    }

    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      currentCell.updateState();
    }
  }
}
