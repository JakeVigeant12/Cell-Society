package cellsociety.model.neighborhoods;

import java.util.List;

public class Neighborhood {
  protected List<Boolean> neighborHood;

  /**
   * Constructor for Neighborhood class
   */
  public Neighborhood(){}

  /**
   * Method that counts the neighbors of a cell
   * @return neighborHood
   */
  public boolean countNeighbor(int neighborNumber){
    return neighborHood.get(neighborNumber);
  }

}
