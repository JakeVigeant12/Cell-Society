package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.model.grids.FireGraphGrid;
import cellsociety.model.grids.GameOfLifeGraphGrid;
import cellsociety.model.grids.RockPaperScissorGraphGrid;
import cellsociety.model.grids.SegregationGraphGrid;
import cellsociety.model.grids.WatorWorldGraphGrid;
import cellsociety.parser.CSVParser;
import cellsociety.view.GridWrapper;
import java.util.ArrayList;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class WatorGraphGridTest {
  CSVParser myGridParser = new CSVParser();
  GridWrapper gridWrapper = myGridParser.parseData("wator/buffet.csv");
  Properties p = new Properties();
  @Test
  public void testStateComputation()
  {
    p.setProperty("Type", "WatorWorld");
    WatorWorldGraphGrid myTestGrid = new WatorWorldGraphGrid(gridWrapper, p);
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

    gridWrapper = myGridParser.parseData("wator/buffet.csv");
    myTestGrid = new WatorWorldGraphGrid(gridWrapper, p);
    prevStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    myTestGrid.computeStates();
    nextStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    areEqual = true;
    for (int i = 0; i < prevStates.size(); i++) {
      areEqual = areEqual && (prevStates.get(i) == nextStates.get(i));
    }
    //Unstable input config
    assertFalse(areEqual);

  }
}
