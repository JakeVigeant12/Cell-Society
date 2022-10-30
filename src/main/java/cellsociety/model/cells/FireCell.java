package cellsociety.model.cells;

import java.awt.Point;;
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
  public FireCell(int state, Point id, double parameter) throws IllegalStateException{
    super(state, id);
    turns = 0;
    double probCatch = parameter;
    if (probCatch > 1){
      throw new IllegalStateException("probabilityError");
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
