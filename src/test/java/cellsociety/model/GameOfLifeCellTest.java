package cellsociety.model;

import cellsociety.model.cells.GameOfLifeCell;
import org.junit.jupiter.api.Test;

import java.awt.Point;;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeCellTest {

  int state = 1;
  Point id = new Point(0, 0);
  GameOfLifeCell c = new GameOfLifeCell(state, id);


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
    GameOfLifeCell c1 = new GameOfLifeCell(1, new Point(0, 0));
    GameOfLifeCell c2 = new GameOfLifeCell(0, new Point(1, 0));
    GameOfLifeCell c3 = new GameOfLifeCell(1, new Point(1, 0));
    GameOfLifeCell c4 = new GameOfLifeCell(0, new Point(1, 0));

    c.setFutureState(List.of(c1, c2, c3, c4));

    assertEquals(1, c.getFutureState());
  }

  @Test
  void testNeighborsBad() {
    GameOfLifeCell c1 = new GameOfLifeCell(23124, new Point(0, 0));
    GameOfLifeCell c2 = new GameOfLifeCell(13, new Point(1, 0));
    GameOfLifeCell c3 = new GameOfLifeCell(2132, new Point(1, 0));
    GameOfLifeCell c4 = new GameOfLifeCell(213, new Point(1, 0));

    c.setFutureState(List.of(c1, c2, c3, c4));

    assertEquals(0, c.getFutureState());
  }

  @Test
  void testNeighborStates() {
    GameOfLifeCell c1 = new GameOfLifeCell(1, new Point(0, 0));
    GameOfLifeCell c2 = new GameOfLifeCell(0, new Point(1, 0));
    GameOfLifeCell c3 = new GameOfLifeCell(1, new Point(1, 0));
    GameOfLifeCell c4 = new GameOfLifeCell(0, new Point(1, 0));

    List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

    assertEquals(neighborStates, List.of(1, 0, 1, 0));
  }


}
