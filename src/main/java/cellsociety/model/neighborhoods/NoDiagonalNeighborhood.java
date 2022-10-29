package cellsociety.model.neighborhoods;

import java.util.ArrayList;
import java.util.Arrays;

public class NoDiagonalNeighborhood extends Neighborhood{

  /**
   * Constructor for NoDiagonalNeighborhood class
   */
  public NoDiagonalNeighborhood() {
    super();
    neighborHood = new ArrayList<>(Arrays.asList(false,true,false,true,true,false,true,false));
  }

}
