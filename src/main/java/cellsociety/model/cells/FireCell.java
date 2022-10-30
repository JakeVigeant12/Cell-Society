package cellsociety.model.cells;

import java.awt.Point;;
import java.util.List;
import java.util.Properties;

public class FireCell extends Cell {

  private int turns;
  private double myProbCatch;

  private String PROBABILITY_ERROR_MESSAGE = "probabilityError";

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
      throw new IllegalArgumentException(PROBABILITY_ERROR_MESSAGE);
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
