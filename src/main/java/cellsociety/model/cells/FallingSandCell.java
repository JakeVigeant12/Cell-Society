package cellsociety.model.cells;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FallingSandCell extends Cell {

    /**
     * Sets the future state of the cell
     * @param neighbors
     */
    @Override
    public void setFutureState(List<Cell> neighbors) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        neighborHood = neighbors;
        wantsToSwap = false;
        try {
            this.getClass().getDeclaredMethod("set" + stateMap.get(getCurrentState())).invoke(this);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw e;
        }
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
  private void setWATER() {
    if (neighborHood.get(LOWER).getCurrentState()
        == EMPTY) { // if the cell is water and below is empty
      setFutureStateValue(EMPTY); // Turn into empty
    } else {
            /*try {
                int upperState = neighborHood.get(UPPER).getCurrentState();
                this.getClass().getDeclaredMethod("rules" + positionMap.get(UPPER) + stateMap.get(upperState)).invoke(this);
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw e;
            }*/

      if (neighborHood.get(UPPER).getCurrentState()
          == SAND) { // if the cell is water and above is sand
        setFutureStateValue(SAND); // Turn into sand
      }
      if (neighborHood.get(LOWERLEFT).getCurrentState() == EMPTY
          && neighborHood.get(LEFT).getCurrentState() == EMPTY) {
        setFutureStateValue(WATER);
        wantsToSwap = true;
        cellToSwap = neighborHood.get(LOWERLEFT);
      } else if (neighborHood.get(LOWERRIGHT).getCurrentState() == EMPTY
          && neighborHood.get(RIGHT).getCurrentState() == EMPTY) {
        setFutureStateValue(WATER);
        wantsToSwap = true;
        cellToSwap = neighborHood.get(LOWERRIGHT);
      } else if (neighborHood.get(LEFT).getCurrentState() == EMPTY
          && neighborHood.get(RIGHT).getCurrentState()
          == EMPTY) { // If the cell is water and the left and right are empty, but lower left and lower right are not empty
        setFutureStateValue(WATER);
        Random rand = new Random();
        wantsToSwap = true;
        if (rand.nextBoolean()) {
          cellToSwap = neighborHood.get(LEFT);
        } else {
          cellToSwap = neighborHood.get(RIGHT);
        }
      } else if (neighborHood.get(LEFT).getCurrentState() == EMPTY) {
        setFutureStateValue(WATER);
        wantsToSwap = true;
        cellToSwap = neighborHood.get(LEFT);
      } else if (neighborHood.get(RIGHT).getCurrentState() == EMPTY) {
        setFutureStateValue(WATER);
        wantsToSwap = true;
        cellToSwap = neighborHood.get(RIGHT);
      } else {
        setFutureStateValue(WATER); // Stay the same
      }
    }
  }

  private void rulesUPPERSAND() {
    setFutureStateValue(SAND); // Turn into SAND
  }

  private void rulesUPPERWATER() {
    setFutureStateValue(WATER);
  }

  private void rulesUPPEREMPTY() {
    setFutureStateValue(WATER);
  }

  private void rulesRIGHTEMPTY() {
    horizontalMovement(LOWERRIGHT, RIGHT);
  }

  private void rulesLEFTEMPTY() {
    horizontalMovement(LOWERLEFT, LEFT);
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
