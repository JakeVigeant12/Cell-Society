package cellsociety.model;

public abstract class Model {
  private Parser inputGrid;
  private Grid myGrid;


  //Use the parser abstraction to read in data file. Use myGrid to store the data structure representing the grid.
  //Tell grid to loop through its implementation and find new cell states
  public Model(){
    //decision on which sort of grid to implement. myGrid = new ();
  }
  public Grid computeStates(){
    myGrid.computeStates();
    return myGrid.extractState();
  }
  public Grid constructGrid(){
     inputGrid.parseData();
  }

}
