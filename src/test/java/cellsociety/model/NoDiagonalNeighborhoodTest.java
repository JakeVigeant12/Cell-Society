package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.model.neighborhoods.NoDiagonalNeighborhood;
import org.junit.jupiter.api.Test;

public class NoDiagonalNeighborhoodTest {
  @Test
  void holisticTest(){
    Neighborhood tester2 = new NoDiagonalNeighborhood();
    assertEquals(tester2.countNeighbor(0),false);
    assertEquals(tester2.countNeighbor(1),true);
    assertEquals(tester2.countNeighbor(2),false);
    assertEquals(tester2.countNeighbor(3),true);
    assertEquals(tester2.countNeighbor(4),true);
    assertEquals(tester2.countNeighbor(5),false);
    assertEquals(tester2.countNeighbor(6),true);
    assertEquals(tester2.countNeighbor(7),false);
  }
}
