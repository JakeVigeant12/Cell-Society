package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.model.neighborhoods.CompleteNeighborhood;
import cellsociety.model.neighborhoods.Neighborhood;
import org.junit.jupiter.api.Test;

public class CompleteNeighborhoodTest {

  @Test
  void holisticTest() {
    Neighborhood tester1 = new CompleteNeighborhood();
    assertTrue(tester1.countNeighbor(0));
    assertTrue(tester1.countNeighbor(1));
    assertTrue(tester1.countNeighbor(2));
    assertTrue(tester1.countNeighbor(3));
    assertTrue(tester1.countNeighbor(4));
    assertTrue(tester1.countNeighbor(5));
    assertTrue(tester1.countNeighbor(6));
    assertTrue(tester1.countNeighbor(7));

  }
}
