package cellsociety.model.cells;

import java.awt.*;
import java.util.List;
import java.util.Properties;

public class GameOfLifeCell extends Cell {
  /**
   * Constructor for GameOfLifeCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public GameOfLifeCell(int state, Point id) {
    super(state, id);
  }

  /**
   * Method that returns the next state of the cell
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
}
