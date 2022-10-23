package cellsociety.model;

import cellsociety.model.cells.WaTorWorldCell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaTorWorldCellTest {
    int state = 1;
    int id = 0;
    WaTorWorldCell c = new WaTorWorldCell(state, id);


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
        WaTorWorldCell fish = new WaTorWorldCell(1, 0);
        WaTorWorldCell c1 = new WaTorWorldCell(1, 0);
        WaTorWorldCell c2 = new WaTorWorldCell(1, 1);
        WaTorWorldCell c3 = new WaTorWorldCell(0, 2);
        WaTorWorldCell c4 = new WaTorWorldCell(0, 3);

        fish.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, fish.getFutureState());
        assertEquals(true, fish.getWantsToMove());
    }

    @Test
    void testNeighborsFishesWithFishesStay () {
        WaTorWorldCell fish = new WaTorWorldCell(1, 0);
        WaTorWorldCell c1 = new WaTorWorldCell(1, 0);
        WaTorWorldCell c2 = new WaTorWorldCell(1, 1);
        WaTorWorldCell c3 = new WaTorWorldCell(1, 2);
        WaTorWorldCell c4 = new WaTorWorldCell(1, 3);

        fish.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(1, fish.getFutureState());
        assertEquals(false, fish.getWantsToMove());
    }

    @Test
    void testNeighborsShark () {
        WaTorWorldCell shark = new WaTorWorldCell(2, 0);
        WaTorWorldCell c1 = new WaTorWorldCell(1, 0);
        WaTorWorldCell c2 = new WaTorWorldCell(1, 1);
        WaTorWorldCell c3 = new WaTorWorldCell(1, 2);
        WaTorWorldCell c4 = new WaTorWorldCell(1, 3);

        shark.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(2, shark.getFutureState());
        assertEquals(true, shark.getWantsToMove());
    }

    @Test
    void testNeighborsSharkNoEat () {
        WaTorWorldCell shark = new WaTorWorldCell(2, 0);
        WaTorWorldCell c1 = new WaTorWorldCell(2, 0);
        WaTorWorldCell c2 = new WaTorWorldCell(2, 1);
        WaTorWorldCell c3 = new WaTorWorldCell(2, 2);
        WaTorWorldCell c4 = new WaTorWorldCell(2, 3);

        shark.setFutureState(List.of(c1, c2, c3, c4));

        assertEquals(3, shark.getFutureState());
        assertEquals(false, shark.getWantsToMove());
        assertEquals(0, shark.getSharkStarve());
    }

    @Test
    void testNeighborStates () {
        WaTorWorldCell c1 = new WaTorWorldCell(1, 0);
        WaTorWorldCell c2 = new WaTorWorldCell(0, 1);
        WaTorWorldCell c3 = new WaTorWorldCell(1, 2);
        WaTorWorldCell c4 = new WaTorWorldCell(0, 3);

        List<Integer> neighborStates = c.getNeighborStates(List.of(c1, c2, c3, c4));

        assertEquals(neighborStates, List.of(1, 0, 1, 0));
    }

}
