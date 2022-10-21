package cellsociety.model;

import cellsociety.SimType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphGrid extends Grid{
  private HashMap<Integer, Cell> myCells;
  private HashMap<Cell, List<Cell>> myAdjacenyList;
  private final SimType simType;
  public GraphGrid(List<List<String>> gridParsing, SimType simInput) {
    simType = simInput;
    myAdjacenyList = new HashMap<>();
    myCells = new HashMap<>();
    createCells(gridParsing);
    initializeNeighbors(gridParsing);

  }

  @Override
  //Assume grid values are passed in as expected, sans dimensions
  public void createCells(List<List<String>> inputLayout) {
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
            //TODO: implementations for these other cells
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
        myCells.putIfAbsent(cellCount,newCell);
      }
    }
  }

  @Override
  public void initializeNeighbors(List<List<String>> gridParsing) {
    //Currently assumes the use of a rectangular input file, thus rectangular gridparsing
    //ID of the current cell
    int currId = 0;
    for (int i = 0; i < gridParsing.size(); i++) {
      for (int j = 0; j < gridParsing.get(i).size(); j++) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        currId++;
        Cell currentCell = myCells.get(currId);
        myAdjacenyList.putIfAbsent(currentCell, neighbors);
        if(isInBounds(i - 1, j, gridParsing)){
          int topNeighborId = currId - gridParsing.get(i).size();
          myAdjacenyList.get(currentCell).add(myCells.get(topNeighborId));
        }
        if(isInBounds(i, j+1, gridParsing)){
          int rightNeighborId = currId +1;
          myAdjacenyList.get(currentCell).add(myCells.get(rightNeighborId));
        }
        if(isInBounds(i, j-1, gridParsing)){
          int leftNeighborId = currId - 1;
          myAdjacenyList.get(currentCell).add(myCells.get(leftNeighborId));
        }
        if(isInBounds(i+1, j, gridParsing)){
          int bottomNeighborId = currId + gridParsing.get(i).size();
          myAdjacenyList.get(currentCell).add(myCells.get(bottomNeighborId));
        }
        if(isInBounds(i+1, j+1, gridParsing)){
          int bottomRightNeighborId = currId + gridParsing.get(i).size() + 1;
          myAdjacenyList.get(currentCell).add(myCells.get(bottomRightNeighborId));
        }
        if(isInBounds(i-1, j-1, gridParsing)){
          int upperLeftNeighborId = currId - gridParsing.get(i).size() -1;
          myAdjacenyList.get(currentCell).add(myCells.get(upperLeftNeighborId));
        }
        if(isInBounds(i-1, j+1, gridParsing)){
          int upperRightNeighborId = currId - gridParsing.get(i).size() + 1;
          myAdjacenyList.get(currentCell).add(myCells.get(upperRightNeighborId));
        }
        if(isInBounds(i+1, j-1, gridParsing)){
          int lowerLeftNeighborId = currId + gridParsing.get(i).size() - 1;
          myAdjacenyList.get(currentCell).add(myCells.get(lowerLeftNeighborId));
        }
      }
    }
  }
  public static boolean isInBounds(int row, int col, List<List<String>> gridParsing){
    boolean res = (row >= 0 && row < gridParsing.size()) && (col >= 0 && col < gridParsing.get(row).size());
    return res;
  }
  @Override
  public void computeStates() {
    for(Cell currentCell  : myAdjacenyList.keySet()){
      currentCell.setFutureState(myAdjacenyList.get(currentCell));
    }
    for(Cell currentCell  : myAdjacenyList.keySet()){
      currentCell.updateState();
    }
  }

  @Override
  public Map<Integer, Cell> getCells(){
    return myCells;
  }
}
