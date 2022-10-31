package cellsociety.model.cells;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class FireCell extends Cell {

  private int turns;
  private double myProbCatch;
  private static final int EMPTY = 0;
  private static final int TREE = 1;
  private static final int BURNING = 2;
  private static final int BURNING_TIME = 3;
  public static final String PROBABILITY_ERROR_MESSAGE = "Probability of catching fire must be between 0 and 1";
  private List<Cell> myNeighbors;
  private Map<Integer, String> stateMap;

  /**
   * Constructor for FireCell class
   *
   * @param state is the state of the cell
   * @param id    is the id of the cell
   */
  public FireCell(int state, Point id, double parameter) throws IllegalStateException {
    super(state, id);
    turns = 0;
    double probCatch = parameter;
    if (probCatch > 1) {
      throw new IllegalStateException("probabilityError");
    }
    myProbCatch = probCatch;
    stateMap = Map.of(EMPTY, "EMPTY", TREE, "TREE", BURNING, "BURNING");
  }

  /**
   * Method that returns the future state of the cell
   *
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) throws IllegalStateException {
    myNeighbors = neighbors;
    try {
      this.getClass().getDeclaredMethod("set" + stateMap.get(getCurrentState())).invoke(this);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new IllegalStateException("methodNotFound", e);
    }
  }

  /**
   * Sets the state of a cell to Tree
   **/
  private void setTREE() {
    if (getNeighborStates(myNeighbors).contains(BURNING)) {
      double burnVal = Math.random();// If current cell is a tree and has a burning neighbor
      if (burnVal < myProbCatch) { // If random number is less than probability of catching fire
        setFutureStateValue(BURNING); // Set current cell to burning
      } else {
        setFutureStateValue(TREE); // Set current cell to tree
      }
    } else {
      setFutureStateValue(TREE);
    }
  }

  /**
   * Sets the state of a cell to Burning
   **/
  private void setBURNING() {
    if (turns == BURNING_TIME) { // If current cell has been burning for BURNING_TIME turns
      setFutureStateValue(EMPTY); // Set current cell to empty
    } else {
      turns++;
      setFutureStateValue(BURNING); // Keep current cell burning
    }
  }

  /**
   * Sets the state of a cell to Empty
   **/
  private void setEMPTY() {
    setFutureStateValue(EMPTY);
  }
}
