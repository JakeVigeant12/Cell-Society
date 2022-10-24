package cellsociety.parser;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.model.Grid;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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
    assertEquals(expectedGrid.row(), actualGrid.row());
    assertEquals(expectedGrid.column(0), actualGrid.column(0));
    for(int i = 0; i < expectedGrid.row(); i++) {
      for(int j = 0; j < expectedGrid.column(0); j++) {
        assertEquals(expectedGrid.get(i,j), actualGrid.get(i, j));
      }
    }
  }

  @Test
  void testParseRectangularGrid() throws CsvValidationException, IOException {
    GridWrapper expectedGrid = createRectangularGrid();
    GridWrapper actualGrid = myParser.parseData("test/test_rectangle.csv");
    assertEquals(expectedGrid.row(), actualGrid.row());
    assertEquals(expectedGrid.column(0), actualGrid.column(0));
    for(int i = 0; i < expectedGrid.row(); i++) {
      for(int j = 0; j < expectedGrid.column(0); j++) {
        assertEquals(expectedGrid.get(i,j), actualGrid.get(i, j));
      }
    }
  }

  @Test
  void testSaveCurrentSquareGrid() throws IOException, CsvValidationException {
//    GridWrapper grid = createSquareGrid();
//    File file = new File("data/test/test_save_square.csv");
//    myParser.saveCurrentGrid(grid, file);
//    List<List<String>> parsedCSVGrid = myParser.parseData("test/test_save_square.csv");
//    assertEquals(grid.row(), parsedCSVGrid.size());
//    assertEquals(grid.column(0), parsedCSVGrid.get(0).size());
//    for(int i = 0; i < grid.row(); i++) {
//      for(int j = 0; j < grid.column(0); j++) {
//        assertEquals(grid.get(i,j), Integer.parseInt(parsedCSVGrid.get(i).get(j)));
//      }
//    }
  }

  @Test
  void testSaveCurrentRectangularGrid() throws IOException, CsvValidationException {
//    GridWrapper grid = createRectangularGrid();
//    File file = new File("data/test/test_save_rectangle.csv");
//    myParser.saveCurrentGrid(grid, file);
//    List<List<String>> parsedCSVGrid = myParser.parseData("test/test_save_rectangle.csv");
//    assertEquals(grid.row(), parsedCSVGrid.size());
//    assertEquals(grid.column(0), parsedCSVGrid.get(0).size());
//    for(int i = 0; i < grid.row(); i++) {
//      for(int j = 0; j < grid.column(0); j++) {
//        assertEquals(grid.get(i,j), Integer.parseInt(parsedCSVGrid.get(i).get(j)));
//      }
//    }
  }

  private GridWrapper createSquareGrid() {
    GridWrapper gridWrapper = new GridWrapper(3, 3);
    int[][] grid = {{0, 0, 1}, {1, 0, 0}, {0, 1, 0}};
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[i].length; j++) {
        gridWrapper.set(i, j, grid[i][j]);
      }
    }
    return gridWrapper;
  }

  private GridWrapper createRectangularGrid() {
    GridWrapper gridWrapper = new GridWrapper(3, 4);
    int[][] grid = {{0, 0, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 1}};
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[i].length; j++) {
        gridWrapper.set(i, j, grid[i][j]);
      }
    }
    return gridWrapper;
  }
}