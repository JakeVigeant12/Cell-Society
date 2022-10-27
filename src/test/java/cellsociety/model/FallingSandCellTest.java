package cellsociety.model;

import cellsociety.model.cells.FallingSandCell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FallingSandCellTest {
    int state = 1;
    int id = 0;
    FallingSandCell c = new FallingSandCell(state, id);


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
        FallingSandCell c1 = new FallingSandCell(1, 0);
        FallingSandCell c2 = new FallingSandCell(0, 1);
        FallingSandCell c3 = new FallingSandCell(1, 1);
        FallingSandCell c4 = new FallingSandCell(0, 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, c.getFutureState());
    }

    @Test
    void testNeighborsBad () {
        FallingSandCell c1 = new FallingSandCell(23124, 0);
        FallingSandCell c2 = new FallingSandCell(13, 1);
        FallingSandCell c3 = new FallingSandCell(2132, 1);
        FallingSandCell c4 = new FallingSandCell(213, 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(0, c.getFutureState());
    }

    @Test
    void testNeighborStates () {
        FallingSandCell c1 = new FallingSandCell(1, 0);
        FallingSandCell c2 = new FallingSandCell(0, 1);
        FallingSandCell c3 = new FallingSandCell(1, 1);
        FallingSandCell c4 = new FallingSandCell(0, 1);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }


}
