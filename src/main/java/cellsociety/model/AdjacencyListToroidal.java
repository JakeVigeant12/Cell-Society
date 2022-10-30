package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class AdjacencyListToroidal extends AdjacencyList{

  public AdjacencyListToroidal(GridWrapper inputLayout, Map<Point, Cell> cells, Neighborhood simulationNeighbors) {
    super(inputLayout, cells, simulationNeighbors);
  }

  @Override
  protected void createNeighborhood(Point cell, GridWrapper gridParsing, Point neighbor, Neighborhood simulationNeighbors, int neighborNumber, Map<Cell, List<Cell>> adjacencyList, Cell currentCell, Map<Point, Cell> myCells) {
    if(isInBounds(neighbor, gridParsing)) {
      if(simulationNeighbors.countNeighbor(neighborNumber)) {
        adjacencyList.get(currentCell).add(myCells.get(neighbor));
      }
    }
    else {
      if(simulationNeighbors.countNeighbor(neighborNumber)) {
        adjacencyList.get(currentCell).add(myCells.get(new Point(this.getRowSize() - cell.x - 1, this.getRowCount() - cell.y - 1)));
      }
    }
  }
}
