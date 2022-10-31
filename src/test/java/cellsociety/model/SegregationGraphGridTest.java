package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.model.grids.FireGraphGrid;
import cellsociety.model.grids.GameOfLifeGraphGrid;
import cellsociety.parser.CSVParser;
import cellsociety.view.GridWrapper;
import java.util.ArrayList;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class SegregationGraphGridTest {
  CSVParser myGridParser = new CSVParser();
  GridWrapper gridWrapper = myGridParser.parseData("schelling/schelling_simple_test.csv");
  Properties p = new Properties();
  @Test
  public void testStateComputation()
  {
    p.setProperty("Type", "Segregation");
    GameOfLifeGraphGrid myTestGrid = new GameOfLifeGraphGrid(gridWrapper, p);
    ArrayList<Integer> prevStates = new ArrayList<>();
    ArrayList<Integer> nextStates = new ArrayList<>();
    prevStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    myTestGrid.computeStates();
    nextStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    boolean areEqual = true;
    for (int i = 0; i < prevStates.size(); i++) {
      areEqual = areEqual && (prevStates.get(i) == nextStates.get(i));
    }
    //Stable input config
    assertTrue(areEqual);
  }
}
