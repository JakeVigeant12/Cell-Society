package cellsociety.model;

import static cellsociety.view.GridScreen.TYPE;

import cellsociety.model.cells.Cell;
import cellsociety.model.grids.*;
import cellsociety.view.GridWrapper;

import java.awt.Point;;
import java.util.Map;
import java.util.Properties;

//Default implementation of the model
public class InitialModelImplementation extends Model {

  public static final String WATOR_WORLD = "WatorWorld";
  public static final String SEGREGATION = "Segregation";
  public static final String FALLING_SAND = "FallingSand";
  private final Grid myGrid;

  /**
   * Constructor for the model
   * @param gridWrapper
   * @param simParameters
   */
  public InitialModelImplementation(GridWrapper gridWrapper, Properties simParameters) {
    if(simParameters.getProperty(TYPE).equals(WATOR_WORLD)){
      myGrid = new WatorGraphGrid(gridWrapper, simParameters);
    }
    else if(simParameters.getProperty(TYPE).equals(SEGREGATION)) {
      myGrid = new SegregationGraphGrid(gridWrapper, simParameters);
    }
    else if (simParameters.getProperty(TYPE).equals(FALLING_SAND)) {
      myGrid = new FallingSandGraphGrid(gridWrapper, simParameters);
    }
    else {
      myGrid = new GraphGrid(gridWrapper, simParameters);
    }

  }

  /**
   * Method that computes the states of the cells
   */
  public void computeStates() {
    myGrid.computeStates();
  }

  /**
   * Method that sets the current state of a cell
   * @param key
   * @param state
   */
  @Override
  public void setCellCurrentState (Point key, int state){
    myGrid.setCellCurrentState(key, state);
  }

  /**
   * Method that returns the cells
   * @return cells
   */
  @Override
  public Map<Point, Cell> getCells() {
    return myGrid.getCells();
  }
}
