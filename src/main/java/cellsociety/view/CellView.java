package cellsociety.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;


public abstract class CellView extends StackPane {

  private Shape shape;
  private int state;
  private int numStates;
  private ColorMap colors;
  private ImageMap images;
  private boolean isUsingColors;

  /**
   * Constructor for CellView
   *
   * @param state
   */
  public CellView(int state) {
    this.state = state;
  }

  public CellView(int state, ImageMap images) {
    this(state);
    isUsingColors = false;
    this.images = images;
    numStates = this.images.getStateCount();
  }

  public CellView(int state, ColorMap colors) {
    this(state);
    isUsingColors = true;
    this.colors = colors;
    numStates = this.colors.getStateCount();
  }

  public int getState() {
    return state;
  }

  /**
   * Change the state of the cell on click
   */
  public void setOnClick() {
    //circulateState() must be put before isClicked.setValue(true). Otherwise, controller cannot observe the change in state.
    circulateState();
    setStateStyle();
  }

  protected void setStateStyle() {
    if (isUsingColors) {
      shape.setFill(colors.getColor(state));
    } else {
      shape.setFill(new ImagePattern(images.getImage(state)));
    }
  }

  /**
   * Circulate through all the states
   */
  private void circulateState() {
    if (state < numStates - 1) {
      state++;
    } else {
      state = 0;
    }
  }

  /**
   * Updates the visual representation of the cell
   *
   * @param state
   */
  public void updateState(Integer state) {
    this.state = state;
    setStateStyle();
  }

  public void updateSize(double size) {
  }

  public void showBorder() {
    shape.setStroke(Color.BROWN);
  }

  //  For test purpose
  protected double getShapeSize() {
    return 0;
  }

  protected Shape getShapePolygon() {
    return shape;
  }

  protected void setPolygon(Shape shape) {
    this.shape = shape;
  }
}
