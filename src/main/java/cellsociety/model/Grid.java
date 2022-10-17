package cellsociety.model;

import java.util.*;

//abstraction of grid to allow flexibility in implementation
public abstract class Grid {

  private HashMap<Integer, Cell> myCells;
  private HashMap<Cell, ArrayList<Cell>> myAdjacenyList;


  public void computeStates() {
    return;
  }

  public abstract void populateGrid(ArrayList<ArrayList<String>> inputLayout);
}

