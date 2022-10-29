package cellsociety.model.grids;


import cellsociety.model.AdjacencyList;
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
  protected AdjacencyList myAdjacencyList;
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
    myCells = createCells(gridParsing);
    simulationNeighbors = setNeighbors(properties.getProperty("Type"));
    myAdjacencyList = new AdjacencyList(gridParsing, myCells, simulationNeighbors);
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
    for(int i = 0; i < inputLayout.getRowCount(); i++){
      for(int j = 0; j < inputLayout.getRowSize(0); j++){
        cellCount++;
        createCell(inputLayout.getState(i, j), cellHolder, cellCount);
      }
    }
    return cellHolder;
  }

  /**
   * Method that creates a cell
   * @param cellData
   * @param cellHolder
   * @param cellCount
   */
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
            throw new IllegalStateException("Missing parameters");
          }
        }
        newCell = (Cell) makeNewCell[0].newInstance(cellData, cellCount, parameter);
      }
      else{
        newCell = (Cell) makeNewCell[0].newInstance(cellData, cellCount);
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
             InvocationTargetException e) {
      throw new IllegalStateException("Cannot make buttons");
    }
    cellHolder.putIfAbsent(cellCount, newCell);
  }

  /**
   * Method that sets the current cell state of the grid
   * @param key
   * @param state
   */
  @Override
  public void setCellCurrentState (int key, int state){
    myCells.get(key).setCurrentState(state);
  }

  /**
   * Sets the neighbors for the grid
   * @param simType
   * @return
   */
  //TODO refactor
  public Neighborhood setNeighbors(String simType){
    if(simType.equals("Fire")){
      return new NoDiagonalNeighborhood();
    }
    else{
      return new CompleteNeighborhood();
    }
  }

  /**
   * Method that initializes the neighbors for the grid
   * @param gridParsing
   * @param myCells
   * @param simulationNeighbors
   * @return the adjacency list
   */
  @Override
  public Map<Cell, List<Cell>> initializeNeighbors(GridWrapper gridParsing, Map<Integer, Cell> myCells, Neighborhood simulationNeighbors) {
    //Currently assumes the use of a rectangular input file, thus rectangular gridparsing
    //ID of the current cell
    Map<Cell, List<Cell>> adjacencyList = new HashMap<>();
    int currId = 0;
    for (int i = 0; i < gridParsing.getRowCount(); i++) {
      for (int j = 0; j < gridParsing.getRowSize(0); j++) {
        List<Cell> neighbors = new ArrayList<>();
        currId++;
        Cell currentCell = myCells.get(currId);
        adjacencyList.putIfAbsent(currentCell, neighbors);
        createNeighborhood(i - 1, j - 1, gridParsing, currId - gridParsing.getRowSize(0) - 1, simulationNeighbors, 0, adjacencyList, currentCell, myCells);
        createNeighborhood(i - 1, j, gridParsing, currId - gridParsing.getRowSize(0), simulationNeighbors, 1, adjacencyList, currentCell, myCells);
        createNeighborhood(i - 1, j + 1, gridParsing, currId - gridParsing.getRowSize(0) + 1, simulationNeighbors, 2, adjacencyList, currentCell, myCells);
        createNeighborhood(i, j - 1, gridParsing, currId - 1, simulationNeighbors, 3, adjacencyList, currentCell, myCells);
        createNeighborhood(i, j + 1, gridParsing, currId + 1, simulationNeighbors, 4, adjacencyList, currentCell, myCells);
        createNeighborhood(i + 1, j - 1, gridParsing, currId + gridParsing.getRowSize(0) - 1, simulationNeighbors, 5, adjacencyList, currentCell, myCells);
        createNeighborhood(i + 1, j, gridParsing, currId + gridParsing.getRowSize(0), simulationNeighbors, 6, adjacencyList, currentCell, myCells);
        createNeighborhood(i + 1, j + 1, gridParsing, currId + gridParsing.getRowSize(0) + 1, simulationNeighbors, 7, adjacencyList, currentCell, myCells);
      }
    }
    return adjacencyList;
  }

  /**
   * Method that creates the neighborhood for the cell
   * @param i
   * @param j
   * @param gridParsing
   * @param currId
   * @param simulationNeighbors
   * @param neighborNumber
   * @param adjacencyList
   * @param currentCell
   * @param myCells
   */
  private void createNeighborhood(int i, int j, GridWrapper gridParsing, int currId, Neighborhood simulationNeighbors, int neighborNumber, Map<Cell, List<Cell>> adjacencyList, Cell currentCell, Map<Integer, Cell> myCells) {
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
    return (row >= 0 && row < gridWrapper.getRowCount()) && (col >= 0 && col < gridWrapper.getRowSize(0));
  }

  /**
   * Method that does two passes, the first sets the state, the second updates the state
   */
  @Override
  public void computeStates() {
    emptyCells = new ArrayList<>();
    for (Cell currentCell : myAdjacencyList.getCells()){
      currentCell.setFutureState(myAdjacencyList.getNeighbors(currentCell));
      if (currentCell.getCurrentState() == 0) { // creates a list of empty cells so that the game knows where a cell can move to
        emptyCells.add(currentCell);
      }
    }
    for (Cell currentCell : myAdjacencyList.getCells()){
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
