package cellsociety.model;

import cellsociety.model.cells.WatorWorldCell;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatorWorldCellTest {
    int state = 1;
    Point id = new Point(0, 0);
    WatorWorldCell c = new WatorWorldCell(state, id);


    @Test
    void testCellBasics () {
        assertEquals(state, c.getCurrentState());
        assertEquals(id, c.getId());
    }

    @Test
    void testSetFutureState () {
        c.setFutureStateValue(2);
        assertEquals(2, c.getFutureState());
    }



    @Test
    void testNeighborsFishesWithFishesMove () {
        WatorWorldCell fish = new WatorWorldCell(1, new Point(0, 0));
        WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
        WatorWorldCell c2 = new WatorWorldCell(1, new Point(1, 0));
        WatorWorldCell c3 = new WatorWorldCell(0, new Point(1, 1));
        WatorWorldCell c4 = new WatorWorldCell(0, new Point(2, 1));

        fish.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, fish.getFutureState());
    }

    @Test
    void testNeighborsFishesWithFishesStay () {
        WatorWorldCell fish = new WatorWorldCell(1, new Point(0, 0));
        WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
        WatorWorldCell c2 = new WatorWorldCell(1, new Point(1, 0));
        WatorWorldCell c3 = new WatorWorldCell(1, new Point(1, 1));
        WatorWorldCell c4 = new WatorWorldCell(1, new Point(2, 1));

        fish.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, fish.getFutureState());
    }

    @Test
    void testNeighborsShark () {
        WatorWorldCell shark = new WatorWorldCell(2, new Point(0, 0));
        WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
        WatorWorldCell c2 = new WatorWorldCell(1, new Point(1, 0));
        WatorWorldCell c3 = new WatorWorldCell(1, new Point(1, 1));
        WatorWorldCell c4 = new WatorWorldCell(1, new Point(2, 1));

        shark.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, shark.getFutureState());
    }

    @Test
    void testNeighborsSharkNoEat () {
        WatorWorldCell shark = new WatorWorldCell(2, new Point(0, 0));
        WatorWorldCell c1 = new WatorWorldCell(2, new Point(1, 0));
        WatorWorldCell c2 = new WatorWorldCell(2, new Point(1, 0));
        WatorWorldCell c3 = new WatorWorldCell(2, new Point(1, 1));
        WatorWorldCell c4 = new WatorWorldCell(2, new Point(2, 1));

        shark.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, shark.getFutureState());

    }

    @Test
    void testNeighborStates () {
        WatorWorldCell c1 = new WatorWorldCell(1, new Point(0, 0));
        WatorWorldCell c2 = new WatorWorldCell(0, new Point(1, 0));
        WatorWorldCell c3 = new WatorWorldCell(1, new Point(1, 1));
        WatorWorldCell c4 = new WatorWorldCell(0, new Point(2, 1));

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }

}
