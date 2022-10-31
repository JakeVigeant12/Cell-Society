package cellsociety.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.model.grids.FireGraphGrid;
import cellsociety.parser.CSVParser;
import cellsociety.view.GridWrapper;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class FireGraphGridTest {
  CSVParser myGridParser = new CSVParser();
  GridWrapper gridWrapper = myGridParser.parseData("fire/fire_simple_test.csv");

  @Test
  public void testStateComputation(){
    Properties p = new Properties();
    p.setProperty("Type", "Fire");
    p.setProperty("Parameter", "1");
    FireGraphGrid myTestGrid = new FireGraphGrid(gridWrapper,p);
    ArrayList<Integer> prevStates = new ArrayList<>();
    ArrayList<Integer> nextStates = new ArrayList<>();
    prevStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    myTestGrid.computeStates();
    nextStates = (ArrayList) myTestGrid.representStatesAsList(myTestGrid.getCells());
    boolean areEqual = true;
    for(int i = 0; i < prevStates.size(); i++){
      areEqual = areEqual && (prevStates.get(i) == nextStates.get(i));
    }
    assertFalse(areEqual);

  }

}
