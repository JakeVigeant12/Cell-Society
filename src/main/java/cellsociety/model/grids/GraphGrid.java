package cellsociety.model.grids;


import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.CompleteNeighborhood;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.model.neighborhoods.NoDiagonalNeighborhood;
import cellsociety.view.GridWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GraphGrid extends Grid {
  protected Map<Integer, Cell> myCells;
  protected Map<Cell, List<Cell>> myAdjacenyList;
  protected List<Cell> emptyCells;
  protected Properties myProperties;
  protected final String cellPackagePath = "cellsociety.model.cells.";
  protected Neighborhood simulationNeighbors;
  public static final String DEFAULT_RESOURCE_PACKAGE = GraphGrid.class.getPackageName() + ".";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");

  /**
   * Constructor for GraphGrid class
   * @param gridParsing is the layout of the grid
   */
  public GraphGrid(GridWrapper gridParsing, Properties properties) {
    myProperties = properties;
    myAdjacenyList = new HashMap<>();
    myCells = createCells(gridParsing);
    simulationNeighbors = setNeighbors(properties.getProperty("Type"));
    myAdjacenyList=initializeNeighbors(gridParsing, myCells, simulationNeighbors);
  }

  /**
   * Method that creates the cells for the grid
   *
   * @param inputLayout
   * @return
   */
  @Override
  //Assume grid values are passed in as expected, sans dimensions
  public Map<Integer, Cell> createCells(GridWrapper inputLayout) {
    //Used to ID the cells as they are created for ease of access, upper left is 1, lower right is max
    Map<Integer, Cell> cellHolder = new HashMap<>();
    int cellCount = 0;
    for(int i = 0; i < inputLayout.row(); i++){
      for(int j = 0; j < inputLayout.column(0); j++){
        cellCount++;
        createCell(inputLayout.get(i, j), cellHolder, cellCount);
      }
    }
    return cellHolder;
  }

  private void createCell(int cellData, Map<Integer, Cell> cellHolder, int cellCount) {
    Cell newCell;
    try {
      Class<?> cellClass = Class.forName(cellPackagePath + myProperties.get("Type") + "Cell");
      Constructor<?>[] makeNewCell = cellClass.getConstructors();
      if(makeNewCell[0].getParameterCount() == 3){
        double parameter;
        try {
          parameter = Double.parseDouble((String) myProperties.get("Parameters"));
        }
        catch (NullPointerException e) {//No parameter specified in .sim file
          try {//load parameter from .sim file
            parameter = Double.parseDouble(ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Default" + myProperties.get("Type")).getString("Parameters"));
          }
          catch (MissingResourceException e1) {//Cannot find default resource, either cannot find .properties file or missing parameter in .properties file
            e1.printStackTrace();
            throw new IllegalStateException("Cannot find default resource");
          }
        }
        newCell = (Cell) makeNewCell[0].newInstance(cellData, cellCount, parameter);
      }
      else{
        newCell = (Cell) makeNewCell[0].newInstance(cellData, cellCount);
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
             InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    cellHolder.putIfAbsent(cellCount, newCell);
  }

  @Override
  public void setCellCurrentState (int key, int state){
    myCells.get(key).setCurrentState(state);
  }

  
  //TODO refactor
  public Neighborhood setNeighbors(String simType){
    if(simType.equals("Fire")){
      return new NoDiagonalNeighborhood();
    }
    else{
      return new CompleteNeighborhood();
    }
  }
  @Override
  public Map<Cell, List<Cell>> initializeNeighbors(GridWrapper gridParsing, Map<Integer, Cell> myCells, Neighborhood simulationNeighbors) {
    //Currently assumes the use of a rectangular input file, thus rectangular gridparsing
    //ID of the current cell
    HashMap<Cell, List<Cell>> adjacencyList = new HashMap<>();
    int currId = 0;
    for (int i = 0; i < gridParsing.row(); i++) {
      for (int j = 0; j < gridParsing.column(0); j++) {
        List<Cell> neighbors = new ArrayList<>();
        currId++;
        Cell currentCell = myCells.get(currId);
        adjacencyList.putIfAbsent(currentCell, neighbors);
        if(isInBounds(i-1, j-1, gridParsing)){
          int upperLeftNeighborId = currId - gridParsing.column(0) -1;
          if(simulationNeighbors.countNeighbor(0)) {
            adjacencyList.get(currentCell).add(myCells.get(upperLeftNeighborId));
          }
        }
        if(isInBounds(i - 1, j, gridParsing)){
          int topNeighborId = currId - gridParsing.column(0);
          if(simulationNeighbors.countNeighbor(1)) {
            adjacencyList.get(currentCell).add(myCells.get(topNeighborId));
          }
        }
        if(isInBounds(i-1, j+1, gridParsing)){
          int upperRightNeighborId = currId - gridParsing.column(0) + 1;
          if(simulationNeighbors.countNeighbor(2)) {
            adjacencyList.get(currentCell).add(myCells.get(upperRightNeighborId));
          }
        }
        if(isInBounds(i, j-1, gridParsing)){
          int leftNeighborId = currId - 1;
          if(simulationNeighbors.countNeighbor(3)) {
            adjacencyList.get(currentCell).add(myCells.get(leftNeighborId));
          }
        }
        if(isInBounds(i, j+1, gridParsing)){
          int rightNeighborId = currId + 1;
          if(simulationNeighbors.countNeighbor(4)) {
            adjacencyList.get(currentCell).add(myCells.get(rightNeighborId));
          }
        }
        if(isInBounds(i+1, j-1, gridParsing)){
          int lowerLeftNeighborId = currId + gridParsing.column(0) - 1;
          if(simulationNeighbors.countNeighbor(5)) {
            adjacencyList.get(currentCell).add(myCells.get(lowerLeftNeighborId));
          }
        }
        if(isInBounds(i+1, j, gridParsing)){
          int bottomNeighborId = currId + gridParsing.column(0);
          if(simulationNeighbors.countNeighbor(6)) {
            adjacencyList.get(currentCell).add(myCells.get(bottomNeighborId));
          }
        }
        if(isInBounds(i+1, j+1, gridParsing)){
          int bottomRightNeighborId = currId + gridParsing.column(0) + 1;
          if(simulationNeighbors.countNeighbor(7)) {
            adjacencyList.get(currentCell).add(myCells.get(bottomRightNeighborId));
          }
        }
      }
    }
    return adjacencyList;
  }

  /**
   * Method that checks if the cell is in bounds
   * @param row
   * @param col
   * @param gridWrapper
   * @return
   */
  public static boolean isInBounds(int row, int col, GridWrapper gridWrapper){
    return (row >= 0 && row < gridWrapper.row()) && (col >= 0 && col < gridWrapper.column(0));
  }

  /**
   * Method that does two passes, the first sets the state, the second updates the state
   */
  @Override
  public void computeStates() {
    emptyCells = new ArrayList<>();
    for (Cell currentCell : myAdjacenyList.keySet()){
      currentCell.setFutureState(myAdjacenyList.get(currentCell));
      if (currentCell.getCurrentState() == 0) { // creates a list of empty cells so that the game knows where a cell can move to
        emptyCells.add(currentCell);
      }
    }
    for (Cell currentCell : myAdjacenyList.keySet()){
      currentCell.updateState();
    }
  }

  /**
   * Method that returns the map of cells
   * @return myCells
   */
  @Override
  public Map<Integer, Cell> getCells(){
    return myCells;
  }
}
