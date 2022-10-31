package cellsociety.parser;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVParserTest {

  private CSVParser myParser;

  @BeforeEach
  void setUp() {
    myParser = new CSVParser();
  }

  @Test
  void testParseSquareGrid() throws CsvValidationException, IOException {
    GridWrapper expectedGrid = createSquareGrid();
    GridWrapper actualGrid = myParser.parseData("test/test_square.csv");
    assertEquals(expectedGrid.getRowCount(), actualGrid.getRowCount());
    assertEquals(expectedGrid.getRowSize(0), actualGrid.getRowSize(0));
    for (int i = 0; i < expectedGrid.getRowCount(); i++) {
      for (int j = 0; j < expectedGrid.getRowSize(0); j++) {
        assertEquals(expectedGrid.getState(i, j), actualGrid.getState(i, j));
      }
    }
  }

  @Test
  void testParseRectangularGrid() throws CsvValidationException, IOException {
    GridWrapper expectedGrid = createRectangularGrid();
    GridWrapper actualGrid = myParser.parseData("test/test_rectangle.csv");
    assertEquals(expectedGrid.getRowCount(), actualGrid.getRowCount());
    assertEquals(expectedGrid.getRowSize(0), actualGrid.getRowSize(0));
    for (int i = 0; i < expectedGrid.getRowCount(); i++) {
      for (int j = 0; j < expectedGrid.getRowSize(0); j++) {
        assertEquals(expectedGrid.getState(i, j), actualGrid.getState(i, j));
      }
    }
  }

  @Test
  void testSaveCurrentSquareGrid() throws IOException, CsvValidationException {
    GridWrapper grid = createSquareGrid();
    File file = new File("data/test/test_save_square.csv");
    myParser.saveCurrentGrid(grid, file);
    GridWrapper parsedCSVGridWrapper = myParser.parseData("test/test_save_square.csv");
    List<List<Integer>> parsedCSVGrid = parsedCSVGridWrapper.getGrid();
    assertEquals(grid.getRowCount(), parsedCSVGrid.size());
    assertEquals(grid.getRowSize(0), parsedCSVGrid.get(0).size());
    for (int i = 0; i < grid.getRowCount(); i++) {
      for (int j = 0; j < grid.getRowSize(0); j++) {
        assertEquals(grid.getState(i, j), parsedCSVGrid.get(i).get(j));
      }
    }
  }

  @Test
  void testSaveCurrentRectangularGrid() throws IOException, CsvValidationException {
    GridWrapper grid = createRectangularGrid();
    File file = new File("data/test/test_save_rectangle.csv");
    myParser.saveCurrentGrid(grid, file);
    GridWrapper parsedCSVGridWrapper = myParser.parseData("test/test_save_rectangle.csv");
    List<List<Integer>> parsedCSVGrid = parsedCSVGridWrapper.getGrid();
    assertEquals(grid.getRowCount(), parsedCSVGrid.size());
    assertEquals(grid.getRowSize(0), parsedCSVGrid.get(0).size());
    for (int i = 0; i < grid.getRowCount(); i++) {
      for (int j = 0; j < grid.getRowSize(0); j++) {
        assertEquals(grid.getState(i, j), parsedCSVGrid.get(i).get(j));
      }
    }
  }

  private GridWrapper createSquareGrid() {
    GridWrapper gridWrapper = new GridWrapper(3, 3);
    int[][] grid = {{0, 0, 1}, {1, 0, 0}, {0, 1, 0}};
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        gridWrapper.setState(i, j, grid[i][j]);
      }
    }
    return gridWrapper;
  }

  private GridWrapper createRectangularGrid() {
    GridWrapper gridWrapper = new GridWrapper(3, 4);
    int[][] grid = {{0, 0, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 1}};
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        gridWrapper.setState(i, j, grid[i][j]);
      }
    }
    return gridWrapper;
  }
}