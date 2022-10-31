package cellsociety.model.cells;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class RockPaperScissorCell extends Cell {

  private static final int ROCK = 0;
  private static final int PAPER = 1;
  private static final int SCISSOR = 2;
  List<Integer> myNeighborStates;
  private Map<Integer, String> stateMap;
  // Key States
  // 0 = Rock
  // 1 = Paper
  // 2 = Scissors

  /**
   * Constructor for RockPaperScissorsCell class
   *
   * @param state is the state of the cell
   * @param id    is the id of the cell
   */
  public RockPaperScissorCell(int state, Point id) {
    super(state, id);
    stateMap = Map.of(ROCK, "ROCK", PAPER, "PAPER", SCISSOR, "SCISSOR");
  }

  /**
   * Method that sets the next state of the cell
   *
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) throws IllegalStateException {
    myNeighborStates = getNeighborStates(neighbors);
    try {
      this.getClass().getDeclaredMethod("set" + stateMap.get(getCurrentState())).invoke(this);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new IllegalStateException("methodNotFound",e);
    }

  }

  private void setROCK() {
    if (myNeighborStates.contains(PAPER)) { // if neighbor is paper
      setFutureStateValue(PAPER); // cell becomes paper
    }
  }

  private void setPAPER() {
    if (myNeighborStates.contains(SCISSOR)) { // if neighbor is scissors
      setFutureStateValue(SCISSOR); // cell becomes scissors
    }
  }

  private void setSCISSOR() {
    if (myNeighborStates.contains(ROCK)) { // if neighbor is rock
      setFutureStateValue(ROCK); // cell becomes rock
    }
  }

}
