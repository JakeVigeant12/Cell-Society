package cellsociety.model;

import cellsociety.model.cells.FireCell;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireCellTest {
    int state = 1;
    Point id = new Point(0, 0);
    double probCatch = 1;
    FireCell c = new FireCell(state, id, probCatch);


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
    void testNeighborsGood () {
        FireCell c1 = new FireCell(1, new Point(0, 0), 1);
        FireCell c2 = new FireCell(0, new Point(1, 0), 1);
        FireCell c3 = new FireCell(2, new Point(1, 1), 1);
        FireCell c4 = new FireCell(0, new Point(2, 1), 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, c.getFutureState());
    }

    @Test
    void testNeighborsBad () {
        FireCell c1 = new FireCell(23124, new Point(0, 0), 1);
        FireCell c2 = new FireCell(13, new Point(1, 0), 1);
        FireCell c3 = new FireCell(2132, new Point(1, 1), 1);
        FireCell c4 = new FireCell(213, new Point(2, 1), 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, c.getFutureState());
    }

    @Test
    void testNeighborStates () {
        FireCell c1 = new FireCell(1, new Point(0, 0), 1);
        FireCell c2 = new FireCell(0, new Point(1, 0), 1);
        FireCell c3 = new FireCell(1, new Point(1, 1), 1);
        FireCell c4 = new FireCell(0, new Point(2, 1), 1);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }

    @Test
    void testProbCatch () {
        FireCell mainCell = new FireCell(2, new Point(2, 2), 0);
        FireCell c1 = new FireCell(2, new Point(0, 0), 1);
        FireCell c2 = new FireCell(0, new Point(1, 0), 1);
        FireCell c3 = new FireCell(2, new Point(1, 1), 1);
        FireCell c4 = new FireCell(0, new Point(2, 1), 1);
        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, mainCell.getFutureState());
    }

    @Test
    void testProbCatchAfter3Turns () {
        FireCell mainCell = new FireCell(2, new Point(2, 2), 0);
        FireCell c1 = new FireCell(2, new Point(0, 0), 1);
        FireCell c2 = new FireCell(0, new Point(1, 0), 1);
        FireCell c3 = new FireCell(2, new Point(1, 1), 1);
        FireCell c4 = new FireCell(0, new Point(2, 1), 1);

        mainCell.setFutureState(List.of(c1, c2, c3, c4));
        mainCell.setFutureState(List.of(c1, c2, c3, c4));
        mainCell.setFutureState(List.of(c1, c2, c3, c4));
        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(0, mainCell.getFutureState());
    }


}
