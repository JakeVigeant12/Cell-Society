package cellsociety.model;

import cellsociety.parser.Parser;

public abstract class Model {
  private Parser inputGrid;
  private Grid myGrid;
  private String simType;


  //Use the parser abstraction to read in data file. Use myGrid to store the data structure representing the grid.
  //Tell grid to loop through its implementation and find new cell states
  public void computeStates(){
    myGrid.computeStates();
  }
}
