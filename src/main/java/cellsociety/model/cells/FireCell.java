package cellsociety.model.cells;

import java.awt.*;
import java.util.List;
import java.util.Properties;

public class FireCell extends Cell {

  private int turns;
  private double myProbCatch;


  /**
   * Constructor for FireCell class
   * @param state is the state of the cell
   * @param id is the id of the cell
   */
  public FireCell(int state, Point id, double parameter){
    super(state, id);
    turns = 0;
    double probCatch = parameter;
    if (probCatch > 1){
      throw new IllegalArgumentException("Probability of catching fire must be between 0 and 1");
    }
    myProbCatch = probCatch;
  }

  public double getMyProbCatch() {
    return myProbCatch;
  }
  public void updateTurns(){
    turns++;
  }
  public int getTurns(){
    return turns;
  }
}
