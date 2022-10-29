package cellsociety.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class CellView extends StackPane {
  private Rectangle rectangle;
  private int state;
  private final BooleanProperty isClicked;
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
    isClicked = new SimpleBooleanProperty(false);
    //TODO: x, y might not be needed

    rectangle = new Rectangle();
    rectangle.setStroke(Color.BROWN);
    this.state = state;

    getChildren().addAll(rectangle);
    setOnClick();
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

  private void setStateStyle() {
    if(isUsingColors)
      rectangle.setFill(colors.getColor(state));
    else {
      rectangle.setFill(new ImagePattern(images.getImage(state)));
    }
  }

  public int getState() {
    return state;
  }

  /**
   * Change the state of the cell on click
   */
  public void setOnClick() {
    this.setOnMouseClicked(e -> {
      //circulateState() must be put before isClicked.setValue(true). Otherwise, controller cannot observe the change in state.
      circulateState();
      isClicked.setValue(true);
      if(isUsingColors)
        rectangle.setFill(colors.getColor(state));
      else
        rectangle.setFill(new ImagePattern(images.getImage(state)));
      isClicked.set(false);
    });
  }

  public BooleanProperty isClickedProperty() {
    return isClicked;
  }

  private void circulateState() {
    if (state < numStates - 1)
      state++;
    else
      state = 0;
  }

  /**
   * Updates the visual representation of the cell
   *
   * @param state
   */
  public void updateState(Integer state) {
    this.state = state;
    if(isUsingColors)
      rectangle.setFill(colors.getColor(state));
    else
      rectangle.setFill(new ImagePattern(images.getImage(state)));
    isClicked.set(false);
  }

  public void updateSize(double size) {
    rectangle.setWidth(size);
    rectangle.setHeight(size);
  }

  //For test purpose
  protected double getRectangleSize() {
    return rectangle.getWidth();
  }
}
