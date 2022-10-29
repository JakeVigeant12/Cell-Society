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
  protected Map<Cell, List<Cell>> myAdjacencyList;
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
    myAdjacencyList = new HashMap<>();
    myCells = createCells(gridParsing);
    simulationNeighbors = setNeighbors(properties.getProperty("Type"));
    myAdjacencyList =initializeNeighbors(gridParsing, myCells, simulationNeighbors);
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
        createNeighborhood(i - 1, j - 1, gridParsing, currId - gridParsing.column(0) - 1, simulationNeighbors, 0, adjacencyList, currentCell, myCells);
        createNeighborhood(i - 1, j, gridParsing, currId - gridParsing.column(0), simulationNeighbors, 1, adjacencyList, currentCell, myCells);
        createNeighborhood(i - 1, j + 1, gridParsing, currId - gridParsing.column(0) + 1, simulationNeighbors, 2, adjacencyList, currentCell, myCells);
        createNeighborhood(i, j - 1, gridParsing, currId - 1, simulationNeighbors, 3, adjacencyList, currentCell, myCells);
        createNeighborhood(i, j + 1, gridParsing, currId + 1, simulationNeighbors, 4, adjacencyList, currentCell, myCells);
        createNeighborhood(i + 1, j - 1, gridParsing, currId + gridParsing.column(0) - 1, simulationNeighbors, 5, adjacencyList, currentCell, myCells);
        createNeighborhood(i + 1, j, gridParsing, currId + gridParsing.column(0), simulationNeighbors, 6, adjacencyList, currentCell, myCells);
        createNeighborhood(i + 1, j + 1, gridParsing, currId + gridParsing.column(0) + 1, simulationNeighbors, 7, adjacencyList, currentCell, myCells);
      }
    }
    return adjacencyList;
  }

  private void createNeighborhood(int i, int j, GridWrapper gridParsing, int currId, Neighborhood simulationNeighbors, int neighborNumber, HashMap<Cell, List<Cell>> adjacencyList, Cell currentCell, Map<Integer, Cell> myCells) {
    if(isInBounds(i, j, gridParsing)){
      if(simulationNeighbors.countNeighbor(neighborNumber)) {
        adjacencyList.get(currentCell).add(myCells.get(currId));
      }
    }
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
    for (Cell currentCell : myAdjacencyList.keySet()){
      currentCell.setFutureState(myAdjacencyList.get(currentCell));
      if (currentCell.getCurrentState() == 0) { // creates a list of empty cells so that the game knows where a cell can move to
        emptyCells.add(currentCell);
      }
    }
    for (Cell currentCell : myAdjacencyList.keySet()){
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
