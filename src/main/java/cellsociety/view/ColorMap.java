package cellsociety.view;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class ColorMap {

  private Map<Integer, Color> colorMap;
  private int count;

  public ColorMap() {
    colorMap = new HashMap<>();
    count = 0;
  }

  public void addColor(Color color) {
    colorMap.put(count, color);
    count++;
  }

  public Color getColor(int state) {
    return colorMap.get(state);
  }

  public int getStateCount() {
    return colorMap.size();
  }
}
