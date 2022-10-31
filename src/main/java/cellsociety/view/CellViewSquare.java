package cellsociety.view;

import javafx.scene.shape.Rectangle;

public class CellViewSquare extends CellView {
  public CellViewSquare(int state, ColorMap colors) {
    super(state, colors);
    initializeShape();
  }

  public CellViewSquare(int state, ImageMap images) {
    super(state, images);
    initializeShape();
  }

  private void initializeShape() {
    setPolygon(new Rectangle());
    getChildren().add(getShapePolygon());
    setStateStyle();
  }

  @Override
  public void updateSize(double size) {
    Rectangle rectangle = (Rectangle) getShapePolygon();
    rectangle.setWidth(size);
    rectangle.setHeight(size);
    setStateStyle();
  }
}
