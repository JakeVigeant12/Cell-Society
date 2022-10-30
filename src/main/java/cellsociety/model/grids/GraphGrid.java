package cellsociety.model.grids;


import static cellsociety.view.GridScreen.TYPE;
import static cellsociety.view.GridView.CELL;

import cellsociety.model.AdjacencyList;
import cellsociety.model.AdjacencyListToroidal;
import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.CompleteNeighborhood;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.model.neighborhoods.NoDiagonalNeighborhood;
import cellsociety.view.GridWrapper;

import java.awt.Point;;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

public class GraphGrid extends Grid {

  public static final String FIRE = "Fire";
  public static final String CANNOT_MAKE_BUTTONS = "Cannot make buttons";
  public static final String MISSING_PARAMETERS = "Missing parameters";
  public static final String PARAMETERS = "Parameters";
  public static final String DEFAULT = "Default";
  private Map<Point, Cell> myCells;
  private AdjacencyList myAdjacencyList;
  private List<Cell> emptyCells;
  private Properties myProperties;
  private final String cellPackagePath = "cellsociety.model.cells.";
  private Neighborhood simulationNeighbors;
  public static final String DEFAULT_RESOURCE_PACKAGE = GraphGrid.class.getPackageName() + ".";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   */
  public GraphGrid(GridWrapper gridParsing, Properties properties) throws IllegalStateException {
    myProperties = properties;
    myCells = createCells(gridParsing);
    simulationNeighbors = setNeighbors(properties.getProperty("Type"));
    try {
      if (properties.getProperty("EdgePolicy").equals("toroidal")) {
        myAdjacencyList = new AdjacencyListToroidal(gridParsing, myCells, simulationNeighbors);
      }
    } catch (NullPointerException e) {
      myAdjacencyList = new AdjacencyList(gridParsing, myCells, simulationNeighbors);
    }
  }


  public Map<Point, Cell> getMyCells() {
    return myCells;
  }

  public void setMyCells(Map<Point, Cell> myCells) {
    this.myCells = myCells;
  }

  public List<Cell> getEmptyCells() {
    return emptyCells;
  }

  public void setEmptyCells(List<Cell> emptyCells) {
    this.emptyCells = emptyCells;
  }

  protected AdjacencyList getMyAdjacencyList() {
    return myAdjacencyList;
  }

  public void setMyAdjacencyList(AdjacencyList myAdjacencyList) {
    this.myAdjacencyList = myAdjacencyList;
  }

  public Neighborhood getSimulationNeighbors() {
    return simulationNeighbors;
  }

  public void setSimulationNeighbors(Neighborhood simulationNeighbors) {
    this.simulationNeighbors = simulationNeighbors;
  }


  /**
   * Method that creates the cells for the grid
   *
   * @param inputLayout
   * @return
   */
  //Assume grid values are passed in as expected, sans dimensions
  private Map<Point, Cell> createCells(GridWrapper inputLayout) throws IllegalStateException {
    //Used to ID the cells as they are created for ease of access, upper left is 1, lower right is max
    Map<Point, Cell> cellHolder = new HashMap<>();
    for (int i = 0; i < inputLayout.getRowCount(); i++) {
      for (int j = 0; j < inputLayout.getRowSize(0); j++) {
        createCell(inputLayout.getState(i, j), cellHolder, new Point(j, i));
      }
    }
    return cellHolder;
  }

  /**
   * Method that creates a cell
   *
   * @param cellData
   * @param cellHolder
   * @param cellCount
   */
  private void createCell(int cellData, Map<Point, Cell> cellHolder, Point cellCount)
      throws IllegalStateException {
    Cell newCell;
    Class<?> cellClass;
    try {
      cellClass = Class.forName(cellPackagePath + myProperties.get("Type") + "Cell");
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("classNotFound");
    }
    Constructor<?>[] makeNewCell = cellClass.getConstructors();
    if (makeNewCell[0].getParameterCount() == 3) {
      newCell = getCellWithParameter(cellData, cellCount, makeNewCell);
    } else {
      try {
        newCell = (Cell) makeNewCell[0].newInstance(cellData, cellCount);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
        throw new IllegalStateException("parameterError");
      }
    }
    cellHolder.putIfAbsent(cellCount, newCell);
  }

  private Cell getCellWithParameter(int cellData, Point cellCount, Constructor<?>[] makeNewCell)
      throws IllegalStateException {
    Cell newCell;
    double parameter;
    try {
      parameter = Double.parseDouble((String) myProperties.get("Parameters"));
    } catch (NullPointerException e) {//No parameter specified in .sim file
      try {//load parameter from .sim file
        parameter = Double.parseDouble(ResourceBundle.getBundle(
                DEFAULT_RESOURCE_PACKAGE + "Default" + myProperties.get("Type"))
            .getString("Parameters"));
      } catch (
          MissingResourceException e1) {//Cannot find default resource, either cannot find .properties file or missing parameter in .properties file
        throw new IllegalStateException("parameterError");
      }
    }
    try {
      newCell = (Cell) makeNewCell[0].newInstance(cellData, cellCount, parameter);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new IllegalStateException("parameterError");
    }
    return newCell;
  }

  /**
   * Method that sets the current cell state of the grid
   *
   * @param key
   * @param state
   */
  @Override
  public void setCellCurrentState(Point key, int state) {
    myCells.get(key).setCurrentState(state);
  }

  /**
   * Sets the neighbors for the grid
   *
   * @param simType
   * @return
   */
  //TODO refactor
  public Neighborhood setNeighbors(String simType) {
    if (simType.equals(FIRE)) {
      return new NoDiagonalNeighborhood();
    } else {
      return new CompleteNeighborhood();
    }
  }

  /**
   * Method that checks if the cell is in bounds
   *
   * @param row
   * @param col
   * @param gridWrapper
   * @return
   */
  public static boolean isInBounds(int row, int col, GridWrapper gridWrapper) {
    return (row >= 0 && row < gridWrapper.getRowCount()) && (col >= 0
        && col < gridWrapper.getRowSize(0));
  }

  /**
   * Method that does two passes, the first sets the state, the second updates the state
   */
  @Override
  public void computeStates() {
    for (Cell currentCell : myAdjacencyList.getCells()) {
      currentCell.setFutureState(myAdjacencyList.getNeighbors(currentCell));
    }
    for (Cell currentCell : myAdjacencyList.getCells()) {
      currentCell.updateState();
    }
  }

  /**
   * Method that returns the map of cells
   *
   * @return myCells
   */
  @Override
  public Map<Point, Cell> getCells() {
    return myCells;
  }
}
