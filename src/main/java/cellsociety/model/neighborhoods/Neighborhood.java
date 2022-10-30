package cellsociety.model.neighborhoods;

import java.util.List;

public class Neighborhood {
  private List<Boolean> neighborHood;

  public List<Boolean> getNeighborHood() {
    return neighborHood;
  }

  public void setNeighborHood(List<Boolean> neighborHood) {
    this.neighborHood = neighborHood;
  }

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
