package cellsociety.model;
import cellsociety.model.cells.Cell;
import cellsociety.model.cells.GameOfLifeCell;

import cellsociety.model.grids.GraphGrid;

import java.awt.Point;;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

import cellsociety.view.GridWrapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphGridTest {
  GridWrapper gridParsing = new GridWrapper();


  @Test
  void testBasicObject()
      throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
    constructTestArrayList(4,4);
    File myDataFile = new File("./data/game_of_life/blinkers.sim");
    Properties properties = new Properties();
    properties.load(new FileReader(myDataFile));
    GraphGrid gameOfLifeGrid = new GraphGrid(gridParsing ,properties);
    HashMap<Integer, Cell> testCells = new HashMap<>();
    for(int i = 0; i < 16; i++){

      testCells.put(i, new GameOfLifeCell(1,new Point(1, 0)));
    }
    assertEquals(testCells.get(1).getCurrentState(),gameOfLifeGrid.getCells().get(new Point(3,3)).getCurrentState());
    assertEquals(testCells.size(), gameOfLifeGrid.getCells().size());

  }
  @Test
  void testBounds(){
    constructTestArrayList(12,12);
    assertEquals(true , GraphGrid.isInBounds(1,1,gridParsing));

  }
  public void constructTestArrayList(int rows, int cols){
    for(int i = 0; i < rows; i++){
      gridParsing.addRow();
      for(int j = 0; j < cols; j++){
        if(i == 0 && j ==0)
          gridParsing.addValueToRow(i, 0);
        else
          gridParsing.addValueToRow(i, 1);
      }
    }
  }
}
