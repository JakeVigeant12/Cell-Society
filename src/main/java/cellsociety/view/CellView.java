package cellsociety.view;

import java.util.Properties;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

import static cellsociety.view.StartSplash.DEFAULT_RESOURCE_PACKAGE;

public class CellView extends StackPane {
  private Rectangle rectangle;
  private Label label;
  private String myType;
  private int state;
  private final BooleanProperty isClicked;
  private int x;
  private int y;
  private ResourceBundle myResources;
  private String CELLSTATES = "CellView";
  private int numStates;
  private final String[] myColors;

  /**
   * Constructor for CellView
   *
   * @param state
   */
  public CellView(int state, String simulationType, int y, int x, String[] colors) {
    isClicked = new SimpleBooleanProperty(false);
    myResources = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, CELLSTATES));
    myColors = colors;
    this.x = x;
    this.y = y;
    //TODO: x, y might not be needed
    myType = simulationType;
    numStates = Integer.parseInt(myResources.getString(myType));
    // create rectangle
    rectangle = new Rectangle();
    rectangle.setStroke(Color.BROWN);
    this.state = state;
    rectangle.setFill(Color.web(myColors[state]));

    // create label
    label = new Label(String.valueOf(this.state));

    // set position
    // setTranslateX(x);
    // setTranslateY(y);

    getChildren().addAll(rectangle);

    setOnClick();
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
      rectangle.setFill(Color.web(myColors[state]));
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
    rectangle.setFill(Color.web(myColors[state]));
    label.setText(String.valueOf(state));
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

