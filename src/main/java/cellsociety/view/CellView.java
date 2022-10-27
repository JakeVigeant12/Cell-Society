package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.spi.ResourceBundleControlProvider;

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

  /**
   * Constructor for CellView
   *
   * @param state
   */
  public CellView(int state, String simulationType, int y, int x) {
    isClicked = new SimpleBooleanProperty(false);
    myResources = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, CELLSTATES));
    //TODO: refactor it by getting colors

    this.x = x;
    this.y = y;
    //TODO: x, y might not be needed
    myType = simulationType;
    numStates = Integer.parseInt(myResources.getString(myType));
    // create rectangle
    rectangle = new Rectangle();
    rectangle.setStroke(Color.BROWN);
    this.state = state;
    rectangle.getStyleClass().add(myType + this.state);

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
      rectangle.getStyleClass().clear();
      rectangle.getStyleClass().add(myType + state);
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
    rectangle.getStyleClass().remove(0);
    this.state = state;
    rectangle.getStyleClass().add(myType + this.state);
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

