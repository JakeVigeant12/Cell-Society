package cellsociety.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphGrid extends Grid{
  private HashMap<Integer, Cell> myCells;
  private HashMap<Cell, ArrayList<Cell>> myAdjacenyList;
  private final String simType;
  public GraphGrid(ArrayList<ArrayList<String>> graphParsing, String simInput) {
    simType = simInput;
    populateGrid(graphParsing);
  }

  @Override
  public void populateGrid(ArrayList<ArrayList<String>> inputLayout) {

  }
}
