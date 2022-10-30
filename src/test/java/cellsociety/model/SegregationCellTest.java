package cellsociety.model;

import cellsociety.model.cells.SegregationCell;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SegregationCellTest {
    int state = 1;
    Point id = new Point(0, 0);
    Double threshold = 0.5;
    SegregationCell c = new SegregationCell(state, id, threshold);


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
    void testNeighborsAgent1Stay () {
        SegregationCell mainAgent = new SegregationCell(1, new Point(0, 0), 0.25);
        SegregationCell c1 = new SegregationCell(1, new Point(0, 0), 0.5);
        SegregationCell c2 = new SegregationCell(1, new Point(1, 0), 0.5);
        SegregationCell c3 = new SegregationCell(0, new Point(1, 1), 0.5);
        SegregationCell c4 = new SegregationCell(0, new Point(2, 1), 0.5);
        SegregationCell c5 = new SegregationCell(0, new Point(0, 0), 0.5);
        SegregationCell c6 = new SegregationCell(0, new Point(1, 0), 0.5);
        SegregationCell c7 = new SegregationCell(0, new Point(1, 1), 0.5);
        SegregationCell c8 = new SegregationCell(0, new Point(2, 1), 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(1, mainAgent.getFutureState());
        assertEquals(false, mainAgent.getWantsToMove());
    }

    @Test
    void testNeighborsAgent1Leave () {
        SegregationCell mainAgent = new SegregationCell(1, new Point(0, 0), 0.25);
        SegregationCell c1 = new SegregationCell(1, new Point(0, 0), 0.5);
        SegregationCell c2 = new SegregationCell(0, new Point(1, 0), 0.5);
        SegregationCell c3 = new SegregationCell(0, new Point(1, 1), 0.5);
        SegregationCell c4 = new SegregationCell(0, new Point(2, 1), 0.5);
        SegregationCell c5 = new SegregationCell(2, new Point(0, 0), 0.5);
        SegregationCell c6 = new SegregationCell(2, new Point(1, 0), 0.5);
        SegregationCell c7 = new SegregationCell(2, new Point(1, 1), 0.5);
        SegregationCell c8 = new SegregationCell(2, new Point(2, 1), 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(1, mainAgent.getFutureState());
        assertEquals(true, mainAgent.getWantsToMove());
    }

    @Test
    void testNeighborsAgent2Stay () {
        SegregationCell mainAgent = new SegregationCell(2, new Point(0, 0), 0.5);
        SegregationCell c1 = new SegregationCell(2, new Point(0, 0), 0.5);
        SegregationCell c2 = new SegregationCell(2, new Point(1, 0), 0.5);
        SegregationCell c3 = new SegregationCell(2, new Point(1, 1), 0.5);
        SegregationCell c4 = new SegregationCell(2, new Point(2, 1), 0.5);
        SegregationCell c5 = new SegregationCell(2, new Point(0, 0), 0.5);
        SegregationCell c6 = new SegregationCell(1, new Point(1, 0), 0.5);
        SegregationCell c7 = new SegregationCell(0, new Point(1, 1), 0.5);
        SegregationCell c8 = new SegregationCell(0, new Point(2, 1), 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(2, mainAgent.getFutureState());
        assertEquals(false, mainAgent.getWantsToMove());

    }

    @Test
    void testNeighborsAgent2Leave () {
        SegregationCell mainAgent = new SegregationCell(2, new Point(0, 0), 0.5);
        SegregationCell c1 = new SegregationCell(2, new Point(0, 0), 0.5);
        SegregationCell c2 = new SegregationCell(2, new Point(1, 0), 0.5);
        SegregationCell c3 = new SegregationCell(1, new Point(1, 1), 0.5);
        SegregationCell c4 = new SegregationCell(0, new Point(2, 1), 0.5);
        SegregationCell c5 = new SegregationCell(1, new Point(0, 0), 0.5);
        SegregationCell c6 = new SegregationCell(1, new Point(1, 0), 0.5);
        SegregationCell c7 = new SegregationCell(2, new Point(1, 1), 0.5);
        SegregationCell c8 = new SegregationCell(0, new Point(2, 1), 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(2, mainAgent.getFutureState());
        assertEquals(true, mainAgent.getWantsToMove());
    }

    @Test
    void testNeighborsBad () {
        SegregationCell c1 = new SegregationCell(23124, new Point(0, 0), 1);
        SegregationCell c2 = new SegregationCell(13, new Point(1, 0), 1);
        SegregationCell c3 = new SegregationCell(2132, new Point(1, 1), 1);
        SegregationCell c4 = new SegregationCell(213, new Point(2, 1), 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, c.getFutureState());
        assertEquals(true, c.getWantsToMove());
    }

    @Test
    void testNeighborStates () {
        SegregationCell c1 = new SegregationCell(1, new Point(0, 0), 1);
        SegregationCell c2 = new SegregationCell(0, new Point(1, 0), 1);
        SegregationCell c3 = new SegregationCell(1, new Point(1, 1), 1);
        SegregationCell c4 = new SegregationCell(0, new Point(2, 1), 1);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }

    @Test
    void testThreshold () {
        SegregationCell mainCell = new SegregationCell(2, new Point(2, 2), 0);
        SegregationCell c1 = new SegregationCell(1, new Point(0, 0), 0);
        SegregationCell c2 = new SegregationCell(0, new Point(1, 0), 0);
        SegregationCell c3 = new SegregationCell(0, new Point(1, 1), 0);
        SegregationCell c4 = new SegregationCell(0, new Point(2, 1), 0);

        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, mainCell.getFutureState());
    }


}
