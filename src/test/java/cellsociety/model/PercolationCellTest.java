package cellsociety.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercolationCellTest {
    int state = 0;
    int id = 0;
    PercolationCell c = new PercolationCell(state, id);


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
        PercolationCell c1 = new PercolationCell(1, 0);
        PercolationCell c2 = new PercolationCell(2, 1);
        PercolationCell c3 = new PercolationCell(2, 2);
        PercolationCell c4 = new PercolationCell(0, 3);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, c.getFutureState());
    }

    @Test
    void testNeighborsWall () {
        PercolationCell mainCell = new PercolationCell(2, 0);
        PercolationCell c1 = new PercolationCell(1, 0);
        PercolationCell c2 = new PercolationCell(2, 1);
        PercolationCell c3 = new PercolationCell(2, 2);
        PercolationCell c4 = new PercolationCell(0, 3);

        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, mainCell.getFutureState());
    }

    @Test
    void testNeighborsPercolated () {
        PercolationCell mainCell = new PercolationCell(1, 0);
        PercolationCell c1 = new PercolationCell(1, 0);
        PercolationCell c2 = new PercolationCell(2, 1);
        PercolationCell c3 = new PercolationCell(2, 2);
        PercolationCell c4 = new PercolationCell(0, 3);

        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, mainCell.getFutureState());
    }

    @Test
    void testNeighborsBad () {
        PercolationCell c1 = new PercolationCell(23124, 0);
        PercolationCell c2 = new PercolationCell(13, 1);
        PercolationCell c3 = new PercolationCell(2132, 2);
        PercolationCell c4 = new PercolationCell(213, 3);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(0, c.getFutureState());
    }

    @Test
    void testNeighborStates () {
        PercolationCell c1 = new PercolationCell(1, 0);
        PercolationCell c2 = new PercolationCell(0, 1);
        PercolationCell c3 = new PercolationCell(1, 2);
        PercolationCell c4 = new PercolationCell(0, 3);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }


}
