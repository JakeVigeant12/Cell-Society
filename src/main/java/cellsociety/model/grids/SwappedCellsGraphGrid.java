package cellsociety.model.grids;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
  public Cell findCellToSwap(Cell startingCell, List<Cell> locations, int targetState) {
    List<Cell> potentialLocations = new ArrayList();
    for (Cell potentialCell : locations) {
      if (potentialCell.getCurrentState() == targetState) {
        potentialLocations.add(potentialCell);
      }
      //No cells found that match the state we need to swap into
      if(potentialLocations.isEmpty()){
        return startingCell;
      }
    }
    //Of the cells with the correct ending state, select one at random
    Random rand = new Random();
    return potentialLocations.get(rand.nextInt(potentialLocations.size()));
  }
  public int getRandomIndex(int listLength){
    return (int) Math.floor(Math.random()*(listLength));
  }

}
