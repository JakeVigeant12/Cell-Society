package cellsociety.parser;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVParserTest {

  private CSVParser myParser;

  @BeforeEach
  void setUp() throws IOException {
    myParser = new CSVParser("test/test_square.csv");
  }

  @Test
  void testParseSquareGrid() throws CsvValidationException, IOException {
    GridWrapper expectedGrid = createSquareGrid();
    List<List<String>> actualGrid = (List<List<String>>) myParser.parseData();
    assertEquals(expectedGrid.row(), actualGrid.size());
    assertEquals(expectedGrid.column(), actualGrid.get(0).size());
    for(int i = 0; i < expectedGrid.row(); i++) {
      for(int j = 0; j < expectedGrid.column(); j++) {
        assertEquals(expectedGrid.get(i,j), Integer.parseInt(actualGrid.get(i).get(j)));
      }
    }
  }

  @Test
  void testParseRectangularGrid() throws CsvValidationException, IOException {
    GridWrapper expectedGrid = createRectangularGrid();
    List<List<String>> actualGrid = myParser.parseData("test/test_rectangle.csv");
    assertEquals(expectedGrid.row(), actualGrid.size());
    assertEquals(expectedGrid.column(), actualGrid.get(0).size());
    for(int i = 0; i < expectedGrid.row(); i++) {
      for(int j = 0; j < expectedGrid.column(); j++) {
        assertEquals(expectedGrid.get(i,j), Integer.parseInt(actualGrid.get(i).get(j)));
      }
    }
  }

  @Test
  void testSaveCurrentSquareGrid() throws IOException, CsvValidationException {
    GridWrapper grid = createSquareGrid();
    File file = new File("data/test/test_save_square.csv");
    myParser.saveCurrentGrid(grid, file);
    List<List<String>> parsedCSVGrid = myParser.parseData("test/test_save_square.csv");
    assertEquals(grid.row(), parsedCSVGrid.size());
    assertEquals(grid.column(), parsedCSVGrid.get(0).size());
    for(int i = 0; i < grid.row(); i++) {
      for(int j = 0; j < grid.column(); j++) {
        assertEquals(grid.get(i,j), Integer.parseInt(parsedCSVGrid.get(i).get(j)));
      }
    }
  }

  @Test
  void testSaveCurrentRectangularGrid() throws IOException, CsvValidationException {
    GridWrapper grid = createRectangularGrid();
    File file = new File("data/test/test_save_rectangle.csv");
    myParser.saveCurrentGrid(grid, file);
    List<List<String>> parsedCSVGrid = myParser.parseData("test/test_save_rectangle.csv");
    assertEquals(grid.row(), parsedCSVGrid.size());
    assertEquals(grid.column(), parsedCSVGrid.get(0).size());
    for(int i = 0; i < grid.row(); i++) {
      for(int j = 0; j < grid.column(); j++) {
        assertEquals(grid.get(i,j), Integer.parseInt(parsedCSVGrid.get(i).get(j)));
      }
    }
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