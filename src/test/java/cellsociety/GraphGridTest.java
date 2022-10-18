package cellsociety;
import cellsociety.model.Cell;
import cellsociety.model.GameOfLifeCell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import cellsociety.model.GraphGrid;

import static cellsociety.SimType.GameOfLife;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphGridTest {
  List<List<String>> gridParsing = new ArrayList<>();


  @Test
  void testBasicObject(){
    constructTestArrayList(4,4);
    GraphGrid gameOfLifeGrid = new GraphGrid(gridParsing ,GameOfLife);
    HashMap<Integer, Cell> testCells = new HashMap<>();
    for(int i = 1; i <= 16; i++){

      testCells.put(i, new GameOfLifeCell(1,i));
    }
    assertEquals(testCells.get(1).getCurrentState(),gameOfLifeGrid.getCells().get(16).getCurrentState());
    assertEquals(testCells.size(), gameOfLifeGrid.getCells().size());

  }
  public void constructTestArrayList(int rows, int cols){
    for(int i = 0; i < rows; i++){
      gridParsing.add(new ArrayList<String>());
      for(int j = 0; j < cols; j++){
        if(i == 0 && j ==0){
          gridParsing.get(i).add("0");
        }
        gridParsing.get(i).add("1");
      }
    }
  }
}
