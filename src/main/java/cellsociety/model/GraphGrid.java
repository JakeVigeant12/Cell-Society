package cellsociety.model;

import cellsociety.SimType;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphGrid extends Grid{
  private HashMap<Integer, Cell> myCells;
  private HashMap<Cell, ArrayList<Cell>> myAdjacenyList;
  private final SimType simType;
  public GraphGrid(ArrayList<ArrayList<String>> graphParsing, SimType simInput) {
    simType = simInput;
    populateGrid(graphParsing);
  }

  @Override
  //Assume grid values are passed in as expected, sans dimensions
  public void populateGrid(ArrayList<ArrayList<String>> inputLayout) {
    //TODO refactor method to generalize neighbor calculation using open/close solution
    for(ArrayList<String> currRow : inputLayout){
      //TODO: Implemented enum switch for now, refactor using abstract factory design pattern after functional
      for(String content : currRow){

      }


    }


  }
}
