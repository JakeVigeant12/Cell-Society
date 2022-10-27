package cellsociety.model.cells;

import java.util.List;

public class WatorWorldCell extends Cell {
  private int fishTurns;
  private int sharkTurns;
  private int sharkStarve;

  // Cell Key
  // 0 = water
  // 1 = fish
  // 2 = shark
  //3 = intermediaryShark (cleared at end of iteration)

  /**
   * Constructor for WaTorWorldCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public WatorWorldCell(int state, int id){
    super(state, id);
    fishTurns = 0;
    sharkTurns = 0;
    sharkStarve = 0;
  }
  @Override
  public void swapCells(Cell cellToSwap) {
    //Swap the current cell into the other cell's future state and vice versa
    cellToSwap.setFutureStateValue(this.getCurrentState());
    this.setFutureStateValue(cellToSwap.getCurrentState());

  }

  public int getState(){
    return getCurrentState();
  }
  public boolean readyToReproduce(){
    if(getCurrentState() == 1){
      if(fishTurns == 3){
        return true;
      }
      return false;
    }
    if(getCurrentState() == 3){
      if(sharkTurns == 2){
        return true;
      }
      return false;
    }
    return false;
  }
  public boolean readyToDie(){
    if(getCurrentState() == 2){
      if(sharkStarve == 3){
        return true;
      }
    }
    return false;
  }
  public void resetStateParameters(){
    fishTurns = 0;
    sharkTurns = 0;
    sharkStarve = 0;
  }
  @Override
  public void setFutureState(List<Cell> neighbors) {

  }

}
