package cellsociety.model.cells;

import java.util.List;

public class PercolationCell extends Cell {
  private final static int EMPTY = 0;
  private final static int PERCOLATED = 1;
  private final static int BLOCKED = 2;
  // Key States
  // 0 = empty
  // 1 = Percolated
  // 2 = Blocked

  /**
   * Constructor for PercolationCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public PercolationCell(int state, int id){
    super(state, id);
  }

  /**
   * Method that sets the next state of the cell
   * @param neighbors is the list of neighbors of the cell
   * @return next state of the cell
   */
  @Override
  public void setFutureState(List<Cell> neighbors) {
    if (getCurrentState() == EMPTY){
      for (Cell neighbor : neighbors) {
        if (neighbor.getCurrentState() == PERCOLATED){
          setFutureStateValue(PERCOLATED);
        }
      }
    }
    else if (getCurrentState() == PERCOLATED){
      setFutureStateValue(PERCOLATED);
    }
    else if (getCurrentState() == BLOCKED){
      setFutureStateValue(BLOCKED);
    }
  }

}
