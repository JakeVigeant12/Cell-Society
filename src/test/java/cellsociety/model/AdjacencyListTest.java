package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.CompleteNeighborhood;
import cellsociety.view.GridWrapper;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdjacencyListTest {
    private CompleteNeighborhood full = new CompleteNeighborhood();
    private GridWrapper grid = new GridWrapper(1,2);
    private Cell c1 = new Cell(1, new Point(0, 0));
    private Cell c2 = new Cell(1, new Point(1, 0));
    private Cell c3 = new Cell(1, new Point(0, 1));
    private AdjacencyList adjList = new AdjacencyList(grid, Map.of(c1.getId(), c1, c2.getId(), c2), full);

    @Test
    void testGetCells () {
        assertEquals(true, adjList.getCells().contains(c1) && adjList.getCells().contains(c2));
    }

    @Test
    void testGetNeighbors () {
        assertEquals(true, adjList.getNeighbors(c1).contains(c2));
    }

    @Test
    void testGetNeighborsOpposite () {
        assertEquals(true, adjList.getNeighbors(c2).contains(c1));
    }

    @Test
    void testGetNeighborsNotTrue () {
        assertEquals(false, adjList.getNeighbors(c2).contains(c3));
    }

    @Test
    void testGetNeighborsFullNeighborhood () {
        assertEquals(8, adjList.getNeighbors(c1).size());
    }
}
