package cellsociety.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cellsociety.model.neighborhoods.CompleteNeighborhood;
import cellsociety.model.neighborhoods.Neighborhood;
import org.junit.jupiter.api.Test;

public class CompleteNeighborhoodTest {
  @Test
  void holisticTest(){
    Neighborhood tester1 = new CompleteNeighborhood();
    assertEquals(tester1.countNeighbor(0),true);
    assertEquals(tester1.countNeighbor(1),true);
    assertEquals(tester1.countNeighbor(2),true);
    assertEquals(tester1.countNeighbor(3),true);
    assertEquals(tester1.countNeighbor(4),true);
    assertEquals(tester1.countNeighbor(5),true);
    assertEquals(tester1.countNeighbor(6),true);
    assertEquals(tester1.countNeighbor(7),true);

  }
}
