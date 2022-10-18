package cellsociety;

import cellsociety.model.GameOfLifeCell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeCellTest {
    int state = 1;
    int id = 0;
    GameOfLifeCell c = new GameOfLifeCell(state, id);


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
        GameOfLifeCell c1 = new GameOfLifeCell(1, 0);
        GameOfLifeCell c2 = new GameOfLifeCell(0, 1);
        GameOfLifeCell c3 = new GameOfLifeCell(1, 1);
        GameOfLifeCell c4 = new GameOfLifeCell(0, 1);

        c.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, c.getFutureState());
    }

    @Test
    void testNeighborStates () {
        GameOfLifeCell c1 = new GameOfLifeCell(1, 0);
        GameOfLifeCell c2 = new GameOfLifeCell(0, 1);
        GameOfLifeCell c3 = new GameOfLifeCell(1, 1);
        GameOfLifeCell c4 = new GameOfLifeCell(0, 1);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }


}
