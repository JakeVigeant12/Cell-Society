package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
  public void createCells(GridWrapper inputLayout) {
    super.createCells(inputLayout);
  }

  @Override
  public void computeStates() {
    emptyCells = new ArrayList<>();
    for (Cell currentCell : myAdjacenyList.keySet()) {
      if (currentCell.getCurrentState() == 2) {
        moveShark(currentCell);
      }
    }
    for (Cell currentCell : myAdjacenyList.keySet()) {
      if (currentCell.getCurrentState() == 1) {
        //Deal with fish on second loop through
      }
    }
    for (Cell currentCell : myAdjacenyList.keySet()) {
      currentCell.updateState();
    }
  }

  public void moveShark(Cell sharkCell) {
    Cell newLocation = findCelltoSwap(myAdjacenyList.get(sharkCell),
        2); //See if any fish may be eaten first
    //If no fish, find a new space
    return;
    }

  public Cell findCelltoSwap(List<Cell> locations, int targetState) {
    ArrayList<Cell> potentialLocations = new ArrayList();
    for (Cell potentialCell : locations) {
      if (potentialCell.getCurrentState() == targetState) {
        potentialLocations.add(potentialCell);
      }
      return potentialLocations.get(getRandomIndex(potentialLocations.size()));
    }
    //Of the cells with the correct ending state, select one at random

  }
  public int getRandomIndex(int listLength){
    return (int) Math.floor(Math.random()*(listLength));
  }
}

