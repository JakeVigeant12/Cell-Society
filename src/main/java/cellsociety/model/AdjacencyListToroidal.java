package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;

import java.awt.Point;
import java.util.Map;

public class AdjacencyListToroidal extends AdjacencyList {

  public AdjacencyListToroidal(GridWrapper inputLayout, Map<Point, Cell> cells,
      Neighborhood simulationNeighbors) {
    super(inputLayout, cells, simulationNeighbors);
  }

  @Override
  protected void createNeighborhood(Point cell, Point currId, Neighborhood simulationNeighbors,
      int neighborNumber, Cell currentCell) {
    if (isInBounds(currId, getInputLayout())) {
      if (simulationNeighbors.countNeighbor(neighborNumber)) {
        getAdjacencyList().get(currentCell).add(getCellMap().get(currId));
      }
    } else {
      if (simulationNeighbors.countNeighbor(neighborNumber)) {
        getAdjacencyList().get(currentCell).add(getCellMap().get(
            new Point(getInputLayout().getRowSize(0) - cell.x - 1,
                getInputLayout().getRowCount() - cell.y - 1)));
      }
    }
  }
}
