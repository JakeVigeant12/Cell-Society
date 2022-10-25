package cellsociety.model;

import cellsociety.model.cells.Cell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {
    int state = 1;
    int id = 0;
    Cell c = new Cell(state, id);


    @Test
    void testCellState () {
        assertEquals(state, c.getCurrentState());
    }
    @Test
    void testCellStateChange () {
        c.setFutureState(List.of());
        assertEquals(state, c.getFutureState());
    }
    @Test
    void testCellStateUpdate () {
        c.setFutureStateValue(32);
        c.updateState();
        assertEquals(32, c.getCurrentState());
    }

    @Test
    void testCellId () {
        assertEquals(id, c.getId());
    }

    @Test
    void testCellIdChange () {
        c.setId(32);
        assertEquals(32, c.getId());
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

        assertEquals(32, cell1.getCurrentState());
        assertEquals(64, cell2.getCurrentState());
    }

    @Test
    void testSwapCells () {
        Cell cell1 = new Cell(5234, 0);
        Cell cell2 = new Cell(2, 1);

        cell1.swapCells(cell2);

        assertEquals(2, cell1.getCurrentState());
        assertEquals(5234, cell2.getCurrentState());
    }


}
