package cellsociety.model;

import static cellsociety.SimType.GameOfLife;

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
    //Used to ID the cells as they are created for ease of access, upper left is 1, lower right is max
    int cellCount = 0;
    for(ArrayList<String> currRow : inputLayout){
      //TODO: Implemented enum switch for now, refactor using abstract factory design pattern after functional
      for(String cellData : currRow){
        cellCount++;
        Cell newCell;
        switch(simType) {
          case GameOfLife:
            newCell = new GameOfLifeCell(Integer.parseInt(cellData,cellCount));
            break;
          case Fire:
            break;
          case Segregation:
            break;
          case WatorWorld:
            break;
          case RockPaperScissors:
            break;
          case Percolation:
            break;
        }


      }


    }


  }
}
