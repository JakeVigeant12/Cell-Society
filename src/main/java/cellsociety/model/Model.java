package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.parser.Parser;
import java.util.Map;

public abstract class Model {
  private Parser inputGrid;
  private Grid myGrid;
  private String simType;


  //Use the parser abstraction to read in data file. Use myGrid to store the data structure representing the grid.
  //Tell grid to loop through its implementation and find new cell states
  public void computeStates(){
    myGrid.computeStates();
  }

  public void setCellCurrentState (int key, int state){
  }

  public Map<Integer, Cell> getCells() {
    return null;
  }
}
