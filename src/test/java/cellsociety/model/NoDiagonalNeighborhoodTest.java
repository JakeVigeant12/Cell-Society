package cellsociety.model;

import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.model.neighborhoods.NoDiagonalNeighborhood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoDiagonalNeighborhoodTest {
  @Test
  void holisticTest(){
    Neighborhood tester2 = new NoDiagonalNeighborhood();
    assertFalse(tester2.countNeighbor(0));
    assertTrue(tester2.countNeighbor(1));
    assertFalse(tester2.countNeighbor(2));
    assertTrue(tester2.countNeighbor(3));
    assertTrue(tester2.countNeighbor(4));
    assertFalse(tester2.countNeighbor(5));
    assertTrue(tester2.countNeighbor(6));
    assertFalse(tester2.countNeighbor(7));
  }
}
