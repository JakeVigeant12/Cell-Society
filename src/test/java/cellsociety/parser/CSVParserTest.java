package cellsociety.parser;

import static org.junit.jupiter.api.Assertions.*;

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
    List<List<String>> expectedGrid = createSquareGrid();
    List<List<String>> actualGrid = myParser.parseData("test/test_square.csv");
    assertEquals(expectedGrid.size(), actualGrid.size());
    assertEquals(expectedGrid.get(0).size(), actualGrid.get(0).size());
    for(int i = 0; i < expectedGrid.size(); i++) {
      Assertions.assertArrayEquals(expectedGrid.get(i).toArray(), actualGrid.get(i).toArray());
    }
  }

  @Test
  void testParseRectangularGrid() throws CsvValidationException, IOException {
    List<List<String>> expectedGrid = createRectangularGrid();
    List<List<String>> actualGrid = myParser.parseData("test/test_rectangle.csv");
    assertEquals(expectedGrid.size(), actualGrid.size());
    assertEquals(expectedGrid.get(0).size(), actualGrid.get(0).size());
    for(int i = 0; i < expectedGrid.size(); i++) {
      Assertions.assertArrayEquals(expectedGrid.get(i).toArray(), actualGrid.get(i).toArray());
    }
  }

  @Test
  void testSaveCurrentSquareGrid() throws IOException, CsvValidationException {
    List<List<String>> grid = createSquareGrid();
    File file = new File("data/test/test_save_square.csv");
    myParser.saveCurrentGrid(grid, file);
    List<List<String>> parsedCSVGrid = myParser.parseData("test/test_save_square.csv");
    assertEquals(grid.size(), parsedCSVGrid.size());
    assertEquals(grid.get(0).size(), parsedCSVGrid.get(0).size());
    for(int i = 0; i < grid.size(); i++) {
      Assertions.assertArrayEquals(grid.get(i).toArray(), parsedCSVGrid.get(i).toArray());
    }
  }

  @Test
  void testSaveCurrentRectangularGrid() throws IOException, CsvValidationException {
    List<List<String>> grid = createRectangularGrid();
    File file = new File("data/test/test_save_rectangle.csv");
    myParser.saveCurrentGrid(grid, file);
    List<List<String>> parsedCSVGrid = myParser.parseData("test/test_save_rectangle.csv");
    assertEquals(grid.size(), parsedCSVGrid.size());
    assertEquals(grid.get(0).size(), parsedCSVGrid.get(0).size());
    for(int i = 0; i < grid.size(); i++) {
      Assertions.assertArrayEquals(grid.get(i).toArray(), parsedCSVGrid.get(i).toArray());
    }
  }

  private List<List<String>> createSquareGrid() {
    List<List<String>> grid = new ArrayList<>();
    grid.add(List.of("0", "0", "1"));
    grid.add(List.of("1", "0", "0"));
    grid.add(List.of("0", "1", "0"));
    return grid;
  }

  private List<List<String>> createRectangularGrid() {
    List<List<String>> grid = new ArrayList<>();
    grid.add(List.of("0", "0", "1", "0"));
    grid.add(List.of("1", "0", "0", "1"));
    grid.add(List.of("0", "1", "0", "1"));
    return grid;
  }
}