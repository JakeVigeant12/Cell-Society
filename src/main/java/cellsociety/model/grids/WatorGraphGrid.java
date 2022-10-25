package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WatorGraphGrid extends GraphGrid {

  private HashMap<Integer, Cell> myCells;
  private HashMap<Cell, List<Cell>> myAdjacenyList;
  private List<Cell> emptyCells;
  private Properties myProperties;
  private final String cellPackagePath = "cellsociety.model.cells.";
  private Neighborhood simulationNeighbors;

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public WatorGraphGrid(GridWrapper gridParsing, Properties properties) {
    super(gridParsing, properties);
  }

  public HashMap<Cell, List<Cell>> getMyAdjacenyList() {
    return myAdjacenyList;
  }

  @Override
  public Map<Integer, Cell> createCells(GridWrapper inputLayout) {
    return super.createCells(inputLayout);
  }

  @Override
  public void computeStates() {
    emptyCells = new ArrayList<>();
    for (Cell currentCell : super.myAdjacenyList.keySet()) {
      if (currentCell.getCurrentState() == 2) {
        Cell newShark =  moveShark(currentCell);
        if(newShark.getId() != currentCell.getId()) {
          //If the shark moves, swap the states and then deal with reproduction or death
          newShark.swapCells(currentCell);
          newShark.updateState();
          currentCell.setCurrentState(3);
          //if(currentCell.readyToReproduce()){
            //newShark.setCurrentState(2);
          //}
        }
      }
    }
    for (Cell currentCell : super.myAdjacenyList.keySet()) {
      if (currentCell.getCurrentState() == 1) {
        Cell newFish = moveFish(currentCell);
        if(newFish.getId() != currentCell.getId()) {
          newFish.swapCells(currentCell);
          //if(currentCell.readyToReproduce()){
            //newFish.setCurrentState(1);
          //}
        }
      }
    }
    //Erase shark placeholders
    for (Cell currentCell : super.myAdjacenyList.keySet()) {
      if (currentCell.getCurrentState() == 3) {
          currentCell.setCurrentState(0);
      }
    }
    for (Cell currentCell : super.myAdjacenyList.keySet()) {
      currentCell.updateState();
    }
  }

  public Cell moveShark(Cell sharkCell) {
    //See if any fish may be eaten first
    Cell newLocation = findCelltoSwap(sharkCell, super.myAdjacenyList.get(sharkCell),
        1);
    //If the shark can eat a fish, return the fish cell
    if(!(newLocation.getId() == sharkCell.getId())){
      //Do not change the current sharkCell's state yet so no fish can move here on the fish iteration
      return newLocation;
    }
    //If no fish, find a new space
    newLocation = findCelltoSwap(sharkCell, super.myAdjacenyList.get(sharkCell),0);
    //If shark moves to an empty space, return this
    if(!(newLocation.getId() == sharkCell.getId())){
      return newLocation;
    }
    //No places to move the shark state to, so return the same cell
    return sharkCell;
  }


  public Cell moveFish(Cell fishCell) {
    //See if there is an adjacent location for the fish to move into
    Cell newLocation = findCelltoSwap(fishCell, super.myAdjacenyList.get(fishCell), 0);
    //If fish can move to a new empty cell, return this
    if(!(newLocation.getId() == fishCell.getId())){
      return newLocation;
    }
    return fishCell;
  }

  public Cell findCelltoSwap(Cell startingCell, List<Cell> locations, int targetState) {
    ArrayList<Cell> potentialLocations = new ArrayList();
    for (Cell potentialCell : locations) {
      if (potentialCell.getCurrentState() == targetState) {
        potentialLocations.add(potentialCell);
      }
      //No cells found that match the state we need to swap into
      if(potentialLocations.size() == 0){
          return startingCell;
      }
    }
    //Of the cells with the correct ending state, select one at random
    return potentialLocations.get(getRandomIndex(potentialLocations.size()));
  }
  public int getRandomIndex(int listLength){
    return (int) Math.floor(Math.random()*(listLength));
  }
}

