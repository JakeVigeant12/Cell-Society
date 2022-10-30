package cellsociety.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class CellView extends StackPane {

  private Rectangle rectangle;
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
    //TODO: x, y might not be needed

    rectangle = new Rectangle();
    this.state = state;

    getChildren().add(rectangle);
  }

  public CellView(int state, ImageMap images) {
    this(state);
    isUsingColors = false;
    this.images = images;
    numStates = this.images.getStateCount();
    setStateStyle();
  }

  public CellView(int state, ColorMap colors) {
    this(state);
    isUsingColors = true;
    this.colors = colors;
    numStates = this.colors.getStateCount();
    setStateStyle();
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

  private void setStateStyle() {
    if (isUsingColors) {
      rectangle.setFill(colors.getColor(state));
    } else {
      rectangle.setFill(new ImagePattern(images.getImage(state)));
    }
  }

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
    rectangle.setWidth(size);
    rectangle.setHeight(size);
  }

  public void showBorder() {
    rectangle.setStroke(Color.BROWN);
  }

  //For test purpose
  protected double getRectangleSize() {
    return rectangle.getWidth();
  }
}
