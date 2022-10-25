package cellsociety.model.neighborhoods;

import java.util.ArrayList;
import java.util.Arrays;

public class NoDiagonalNeighborhood extends Neighborhood{
  public NoDiagonalNeighborhood() {
    super();
    neighborHood = new ArrayList<>(Arrays.asList(false,true,false,true,true,false,true,false));
  }

}
