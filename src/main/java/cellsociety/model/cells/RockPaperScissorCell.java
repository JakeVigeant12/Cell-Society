package cellsociety.model.cells;

import java.util.List;

public class RockPaperScissorCell extends Cell {
  private static final int ROCK = 0;
  private static final int PAPER = 1;
  private static final int SCISSOR = 2;

  // Key States
  // 0 = Rock
  // 1 = Paper
  // 2 = Scissors

  /**
   * Constructor for RockPaperScissorsCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public RockPaperScissorCell(int state, int id){
    super(state, id);
  }

  /**
   * Method that sets the next state of the cell
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) {
    List<Integer> neighborStates = getNeighborStates(neighbors);
    if (getCurrentState() == ROCK){ // if cell is a rock
      if (neighborStates.contains(PAPER)){ // if neighbor is paper
        setFutureStateValue(PAPER); // cell becomes paper
      }
    }
    else {
      if (getCurrentState() == PAPER){ // if cell is paper
        if (neighborStates.contains(SCISSOR)){ // if neighbor is scissors
          setFutureStateValue(SCISSOR); // cell becomes scissors
        }
      }
      else if (getCurrentState() == SCISSOR){ // if cell is scissors
        if (neighborStates.contains(ROCK)){ // if neighbor is rock
          setFutureStateValue(ROCK); // cell becomes rock
        }
      }
    }
  }

}
