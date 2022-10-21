package cellsociety.model;

import cellsociety.model.Cell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {
    int state = 1;
    int id = 0;
    Cell c = new Cell(state, id);


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
    void testNeighbors () {
        Cell c1 = new Cell(1, 0);
        Cell c2 = new Cell(0, 1);
        Cell c3 = new Cell(1, 1);
        Cell c4 = new Cell(0, 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(state, c.getFutureState());
    }

    @Test
    void testNeighborStates () {
        Cell c1 = new Cell(1, 0);
        Cell c2 = new Cell(0, 1);
        Cell c3 = new Cell(1, 1);
        Cell c4 = new Cell(0, 1);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }

    @Test
    void testSwapCellStates () {
        Cell cell1 = new Cell(64, 0);
        Cell cell2 = new Cell(32, 1);

        cell1.swapCellStates(cell2);

        assertEquals(64, cell1.getCurrentState());
        assertEquals(32, cell2.getCurrentState());

        assertEquals(32, cell1.getFutureState());
        assertEquals(64, cell2.getFutureState());
    }


}
