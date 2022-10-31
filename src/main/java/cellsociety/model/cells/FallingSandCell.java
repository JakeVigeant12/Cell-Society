package cellsociety.model.cells;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FallingSandCell extends Cell {

  private static final int EMPTY = 0;
  private static final int SAND = 1;
  private static final int WATER = 2;
  private static final int BOUNDARY = 3;

  private static final int UPPERLEFT = 0;
  private static final int UPPER = 1;
  private static final int UPPERRIGHT = 2;
  private static final int LEFT = 3;
  private static final int RIGHT = 4;
  private static final int LOWERLEFT = 5;
  private static final int LOWER = 6;
  private static final int LOWERRIGHT = 7;

  // Key States
  // 0 = Empty
  // 1 = Sand
  // 2 = Water
  // 3 = Boundary

  // Directions
  // 0 1 2
  // 3 C 4
  // 5 6 7

  // Grid Loops Required: 3

  private boolean wantsToSwap;
  private Cell cellToSwap;
  private List<Cell> neighborHood;
  private Map<Integer, String> stateMap;
  private Map<Integer, String> positionMap;

  /**
   * Constructor for FallingSandCell class
   *
   * @param state is the state of the cell
   * @param id    is the id of the cell
   */
  public FallingSandCell(int state, Point id) {
    super(state, id);
    stateMap = Map.of(EMPTY, "EMPTY", SAND, "SAND", WATER, "WATER", BOUNDARY, "BOUNDARY");
    positionMap = Map.of(UPPERLEFT, "UPPERLEFT", UPPER, "UPPER", UPPERRIGHT, "UPPERRIGHT", LEFT,
        "LEFT", RIGHT, "RIGHT", LOWERLEFT, "LOWERLEFT", LOWER, "LOWER", LOWERRIGHT, "LOWERRIGHT");
  }

  /**
   * Method that returns the cell that the current cell wants to swap with
   *
   * @return cell that the current cell wants to swap with
   */
  public Cell getNeighborToSwap() {
    return cellToSwap;
  }

  /**
   * Method that returns whether the current cell wants to swap with another cell
   *
   * @return whether the current cell wants to swap with another cell
   */
  public boolean wantsToSwap() {
    return wantsToSwap;
  }

  /**
   * Sets the future state of the cell
   *
   * @param neighbors
   */
  @Override
  public void setFutureState(List<Cell> neighbors) throws IllegalStateException {
    neighborHood = neighbors;
    wantsToSwap = false;
    try {
      this.getClass().getDeclaredMethod("set" + stateMap.get(getCurrentState())).invoke(this);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new IllegalStateException("methodNotFound", e);
    }
  }

  /**
   * Special movement for empty cells
   */
  private void setEMPTY() {
    int newState = neighborHood.get(UPPER).getCurrentState();
    if (newState == SAND || newState == WATER) {
      setFutureStateValue(newState); // Turn into sand or water
    }
  }

  /**
   * Special movement for sand cells
   */
  // NOTE: This functionality was working with a ton of if elses, I did not have enough time to fix it to work with
  // reflection.
  private void setWATER()
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    if (neighborHood.get(LOWER).getCurrentState()
        == EMPTY) { // if the cell is water and below is empty
      setFutureStateValue(EMPTY); // Turn into empty
    } else {
      try {
        if (neighborHood.get(LEFT).getCurrentState() == EMPTY
            && neighborHood.get(RIGHT).getCurrentState()
            == EMPTY) { // If the cell is water and the left and right are empty, but lower left and lower right are not empty
          setFutureStateValue(WATER);
          wantsToSwap = true;
          Random rand = new Random();
          if (rand.nextBoolean()) {
            cellToSwap = neighborHood.get(LEFT);
          } else {
            cellToSwap = neighborHood.get(RIGHT);
          }
        } else {
          int upperState = neighborHood.get(UPPER).getCurrentState();
          this.getClass()
              .getDeclaredMethod("rules" + positionMap.get(UPPER) + stateMap.get(upperState))
              .invoke(this);

          int rightState = neighborHood.get(RIGHT).getCurrentState();
          this.getClass()
              .getDeclaredMethod("rules" + positionMap.get(RIGHT) + stateMap.get(rightState))
              .invoke(this);

          int leftState = neighborHood.get(LEFT).getCurrentState();
          this.getClass()
              .getDeclaredMethod("rules" + positionMap.get(LEFT) + stateMap.get(leftState))
              .invoke(this);
        }
      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
        throw e;
      }
    }
  }

  public void rulesUPPERSAND() {
    setFutureStateValue(SAND); // Turn into SAND
  }

  public void rulesLOWERSAND() {
    setFutureStateValue(WATER);
  }

  public void rulesLEFTSAND() {
    setFutureStateValue(WATER);
  }

  public void rulesRIGHTSAND() {
    setFutureStateValue(WATER);
  }

  public void rulesUPPERWATER() {
    setFutureStateValue(WATER);
  }

  public void rulesLOWERWATER() {
    setFutureStateValue(WATER);
  }

  public void rulesLEFTWATER() {
    setFutureStateValue(WATER);
  }

  public void rulesRIGHTWATER() {
    setFutureStateValue(WATER);
  }

  public void rulesUPPEREMPTY() {
    setFutureStateValue(WATER);
  }

  private void rulesRIGHTEMPTY() {
    horizontalMovement(LOWERRIGHT, RIGHT);
  }

  private void rulesLEFTEMPTY() {
    horizontalMovement(LOWERLEFT, LEFT);
  }

  public void rulesUPPERBOUNDARY() {
    setFutureStateValue(WATER);
  }

  public void rulesLOWERBOUNDARY() {
    setFutureStateValue(WATER);
  }

  private void rulesLEFTBOUNDARY() {
    setFutureStateValue(WATER);
  }

  private void rulesRIGHTBOUNDARY() {
    setFutureStateValue(WATER);
  }

  private void horizontalMovement(int lowerPosition, int position) {
    if (neighborHood.get(lowerPosition).getCurrentState() == EMPTY) {
      setFutureStateValue(WATER);
      wantsToSwap = true;
      cellToSwap = neighborHood.get(lowerPosition);
    } else {
      setFutureStateValue(WATER);
      wantsToSwap = true;
      cellToSwap = neighborHood.get(position);
    }
  }

  /**
   * Special movement for sand cells
   */
  private void setSAND() {
    int newState = neighborHood.get(LOWER).getCurrentState();
    if (newState == EMPTY || newState == WATER) {
      setFutureStateValue(newState); // Turn into empty
    }
  }

  /**
   * Special movement for boundary cells
   */
  private void setBOUNDARY() {
    setFutureStateValue(BOUNDARY);
  }
}
