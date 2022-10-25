package cellsociety.model.neighborhoods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompleteNeighborhood extends Neighborhood
{
  public CompleteNeighborhood() {
    super();
    neighborHood = new ArrayList<>(Arrays.asList(true,true,true,true,true,true,true, true));
  }
}