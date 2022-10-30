package cellsociety.model.cells;

import java.awt.*;
import java.util.List;
import java.util.Properties;

public class FireCell extends Cell {
  private static final int BURNING_TIME = 3;
  private int turns;
  private double myProbCatch;
  private static final int EMPTY = 0;
  private static final int TREE = 1;
  private static final int BURNING = 2;

  /**
   * Constructor for FireCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public FireCell(int state, Point id, double parameter){
    super(state, id);
    turns = 0;
    double probCatch = parameter;
    if (probCatch > 1){
      throw new IllegalArgumentException("Probability of catching fire must be between 0 and 1");
    }
    myProbCatch = probCatch;
  }

  /**
   * Method that returns the future state of the cell
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) {
    if (getCurrentState() == TREE && getNeighborStates(neighbors).contains(BURNING)) {
      double burnVal = Math.random();// If current cell is a tree and has a burning neighbor
      if ( burnVal < myProbCatch) { // If random number is less than probability of catching fire
        setFutureStateValue(BURNING); // Set current cell to burning
      }
      else {
        setFutureStateValue(TREE); // Set current cell to tree
      }
    }
    else if (getCurrentState() == BURNING) { // If current cell is burning
      if (turns == BURNING_TIME) { // If current cell has been burning for BURNING_TIME turns
        setFutureStateValue(EMPTY); // Set current cell to empty
      }
      else {
        turns++;
        setFutureStateValue(BURNING); // Keep current cell burning
      }
    }
    else {
      setFutureStateValue(getCurrentState()); // Keep current cell empty or tree
    }
  }
}
