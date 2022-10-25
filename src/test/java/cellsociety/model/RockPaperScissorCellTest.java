package cellsociety.model;

import cellsociety.model.cells.PercolationCell;
import cellsociety.model.cells.RockPaperScissorCell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RockPaperScissorCellTest {
    int state = 0;
    int id = 0;
    RockPaperScissorCell c = new RockPaperScissorCell(state, id);


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
        RockPaperScissorCell c1 = new RockPaperScissorCell(1, 0);
        RockPaperScissorCell c2 = new RockPaperScissorCell(2, 1);
        RockPaperScissorCell c3 = new RockPaperScissorCell(0, 2);
        RockPaperScissorCell c4 = new RockPaperScissorCell(0, 3);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, c.getFutureState());
    }

    @Test
    void testNeighborsScissors () {
        RockPaperScissorCell mainCell = new RockPaperScissorCell(2, 0);
        RockPaperScissorCell c1 = new RockPaperScissorCell(1, 0);
        RockPaperScissorCell c2 = new RockPaperScissorCell(1, 1);
        RockPaperScissorCell c3 = new RockPaperScissorCell(0, 2);
        RockPaperScissorCell c4 = new RockPaperScissorCell(0, 3);

        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(0, mainCell.getFutureState());
    }

    @Test
    void testNeighborsPaper () {
        RockPaperScissorCell mainCell = new RockPaperScissorCell(1, 0);
        RockPaperScissorCell c1 = new RockPaperScissorCell(1, 0);
        RockPaperScissorCell c2 = new RockPaperScissorCell(2, 1);
        RockPaperScissorCell c3 = new RockPaperScissorCell(2, 2);
        RockPaperScissorCell c4 = new RockPaperScissorCell(1, 3);


        mainCell.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, mainCell.getFutureState());
    }

    @Test
    void testNeighborsBad () {
        RockPaperScissorCell c1 = new RockPaperScissorCell(23124, 0);
        RockPaperScissorCell c2 = new RockPaperScissorCell(13, 1);
        RockPaperScissorCell c3 = new RockPaperScissorCell(2132, 2);
        RockPaperScissorCell c4 = new RockPaperScissorCell(213, 3);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(0, c.getFutureState());
    }

    @Test
    void testNeighborStates () {
        RockPaperScissorCell c1 = new RockPaperScissorCell(1, 0);
        RockPaperScissorCell c2 = new RockPaperScissorCell(0, 1);
        RockPaperScissorCell c3 = new RockPaperScissorCell(1, 2);
        RockPaperScissorCell c4 = new RockPaperScissorCell(0, 3);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }
}
