package cellsociety.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchellingCellTest {
    int state = 1;
    int id = 0;
    double threshold = 0.5;
    SchellingCell c = new SchellingCell(state, id, threshold);


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
        SchellingCell mainAgent = new SchellingCell(1, 0, 0.25);
        SchellingCell c1 = new SchellingCell(1, 0, 0.5);
        SchellingCell c2 = new SchellingCell(1, 1, 0.5);
        SchellingCell c3 = new SchellingCell(0, 2, 0.5);
        SchellingCell c4 = new SchellingCell(0, 3, 0.5);
        SchellingCell c5 = new SchellingCell(0, 0, 0.5);
        SchellingCell c6 = new SchellingCell(0, 1, 0.5);
        SchellingCell c7 = new SchellingCell(0, 2, 0.5);
        SchellingCell c8 = new SchellingCell(0, 3, 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(1, mainAgent.getFutureState());
    }

    @Test
    void testNeighborsAgent1Leave () {
        SchellingCell mainAgent = new SchellingCell(1, 0, 0.25);
        SchellingCell c1 = new SchellingCell(1, 0, 0.5);
        SchellingCell c2 = new SchellingCell(0, 1, 0.5);
        SchellingCell c3 = new SchellingCell(0, 2, 0.5);
        SchellingCell c4 = new SchellingCell(0, 3, 0.5);
        SchellingCell c5 = new SchellingCell(2, 0, 0.5);
        SchellingCell c6 = new SchellingCell(2, 1, 0.5);
        SchellingCell c7 = new SchellingCell(2, 2, 0.5);
        SchellingCell c8 = new SchellingCell(2, 3, 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(3, mainAgent.getFutureState());
    }

    @Test
    void testNeighborsAgent2Stay () {
        SchellingCell mainAgent = new SchellingCell(2, 0, 0.5);
        SchellingCell c1 = new SchellingCell(2, 0, 0.5);
        SchellingCell c2 = new SchellingCell(2, 1, 0.5);
        SchellingCell c3 = new SchellingCell(2, 2, 0.5);
        SchellingCell c4 = new SchellingCell(2, 3, 0.5);
        SchellingCell c5 = new SchellingCell(2, 0, 0.5);
        SchellingCell c6 = new SchellingCell(1, 1, 0.5);
        SchellingCell c7 = new SchellingCell(0, 2, 0.5);
        SchellingCell c8 = new SchellingCell(0, 3, 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(2, mainAgent.getFutureState());
    }

    @Test
    void testNeighborsAgent2Leave () {
        SchellingCell mainAgent = new SchellingCell(2, 0, 0.5);
        SchellingCell c1 = new SchellingCell(2, 0, 0.5);
        SchellingCell c2 = new SchellingCell(2, 1, 0.5);
        SchellingCell c3 = new SchellingCell(1, 2, 0.5);
        SchellingCell c4 = new SchellingCell(0, 3, 0.5);
        SchellingCell c5 = new SchellingCell(1, 0, 0.5);
        SchellingCell c6 = new SchellingCell(1, 1, 0.5);
        SchellingCell c7 = new SchellingCell(2, 2, 0.5);
        SchellingCell c8 = new SchellingCell(0, 3, 0.5);

        mainAgent.setFutureState(List.of(c1, c2, c3, c4, c5, c6, c7, c8));

        assertEquals(4, mainAgent.getFutureState());
    }

    @Test
    void testNeighborsBad () {
        SchellingCell c1 = new SchellingCell(23124, 0, 1);
        SchellingCell c2 = new SchellingCell(13, 1, 1);
        SchellingCell c3 = new SchellingCell(2132, 2, 1);
        SchellingCell c4 = new SchellingCell(213, 3, 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(3, c.getFutureState());
    }

    @Test
    void testNeighborStates () {
        SchellingCell c1 = new SchellingCell(1, 0, 1);
        SchellingCell c2 = new SchellingCell(0, 1, 1);
        SchellingCell c3 = new SchellingCell(1, 2, 1);
        SchellingCell c4 = new SchellingCell(0, 3, 1);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }

    @Test
    void testThreshold () {
        SchellingCell mainCell = new SchellingCell(2, 5, 0);
        SchellingCell c1 = new SchellingCell(1, 0, 0);
        SchellingCell c2 = new SchellingCell(0, 1, 0);
        SchellingCell c3 = new SchellingCell(0, 2, 0);
        SchellingCell c4 = new SchellingCell(0, 3, 0);

        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, mainCell.getFutureState());
    }


}
