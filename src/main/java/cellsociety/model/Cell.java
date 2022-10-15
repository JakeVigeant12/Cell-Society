package cellsociety.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
  private int currentState;
  private int futureState;
  private int row;
  private int col;

  /**
   * Constructor for Cell class
   * @param state is the state of the cell
   * @param row is the row of the cell
   * @param col is the column of the cell
   */
  public Cell(int state, int row, int col) {
    this.currentState = state;
    this.row = row;
    this.col = col;
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
   * Method that returns the row of the cell
   * @return row of the cell
   */
  public int getRow() {
    return row;
  }

  /**
   * Method that returns the column of the cell
   * @return column of the cell
   */
  public int getCol() {
    return col;
  }

  /**
   * Method that sets the row of the cell
   * @param row
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Method that sets the column of the cell
   * @param col
   */
  public void setCol(int col) {
    this.col = col;
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
    Cell neighborCopy = new Cell(neighbor.getCurrentState(), neighbor.getRow(), neighbor.getCol());
    neighbor.setFutureStateValue(currentState); // set neighbor's future state to current state
    setFutureStateValue(neighborCopy.getCurrentState()); // set current cell's future state to neighbor's current state
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
}
