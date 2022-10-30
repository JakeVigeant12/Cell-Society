package cellsociety.model.cells;

import java.awt.*;
import java.util.List;

public class PercolationCell extends Cell {
  // Key States
  // 0 = empty
  // 1 = Percolated
  // 2 = Blocked

  /**
   * Constructor for PercolationCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public PercolationCell(int state, Point id){
    super(state, id);
  }

  /**
   * Method that sets the next state of the cell
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
}
