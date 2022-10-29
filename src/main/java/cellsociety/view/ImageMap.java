package cellsociety.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class ImageMap {
  private Map<Integer, Image> colorMap;
  private int count;
  public ImageMap() {
    colorMap = new HashMap<>();
    count = 0;
  }

  public void addImage(Image image) {
    colorMap.put(count, image);
    count++;
  }

  public Image getImage(int state) {
    return colorMap.get(state);
  }

  public int getStateCount() {
    return colorMap.size();
  }
}
