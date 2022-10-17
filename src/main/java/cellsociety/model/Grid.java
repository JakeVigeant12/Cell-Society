package cellsociety.model;

import java.util.*;

//abstraction of grid to allow flexibility in implementation
public abstract class Grid {
  public void computeStates() {
    return;
  }

  public abstract void populateGrid(List<List<String>> inputLayout);
}

