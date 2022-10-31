package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.model.grids.PercolationGraphGrid;
import cellsociety.parser.CSVParser;

import java.util.ArrayList;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class PercolationGraphGridTest {
  CSVParser myGridParser = new CSVParser();
  PercolationGraphGrid myTestGrid;
  GridWrapper gridWrapper;
  ArrayList<Integer> prevStates = new ArrayList<>();
  ArrayList<Integer> nextStates = new ArrayList<>();
  Properties p = new Properties();
  @Test
  public void testStableStateComputation()
  {
    p.setProperty("Type", "Percolation");
    gridWrapper = myGridParser.parseData("percolation/percolation_simple_stable.csv");
    myTestGrid = new PercolationGraphGrid(gridWrapper, p);
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
  @Test
  public void testMovingStates(){
    p.setProperty("Type", "Percolation");
    gridWrapper = myGridParser.parseData("percolation/percolation_simple_unstable.csv");
    myTestGrid = new PercolationGraphGrid(gridWrapper, p);
    prevStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    myTestGrid.computeStates();
    nextStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    boolean areEqual = true;
    for (int i = 0; i < prevStates.size(); i++) {
      areEqual = areEqual && (prevStates.get(i) == nextStates.get(i));
    }
    //Stable input config
    assertFalse(areEqual);
  }
}
