package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.grids.Grid;
import cellsociety.parser.Parser;

import java.awt.Point;;
import java.util.Map;

public abstract class Model {
  private Parser inputGrid;
  private Grid myGrid;
  private String simType;

  //Use the parser abstraction to read in data file. Use myGrid to store the data structure representing the grid.
  //Tell grid to loop through its implementation and find new cell states

  /**
   * Method that computes the states of the grid class
   */
  public void computeStates(){
    myGrid.computeStates();
  }

  /**
   * Method that sets the current state of the cell
   * @param key
   * @param state
   */
  public void setCellCurrentState (Point key, int state){
  }

  /**
   * Method that returns the cells
   * @return adjacencyList
   */
  public Map<Point, Cell> getCells() {
    return Map.of(null,null);
  }
}
