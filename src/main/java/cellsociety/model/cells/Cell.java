package cellsociety.model.cells;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Cell {

  private int currentState;
  private int futureState;
  private Point id;

  /**
   * Constructor for Cell class
   *
   * @param state is the state of the cell
   * @param id    is the identification number for this cell
   */
  public Cell(int state, Point id) throws IllegalStateException {
    this.currentState = state;
    this.id = id;
  }

  /**
   * Method that returns the state of the cell
   *
   * @return state of the cell
   */
  public int getCurrentState() {
    return currentState;
  }

  /**
   * Method that returns the future state of the cell
   *
   * @return future state of the cell
   */
  public int getFutureState() {
    return futureState;
  }

  public void setCurrentState(int state) {
    currentState = state;
  }

  /**
   * Method that sets the future state of the cell
   */
  public void setFutureState(List<Cell> neighbors) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    futureState = currentState;
  }

  /**
   * Method that sets the future state of the cell
   *
   * @param nextState is the future state of the cell
   */
  public void setFutureStateValue(int nextState) {
    this.futureState = nextState;
  }

  /**
   * Method that sets the id of the cell
   *
   * @param id
   */
  public void setId(Point id) {
    this.id = id;
  }

  /**
   * Method that returns the id of the cell
   *
   * @return id of the cell
   */
  public Point getId() {
    return this.id;
  }

  /**
   * Method that manually updates the state of the cell
   */
  public void updateState() {
    currentState = futureState;
  }

  /**
   * Method to swap states with two cells
   *
   * @param cellToSwap is the cell to swap with
   */
  public void swapCellStates(Cell cellToSwap) {
    Cell neighborCopy = new Cell(cellToSwap.getCurrentState(), cellToSwap.getId());

    cellToSwap.setFutureStateValue(
        getCurrentState()); // set neighbor's future state to current state
    setFutureStateValue(
        neighborCopy.getCurrentState()); // set current cell's future state to neighbor's current statee
  }

  /**
   * Method to swap two cells
   *
   * @param cellToSwap is the cell to swap with
   */
  public void swapCells(Cell cellToSwap) {
    Cell neighborCopy = new Cell(cellToSwap.getCurrentState(), cellToSwap.getId());

    cellToSwap.setCurrentState(getCurrentState()); // set neighbor's future state to current state
    setCurrentState(
        neighborCopy.getCurrentState()); // set current cell's future state to neighbor's current state
  }

  /**
   * Method that returns the list of neighbor states of the cell
   *
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
