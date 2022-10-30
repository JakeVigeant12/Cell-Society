package cellsociety.model.cells;

import java.awt.*;
import java.util.List;

public class WatorWorldCell extends Cell {
  private int fishTurns;
  private int sharkTurns;
  private int sharkStarve;

  // Cell Key
  // 0 = water
  // 1 = fish
  // 2 = shark
  // 3 = intermediaryShark (cleared at end of iteration)

  /**
   * Constructor for WaTorWorldCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public WatorWorldCell(int state, Point id){
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
    if (getCurrentState() == 0) { // if the current cell is water
      setFutureStateValue(0); // do nothing
      fishTurns = 0;
      sharkTurns = 0;
      sharkStarve = 0;
    }
    else { // if the current cell is a fish or shark
      List<Integer> neighborStates = getNeighborStates(neighbors);
      // An animal can move to an empty cell
      if (getCurrentState() == 1){ // If current cell is a fish
        if (neighborStates.contains(0)){
          fishTurns++;
          if (fishTurns == 3){ // if the fish has been alive for 3 turns, then it will breed
            setFutureStateValue(1);
            fishTurns = 0;
          }
          else {
            setFutureStateValue(1); // Needs to swap with a water cell
          }
        }
        else {
          setFutureStateValue(1);
        }
      }
      else if (getCurrentState() == 2) { // If current cell is a shark and eats a fish
        if (neighborStates.contains(0) && !neighborStates.contains(1)) { // if there are empty cells and no fish, then the shark will starve
          sharkStarve++;
          if (sharkStarve == 3) {
            setFutureStateValue(0); // Shark dies
            sharkStarve = 0;
          }
          else {
            setFutureStateValue(2); // Shark stays alive
          }
        }
        else if (neighborStates.contains(1)) { // if there is a fish, then the shark will eat it
          sharkTurns++;
          if (sharkTurns == 3) { // if the shark has been alive for 3 turns, then it will breed
            setFutureStateValue(2);
            sharkTurns = 0;
          } else {
            setFutureStateValue(2); // Needs to swap with a water cell
          }
        }
        else {
          setFutureStateValue(2);
        }
      }
    }
  }
}
