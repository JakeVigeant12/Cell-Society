package cellsociety.model;

import java.util.*;

//abstraction of grid to allow flexibility in implementation
public abstract class Grid {
  public void computeStates() {
    return;
  }
public abstract void createCells(List<List<String>> inputLayout);
  public abstract void initializeNeighbors(List<List<String>> inputLayout);

  public Map<Integer, Cell> getCells() {
    return null;
  }
}

