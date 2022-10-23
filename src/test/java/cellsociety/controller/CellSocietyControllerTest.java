package cellsociety.controller;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellSocietyControllerTest {

  private CellSocietyController myController;

  @BeforeEach
  void setUp() throws CsvValidationException, IOException {
    myController = new CellSocietyController(new File("data/test/validFile.sim"));
  }

  @Test
  void getViewGridTest() {
    Cell cell1 = new Cell(0, 1);
    Cell cell2 = new Cell(0, 2);
    Cell cell3 = new Cell(1, 3);
    Cell cell4 = new Cell(1, 4);
    Cell cell5 = new Cell(0, 5);
    Cell cell6 = new Cell(0, 6);
    Cell cell7 = new Cell(0, 7);
    Cell cell8 = new Cell(1, 8);
    Cell cell9 = new Cell(0, 9);
    Map<Integer, Cell> cellMap = new HashMap<>();
    cellMap.put(1, cell1);
    cellMap.put(2, cell2);
    cellMap.put(3, cell3);
    cellMap.put(4, cell4);
    cellMap.put(5, cell5);
    cellMap.put(6, cell6);
    cellMap.put(7, cell7);
    cellMap.put(8, cell8);
    cellMap.put(9, cell9);
    myController.setBackEndCellsByID(cellMap);
    GridWrapper expected = new GridWrapper(3, 3);
    for(Integer key : cellMap.keySet()) {
      expected.set((key -1)/ 3, (key - 1)  % 3, cellMap.get(key).getCurrentState());
    }
    GridWrapper actual = myController.getViewGrid().toGridWrapper();
    Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
  }

}