package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
  private int currentState;
  private int futureState;
  private int id;


  /**
   * Constructor for Cell class
   * @param state is the state of the cell
   * @param id is the identification number for this cell
   */
  public Cell(int state, int id) {
    this.currentState = state;
    this.id = id;
  }

  /**
   * Method that returns the state of the cell
   * @return state of the cell
   */
  public int getCurrentState() {
    return currentState;
  }

  /**
   * Method that returns the future state of the cell
   * @return future state of the cell
   */
  public int getFutureState() {
    return futureState;
  }

  /**
   * Method that sets the future state of the cell
   */
  public void setFutureState(List<Cell> neighbors) {
    futureState = currentState;
  }

  /**
   * Method that sets the future state of the cell
   * @param nextState is the future state of the cell
   */
  public void setFutureStateValue(int nextState) {
    this.futureState = nextState;
  }
  /**
   * Method that sets the id of the cell
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Method that manually updates the state of the cell
   */
  public void updateState() {
    currentState = futureState;
  }

  /**
   * Method to swap states with two cells
   * @param neighbor
   */
  public void swapCellStates(Cell neighbor) {
    //Cell neighborCopy = new Cell(neighbor.getCurrentState(), neighbor.getRow(), neighbor.getCol());
    //neighbor.setFutureStateValue(currentState); // set neighbor's future state to current state
    //setFutureStateValue(neighborCopy.getCurrentState()); // set current cell's future state to neighbor's current state
  }

  /**
   * Method that returns the list of neighbor states of the cell
   * @param neighbors
   * @return list of neighbor states
   */
  public List<Integer> getNeighborStates(List<Cell> neighbors) {
    List<Integer> neighborStates = new ArrayList<>();
    for (Cell neighbor : neighbors) {
      neighborStates.add(neighbor.getCurrentState());
    }
    return neighborStates;
  }

  public int getId() {
    return this.id;
  }
}
