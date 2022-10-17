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
    for(int i = 0; i < inputLayout.size(); i++){
      //TODO: Implemented enum switch for now, refactor using abstract factory design pattern after functional
      for(int j = 0; j < inputLayout.get(i).size(); j++){
        cellCount++;
        Cell newCell = null;
        Integer cellData  = Integer.parseInt(inputLayout.get(i).get(j));
        switch(simType) {
          case GameOfLife:
            newCell = new GameOfLifeCell(cellData,cellCount);
            break;
          case Fire:
            //TODO: Implement simParameters this is a dummy value
            newCell = new FireCell(cellData,cellCount, 0.1);//Get pcatch
            break;
            //TODO: implmentations for these other cells
          case Segregation:
            newCell = new GameOfLifeCell(cellData,cellCount);
            break;
          case WatorWorld:
            newCell = new GameOfLifeCell(cellData,cellCount);
            break;
          case RockPaperScissors:
            newCell = new GameOfLifeCell(cellData,cellCount);
            break;
          case Percolation:
            newCell = new GameOfLifeCell(cellData,cellCount);
            break;
        }
        myAdjacenyList.put(newCell, initializeNeighbors(inputLayout,i,j));
        myCells.put(cellCount,newCell);
      }
    }
  }
  public ArrayList<Cell> initializeNeighbors(ArrayList<ArrayList<String>> gridParsing, int row, int col){
      ArrayList<Cell> neighbors = new ArrayList<>();



      return neighbors;
  }
}
