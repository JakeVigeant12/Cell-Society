package cellsociety.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
  //https://stackoverflow.com/questions/68129134/create-hexagonal-field-with-flat-tiles-with-javafx
  private double r = 20; // the inner radius from hexagon center to outer corner
  private double n = Math.sqrt(r * r * 0.75); // the inner radius from hexagon center to middle of the axis
  private double TILE_HEIGHT = 2 * r;
  private final double TILE_WIDTH = 2 * n;

  public Hexagon() {
    // creates the polygon using the corner coordinates
    getPoints().addAll(
      0.0, 0.0,
      0.0, r,
      n, r * 1.5,
      TILE_WIDTH, r,
      TILE_WIDTH, 0.0,
      n, -r * 0.5
    );
  }

  public double getR() {
    return r;
  }

  public void setSize(double size) {
    r = size;
    getPoints().clear();
    getPoints().addAll(
      0.0, 0.0,
      0.886*r, -0.5*r,
      0.886*r, -r * 1.5,
      0.0, -2*r,
      -0.886*r, -r * 1.5,
      -0.886*r, -0.5*r
    );
  }
}