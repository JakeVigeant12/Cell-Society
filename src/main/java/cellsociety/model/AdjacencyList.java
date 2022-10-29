package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;

import java.util.*;

public class AdjacencyList {
    Map<Cell, List<Cell>> adjacencyList;

    public AdjacencyList(GridWrapper inputLayout, Map<Integer, Cell> cells, Neighborhood simulationNeighbors) {
        adjacencyList = new HashMap<>();
        int currId = 0;
        for (int i = 0; i < inputLayout.getRowCount(); i++) {
            for (int j = 0; j < inputLayout.getRowSize(0); j++) {
                List<Cell> neighbors = new ArrayList<>();
                currId++;
                Cell currentCell = cells.get(currId);
                adjacencyList.putIfAbsent(currentCell, neighbors);
                createNeighborhood(i - 1, j - 1, inputLayout, currId - inputLayout.getRowSize(0) - 1, simulationNeighbors, 0, adjacencyList, currentCell, cells);
                createNeighborhood(i - 1, j, inputLayout, currId - inputLayout.getRowSize(0), simulationNeighbors, 1, adjacencyList, currentCell, cells);
                createNeighborhood(i - 1, j + 1, inputLayout, currId - inputLayout.getRowSize(0) + 1, simulationNeighbors, 2, adjacencyList, currentCell, cells);
                createNeighborhood(i, j - 1, inputLayout, currId - 1, simulationNeighbors, 3, adjacencyList, currentCell, cells);
                createNeighborhood(i, j + 1, inputLayout, currId + 1, simulationNeighbors, 4, adjacencyList, currentCell, cells);
                createNeighborhood(i + 1, j - 1, inputLayout, currId + inputLayout.getRowSize(0) - 1, simulationNeighbors, 5, adjacencyList, currentCell, cells);
                createNeighborhood(i + 1, j, inputLayout, currId + inputLayout.getRowSize(0), simulationNeighbors, 6, adjacencyList, currentCell, cells);
                createNeighborhood(i + 1, j + 1, inputLayout, currId + inputLayout.getRowSize(0) + 1, simulationNeighbors, 7, adjacencyList, currentCell, cells);
            }
        }
    }

    private void createNeighborhood(int i, int j, GridWrapper gridParsing, int currId, Neighborhood simulationNeighbors, int neighborNumber, Map<Cell, List<Cell>> adjacencyList, Cell currentCell, Map<Integer, Cell> myCells) {
        if(isInBounds(i, j, gridParsing)){
            if(simulationNeighbors.countNeighbor(neighborNumber)) {
                adjacencyList.get(currentCell).add(myCells.get(currId));
            }
        }
    }

    private static boolean isInBounds(int row, int col, GridWrapper gridWrapper){
        return (row >= 0 && row < gridWrapper.getRowCount()) && (col >= 0 && col < gridWrapper.getRowSize(0));
    }

    public List<Cell> getCells() {
        return adjacencyList.keySet().stream().toList();
    }

    public List<Cell> getNeighbors(Cell cell) {
        return adjacencyList.get(cell);
    }
}
