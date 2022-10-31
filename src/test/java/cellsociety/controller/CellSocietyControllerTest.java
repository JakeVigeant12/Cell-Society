package cellsociety.controller;

import cellsociety.model.cells.Cell;
import cellsociety.view.GridWrapper;
import com.opencsv.exceptions.CsvValidationException;

import java.awt.Point;;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellSocietyControllerTest {

  private CellSocietyController myController;

  @BeforeEach
  void setUp()
      throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
    myController = new CellSocietyController(new File("data/test/validFile.sim"));
  }

  @Test
  void getViewGridTest() {
    Cell cell1 = new Cell(0, new Point(0, 0));
    Cell cell2 = new Cell(0, new Point(1, 0));
    Cell cell3 = new Cell(1, new Point(2, 0));
    Cell cell4 = new Cell(1, new Point(1, 0));
    Cell cell5 = new Cell(0, new Point(1, 1));
    Cell cell6 = new Cell(0, new Point(1, 2));
    Cell cell7 = new Cell(0, new Point(2, 0));
    Cell cell8 = new Cell(1, new Point(2, 1));
    Cell cell9 = new Cell(0, new Point(2, 2));
    Map<Point, Cell> cellMap = new HashMap<>();
    cellMap.put(new Point(0, 0), cell1);
    cellMap.put(new Point(1, 0), cell2);
    cellMap.put(new Point(2, 0), cell3);
    cellMap.put(new Point(0, 1), cell4);
    cellMap.put(new Point(1, 1), cell5);
    cellMap.put(new Point(2, 1), cell6);
    cellMap.put(new Point(0, 2), cell7);
    cellMap.put(new Point(1, 2), cell8);
    cellMap.put(new Point(2, 2), cell9);
    myController.setBackEndCellsByID(cellMap);
    GridWrapper expected = new GridWrapper(3, 3);
    for (Point key : cellMap.keySet()) {
      expected.setState(key.y, key.x, cellMap.get(key).getCurrentState());
    }
    GridWrapper actual = myController.getViewGrid();
    Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
  }

}