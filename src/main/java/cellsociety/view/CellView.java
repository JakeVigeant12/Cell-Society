package cellsociety.view;

import java.util.Properties;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

import static cellsociety.view.SplashScreen.DEFAULT_RESOURCE_FOLDER;
import static cellsociety.view.SplashScreen.DEFAULT_RESOURCE_PACKAGE;

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
  private final String[] myStateStyles;
  private final Properties myProperties;

  /**
   * Constructor for CellView
   *
   * @param state
   */
  public CellView(int state, Properties properties, int y, int x, String[] stateStyles) {
    isClicked = new SimpleBooleanProperty(false);
    myResources = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, CELLSTATES));
    myStateStyles = stateStyles;
    this.x = x;
    this.y = y;
    //TODO: x, y might not be needed
    myProperties = properties;
    myType = (String) myProperties.get("Type");
    numStates = Integer.parseInt(myResources.getString(myType));
    // create rectangle
    rectangle = new Rectangle();
    rectangle.setStroke(Color.BROWN);
    this.state = state;
    setStateStyle(state);
    // create label
    label = new Label(String.valueOf(this.state));

    // set position
    // setTranslateX(x);
    // setTranslateY(y);

    getChildren().addAll(rectangle);

    setOnClick();
  }

  private void setStateStyle(int state) {
    if(myProperties.containsKey("StateImages")) {
      Image image = new Image(String.format("%s%s", DEFAULT_RESOURCE_FOLDER, myStateStyles[state]));
      rectangle.setFill(new ImagePattern(image));
    } else {
      rectangle.setFill(Color.web(myStateStyles[state]));
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
      rectangle.setFill(Color.web(myStateStyles[state]));
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
    setStateStyle(state);
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

