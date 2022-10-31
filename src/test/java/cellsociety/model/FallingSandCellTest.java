package cellsociety.model;

import cellsociety.model.cells.FallingSandCell;
import org.junit.jupiter.api.Test;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FallingSandCellTest {

  int state = 1;
  Point id = new Point(0, 0);
  FallingSandCell c = new FallingSandCell(state, id);


  @Test
  void testCellBasics() {
    assertEquals(state, c.getCurrentState());
    assertEquals(id, c.getId());
  }

  @Test
  void testSetFutureState() {
    c.setFutureStateValue(2);
    assertEquals(2, c.getFutureState());
  }

  @Test
  void testNeighborsGood() {
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    FallingSandCell c2 = new FallingSandCell(0, new Point(1, 0));
    FallingSandCell c3 = new FallingSandCell(1, new Point(1, 0));
    FallingSandCell c4 = new FallingSandCell(0, new Point(1, 0));
    FallingSandCell c5 = new FallingSandCell(1, new Point(0, 0));
    FallingSandCell c6 = new FallingSandCell(0, new Point(1, 0));
    FallingSandCell c7 = new FallingSandCell(1, new Point(1, 0));
    FallingSandCell c8 = new FallingSandCell(0, new Point(1, 0));
    c.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

    assertEquals(0, c.getFutureState());
  }

  @Test
  void testNeighborsBad() {
    FallingSandCell c1 = new FallingSandCell(23124, new Point(0, 0));
    FallingSandCell c2 = new FallingSandCell(13, new Point(1, 0));
    FallingSandCell c3 = new FallingSandCell(2132, new Point(1, 0));
    FallingSandCell c4 = new FallingSandCell(213, new Point(1, 0));
    FallingSandCell c5 = new FallingSandCell(23124, new Point(0, 0));
    FallingSandCell c6 = new FallingSandCell(13, new Point(1, 0));
    FallingSandCell c7 = new FallingSandCell(2132, new Point(1, 0));
    FallingSandCell c8 = new FallingSandCell(213, new Point(1, 0));

    c.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

    assertEquals(0, c.getFutureState());
  }

  @Test
  void testNeighborStates() {
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    FallingSandCell c2 = new FallingSandCell(0, new Point(1, 0));
    FallingSandCell c3 = new FallingSandCell(1, new Point(1, 0));
    FallingSandCell c4 = new FallingSandCell(0, new Point(1, 0));

    List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

    assertEquals(neighborStates, List.of(1, 0, 1, 0));
  }
@Test
  void testRulesUpperSand(){
  FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
  c1.rulesUPPERSAND();
  assertEquals(c1.getFutureState(),1);
}
  @Test
  void testRulesLowerSand(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesLOWERSAND();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void testRulesLeftSand(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesLEFTSAND();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void testRulesRightSand(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesRIGHTSAND();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void testRulesUpperWater(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesUPPERWATER();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void testRulesLowerWater(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesLOWERWATER();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void testRulesLeftWater(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesLEFTWATER();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void testRulesRightWater(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesRIGHTWATER();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void testRulesUpperEmpty(){
    FallingSandCell c1 = new FallingSandCell(1, new Point(0, 0));
    c1.rulesUPPEREMPTY();
    assertEquals(c1.getFutureState(),2);
  }

}
