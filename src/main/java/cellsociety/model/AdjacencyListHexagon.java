package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdjacencyListHexagon extends AdjacencyList{
  public AdjacencyListHexagon(GridWrapper inputLayout, Map<Point, Cell> cells, Neighborhood simulationNeighbors) {
    super(inputLayout, cells, simulationNeighbors);
  }

  @Override
  protected void initializeNeighbors() {
    for (int i = 0; i < getRowCount(); i++) {
      for (int j = 0; j < getRowSize(); j++) {
        List<Cell> neighbors = new ArrayList<>();
        Cell currentCell = getCellMap().get(new Point(j, i));
        getAdjacencyList().putIfAbsent(currentCell, neighbors);
        if (i % 2 == 0)
          createEvenNeighbor(i, j, currentCell);
        else
          createOddNeighbor(i, j, currentCell);
      }
    }
  }

  private void createOddNeighbor(int i, int j, Cell currentCell) {
    createNeighborhood(new Point(j, i), new Point(j - 1, i - 1), getSimulationNeighbors(), 0,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j, i - 1), getSimulationNeighbors(), 1,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j - 1, i), getSimulationNeighbors(), 2,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j + 1, i), getSimulationNeighbors(), 3,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j - 1, i + 1), getSimulationNeighbors(), 4,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j, i + 1), getSimulationNeighbors(), 5,
      currentCell);
  }

  private void createEvenNeighbor(int i, int j, Cell currentCell) {
    createNeighborhood(new Point(j, i), new Point(j, i - 1), getSimulationNeighbors(), 0,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j + 1, i - 1), getSimulationNeighbors(), 1,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j - 1, i), getSimulationNeighbors(), 2,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j + 1, i), getSimulationNeighbors(), 3,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j, i + 1), getSimulationNeighbors(), 4,
      currentCell);
    createNeighborhood(new Point(j, i), new Point(j + 1, i + 1), getSimulationNeighbors(), 5,
      currentCell);
  }
}
