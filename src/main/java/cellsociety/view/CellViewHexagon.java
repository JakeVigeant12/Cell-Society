package cellsociety.view;


public class CellViewHexagon extends CellView {

  public CellViewHexagon(int state, ColorMap colors) {
    super(state, colors);
    initializeShape();
  }

  public CellViewHexagon(int state, ImageMap images) {
    super(state, images);
    initializeShape();
  }

  private void initializeShape() {
    setPolygon(new Hexagon());
    getChildren().add(getShapePolygon());
    setStateStyle();
  }

  @Override
  public void updateSize(double size) {
    Hexagon hexagon = (Hexagon) getShapePolygon();
    hexagon.setSize(size);
    setStateStyle();
  }
}
