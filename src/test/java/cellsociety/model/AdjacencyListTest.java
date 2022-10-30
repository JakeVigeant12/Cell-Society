package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.CompleteNeighborhood;
import cellsociety.view.GridWrapper;
import org.junit.jupiter.api.Test;

import java.awt.Point;;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListTest {
    private CompleteNeighborhood full = new CompleteNeighborhood();
    private GridWrapper grid = new GridWrapper(3,3);
    private Cell c1 = new Cell(1, new Point(0, 0));
    private Cell c2 = new Cell(1, new Point(1, 0));
    private Cell c3 = new Cell(1, new Point(2, 0));
    private Cell c4 = new Cell(1, new Point(0, 1));
    private Cell c5 = new Cell(1, new Point(1, 1));
    private Cell c6 = new Cell(1, new Point(2, 1));
    private Cell c7 = new Cell(1, new Point(0, 2));
    private Cell c8 = new Cell(1, new Point(1, 2));
    private Cell c9 = new Cell(1, new Point(2, 2));
    private AdjacencyList adjList = new AdjacencyList(grid, Map.of(c1.getId(), c1, c2.getId(), c2, c3.getId(), c3, c4.getId(),c4, c5.getId(), c5, c6.getId(), c6, c7.getId(), c7, c8.getId(), c8, c9.getId(), c9), full);

    @Test
    void testGetCells () {
        assertTrue(adjList.getCells().contains(c1) && adjList.getCells().contains(c2));
    }

    @Test
    void testGetNeighbors () {
        assertTrue(adjList.getNeighbors(c1).contains(c2));
    }

    @Test
    void testGetNeighborsOpposite () {
        assertTrue(adjList.getNeighbors(c2).contains(c1));
    }

    @Test
    void testGetNeighborsNotTrue () {
        assertFalse(adjList.getNeighbors(c2).contains(c8));
    }

    @Test
    void testGetNeighborsFullNeighborhood () {
        assertEquals(8, adjList.getNeighbors(c5).size());
    }
}
