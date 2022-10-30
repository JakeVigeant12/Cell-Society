package cellsociety.model;

import cellsociety.model.cells.Cell;
import cellsociety.model.neighborhoods.Neighborhood;
import cellsociety.view.GridWrapper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList {
  Map<Cell, List<Cell>> adjacencyList;
  private int rowCount;
  private int rowSize;

  public AdjacencyList(GridWrapper inputLayout, Map<Point, Cell> cells, Neighborhood simulationNeighbors) {
    adjacencyList = new HashMap<>();
    rowCount = inputLayout.getRowCount();
    rowSize = inputLayout.getRowSize(0);
    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < rowSize; j++) {
        List<Cell> neighbors = new ArrayList<>();
        Cell currentCell = cells.get(new Point(j, i));
        adjacencyList.putIfAbsent(currentCell, neighbors);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j - 1, i - 1), simulationNeighbors, 0, adjacencyList, currentCell, cells);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j, i - 1), simulationNeighbors, 1, adjacencyList, currentCell, cells);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j + 1, i - 1), simulationNeighbors, 2, adjacencyList, currentCell, cells);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j - 1, i), simulationNeighbors, 3, adjacencyList, currentCell, cells);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j + 1, i), simulationNeighbors, 4, adjacencyList, currentCell, cells);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j - 1, i + 1), simulationNeighbors, 5, adjacencyList, currentCell, cells);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j, i + 1), simulationNeighbors, 6, adjacencyList, currentCell, cells);
        createNeighborhood(new Point(j, i), inputLayout, new Point(j + 1, i + 1), simulationNeighbors, 7, adjacencyList, currentCell, cells);
      }
    }
  }

  protected void createNeighborhood(Point cell, GridWrapper gridParsing, Point neighbor, Neighborhood simulationNeighbors, int neighborNumber, Map<Cell, List<Cell>> adjacencyList, Cell currentCell, Map<Point, Cell> myCells) {
    if(isInBounds(neighbor, gridParsing)){
      if(simulationNeighbors.countNeighbor(neighborNumber)) {
        adjacencyList.get(currentCell).add(myCells.get(neighbor));
      }
    }
  }

  protected int getRowCount() {
    return rowCount;
  }

  protected int getRowSize() {
    return rowSize;
  }

  public boolean isOnEdges(int row, int col, GridWrapper gridWrapper){
    return (row == 0 || row == gridWrapper.getRowCount() - 1) || (col == 0 || col == gridWrapper.getRowSize(0) - 1);
  }

  protected boolean isInBounds(Point point, GridWrapper gridWrapper){
    return (point.y >= 0 && point.y < gridWrapper.getRowCount()) && (point.x >= 0 && point.x < gridWrapper.getRowSize(0));
  }

  public List<Cell> getCells() {
    return adjacencyList.keySet().stream().toList();
  }

  public List<Cell> getNeighbors(Cell cell) {
    return adjacencyList.get(cell);
  }
}
