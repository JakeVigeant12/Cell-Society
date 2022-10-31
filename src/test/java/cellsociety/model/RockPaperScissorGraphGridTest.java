package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.model.grids.FireGraphGrid;
import cellsociety.model.grids.GameOfLifeGraphGrid;
import cellsociety.model.grids.RockPaperScissorGraphGrid;
import cellsociety.model.grids.SegregationGraphGrid;
import cellsociety.parser.CSVParser;
import java.util.ArrayList;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class RockPaperScissorGraphGridTest {
  CSVParser myGridParser = new CSVParser();
  RockPaperScissorGraphGrid myTestGrid;
  GridWrapper gridWrapper;
  ArrayList<Integer> prevStates = new ArrayList<>();
  ArrayList<Integer> nextStates = new ArrayList<>();
  Properties p = new Properties();
  @Test
  public void testStableStateComputation()
  {
    p.setProperty("Type", "RockPaperScissor");
    myTestGrid = new RockPaperScissorGraphGrid(gridWrapper, p);
    gridWrapper = myGridParser.parseData("rock_paper_scissors/stable_sample.csv");
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
    p.setProperty("Type", "RockPaperScissor");
    gridWrapper = myGridParser.parseData("rock_paper_scissors/stable_sample.csv");
    myTestGrid = new RockPaperScissorGraphGrid(gridWrapper, p);
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
