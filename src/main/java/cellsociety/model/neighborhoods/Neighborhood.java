package cellsociety.model.neighborhoods;

import java.util.List;

public class Neighborhood {
  protected List<Boolean>neighborHood;
  public Neighborhood(){

  }
  public boolean countNeighbor(int neighborNumber){
    return  neighborHood.get(neighborNumber);
  }

}
