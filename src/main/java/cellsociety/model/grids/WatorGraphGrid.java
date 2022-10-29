package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WatorGraphGrid extends SwappedCellsGraphGrid {

  private HashMap<Integer, Cell> myCells;
  private HashMap<Cell, List<Cell>> myAdjacenyList;
  private List<Cell> emptyCells;
  private Properties myProperties;
  private final String cellPackagePath = "cellsociety.model.cells.";
  private Neighborhood simulationNeighbors;

  /**
   * Constructor for WatorGraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public WatorGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }

  /**
   * Method that creates the cells for the grid
   * @param inputLayout
   * @return
   */
  @Override
  public Map<Integer, Cell> createCells(GridWrapper inputLayout) {
    return super.createCells(inputLayout);
  }

  /**
   * Method that computes the new states for the cells
   */
  @Override
  public void computeStates() {
    emptyCells = new ArrayList<>();
    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      if (currentCell.getCurrentState() == 2) {
        Cell newShark = moveShark(currentCell);
        if (newShark.getId() != currentCell.getId()) {
          //If the shark moves, swap the states and then deal with reproduction or death
          newShark.swapCells(currentCell);
          currentCell.setCurrentState(3);
          newShark.setCurrentState(4);
          //if(currentCell.readyToReproduce()){
          //newShark.setCurrentState(2);
          //}
        }
      }
    }
    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      if (currentCell.getCurrentState() == 1) {
        Cell newFish = moveFish(currentCell);
        if (newFish.getId() != currentCell.getId()) {
          newFish.swapCells(currentCell);
          //if(currentCell.readyToReproduce()){
          //newFish.setCurrentState(1);
          //}
        }
      }
    }
    //Erase shark placeholders
    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      if (currentCell.getCurrentState() == 3) {
        currentCell.setCurrentState(0);
      }
      if (currentCell.getCurrentState() == 4) {
        currentCell.setCurrentState(2);
      }
    }
    for (Cell currentCell : super.getMyAdjacencyList().getCells()) {
      currentCell.updateState();
    }
  }

  /**
   * Method that moves the shark
   * @param sharkCell
   * @return the new cell the shark is in
   */
  public Cell moveShark(Cell sharkCell) {
    //See if any fish may be eaten first
    Cell newLocation = findCellToSwap(sharkCell, super.getMyAdjacencyList().getNeighbors(sharkCell),
        1);
    //If the shark can eat a fish, return the fish cell
    if (!(newLocation.getId() == sharkCell.getId())) {
      //Do not change the current sharkCell's state yet so no fish can move here on the fish iteration
      return newLocation;
    }
    //If no fish, find a new space
    newLocation = findCellToSwap(sharkCell, super.getMyAdjacencyList().getNeighbors(sharkCell), 0);
    //If shark moves to an empty space, return this
    if (!(newLocation.getId() == sharkCell.getId())) {
      return newLocation;
    }
    //No places to move the shark state to, so return the same cell
    return sharkCell;
  }

  /**
   * Method that moves the fish
   * @param fishCell
   * @return the new cell the fish is in
   */
  public Cell moveFish(Cell fishCell) {
    //See if there is an adjacent location for the fish to move into
    Cell newLocation = findCellToSwap(fishCell, super.getMyAdjacencyList().getNeighbors(fishCell), 0);
    //If fish can move to a new empty cell, return this
    if (!(newLocation.getId() == fishCell.getId())) {
      return newLocation;
    }
    return fishCell;
  }
}



