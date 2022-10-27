package cellsociety.model.cells;

import java.util.List;

public class RockPaperScissorCell extends Cell {
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
    if (getCurrentState() == 0){ // if cell is a rock
      if (neighborStates.contains(1)){ // if neighbor is paper
        setFutureStateValue(1); // cell becomes paper
      }
    }
    else if (getCurrentState() == 1){ // if cell is paper
      if (neighborStates.contains(2)){ // if neighbor is scissors
        setFutureStateValue(2); // cell becomes scissors
      }
    }
    else if (getCurrentState() == 2){ // if cell is scissors
      if (neighborStates.contains(0)){ // if neighbor is rock
        setFutureStateValue(0); // cell becomes rock
      }
    }
  }

}
