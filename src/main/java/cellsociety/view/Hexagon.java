package cellsociety.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
  //https://stackoverflow.com/questions/68129134/create-hexagonal-field-with-flat-tiles-with-javafx
  private double r = 20; // the inner radius from hexagon center to outer corner

  public Hexagon() {
    // creates the hexagon
    getPoints().addAll(
      0.0, 0.0,
      0.886*r, -0.5*r,
      0.886*r, -r * 1.5,
      0.0, -2*r,
      -0.886*r, -r * 1.5,
      -0.886*r, -0.5*r
    );
  }

  /**
   * Set the size of the hexagon
   * @param size the inner radius from hexagon center to outer corner
   */
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