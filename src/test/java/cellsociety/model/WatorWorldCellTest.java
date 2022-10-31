package cellsociety.model;

import cellsociety.model.cells.WatorWorldCell;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatorWorldCellTest {

  int state = 1;
  Point id = new Point(0, 0);
  WatorWorldCell c = new WatorWorldCell(state, id);


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
  void testNeighborsFishesWithFishesMove() {
    WatorWorldCell fish = new WatorWorldCell(1, new Point(0, 0));
    WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
    WatorWorldCell c2 = new WatorWorldCell(1, new Point(1, 0));
    WatorWorldCell c3 = new WatorWorldCell(0, new Point(1, 1));
    WatorWorldCell c4 = new WatorWorldCell(0, new Point(2, 1));

    fish.setFutureState(List.of(c1, c2, c3, c4));

    assertEquals(1, fish.getFutureState());
  }

  @Test
  void testNeighborsFishesWithFishesStay() {
    WatorWorldCell fish = new WatorWorldCell(1, new Point(0, 0));
    WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
    WatorWorldCell c2 = new WatorWorldCell(1, new Point(1, 0));
    WatorWorldCell c3 = new WatorWorldCell(1, new Point(1, 1));
    WatorWorldCell c4 = new WatorWorldCell(1, new Point(2, 1));

    fish.setFutureState(List.of(c1, c2, c3, c4));

    assertEquals(1, fish.getFutureState());
  }

  @Test
  void testNeighborsShark() {
    WatorWorldCell shark = new WatorWorldCell(2, new Point(0, 0));
    WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
    WatorWorldCell c2 = new WatorWorldCell(1, new Point(1, 0));
    WatorWorldCell c3 = new WatorWorldCell(1, new Point(1, 1));
    WatorWorldCell c4 = new WatorWorldCell(1, new Point(2, 1));

    shark.setFutureState(List.of(c1, c2, c3, c4));

    assertEquals(2, shark.getFutureState());
  }

  @Test
  void testNeighborsSharkNoEat() {
    WatorWorldCell shark = new WatorWorldCell(2, new Point(0, 0));
    WatorWorldCell c1 = new WatorWorldCell(2, new Point(1, 0));
    WatorWorldCell c2 = new WatorWorldCell(2, new Point(1, 0));
    WatorWorldCell c3 = new WatorWorldCell(2, new Point(1, 1));
    WatorWorldCell c4 = new WatorWorldCell(2, new Point(2, 1));

    shark.setFutureState(List.of(c1, c2, c3, c4));

    assertEquals(2, shark.getFutureState());

  }

  @Test
  void testNeighborStates() {
    WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
    WatorWorldCell c2 = new WatorWorldCell(0, new Point(1, 0));
    WatorWorldCell c3 = new WatorWorldCell(1, new Point(1, 1));
    WatorWorldCell c4 = new WatorWorldCell(0, new Point(2, 1));

    List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

    assertEquals(neighborStates, List.of(1, 0, 1, 0));
  }

  @Test
  void testResetState(){
    WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
    c1.resetStateParameters();
    assertEquals(0,c1.getSharkTurns());
    assertEquals(0,c1.getFishTurns());
  }
@Test
  void setFishStateTest(){
  WatorWorldCell c1 = new WatorWorldCell(0, new Point(0, 0));
  c1.setFISH();
  assertEquals(c1.getFutureState(),1);
}
  @Test
  void setWaterStateTest(){
    WatorWorldCell c1 = new WatorWorldCell(2, new Point(0, 0));
    c1.setWATER();
    assertEquals(c1.getFutureState(),0);
  }
  @Test
  void setSharkStateTest(){

    WatorWorldCell c1 = new WatorWorldCell(2, new Point(0, 0));
    WatorWorldCell c2 = new WatorWorldCell(0, new Point(1, 0));
    WatorWorldCell c3 = new WatorWorldCell(1, new Point(1, 1));
    WatorWorldCell c4 = new WatorWorldCell(0, new Point(2, 1));

    List<Integer> neighborStates = c.getNeighborStates(List.of(c2, c3, c4));

    c.setSHARK();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void setIntermediateSharkStateTest(){
    WatorWorldCell c1 = new WatorWorldCell(0, new Point(0, 0));
    c1.setINTERMEDIATESHARK();
    assertEquals(c1.getFutureState(),2);
  }
  @Test
  void fishReadyToBreedTest(){
    WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));

    assertEquals(c1.readyToReproduce(),false);
    c1.setFishTurns(3);
    assertEquals(c1.readyToReproduce(),true);
  }
}
