package cellsociety.model.cells;

import java.awt.*;
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
  public RockPaperScissorCell(int state, Point id){
    super(state, id);
  }

  /**
   * Method that sets the next state of the cell
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
}
