package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private final IntegerProperty state;
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
    public CellView(int state, String simulationType, int y, int x, CellSocietyController controller) {
        myResources = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, CELLSTATES));

        this.x = x;
        this.y = y;
        myType = simulationType;
        numStates = Integer.parseInt(myResources.getString(myType));
        // create rectangle
        rectangle = new Rectangle();
        rectangle.setStroke(Color.BROWN);
        this.state = new SimpleIntegerProperty(state);
        rectangle.getStyleClass().add(myType + this.state.get());

        // create label
        label = new Label(String.valueOf(this.state.get()));

        // set position
        // setTranslateX(x);
        // setTranslateY(y);

        getChildren().addAll(rectangle);

        setOnClick(controller);
    }

    public IntegerProperty stateProperty() {
        return state;
    }

    /**
     * Change the state of the cell on click
     */
    public void setOnClick(CellSocietyController controller) {
        this.setOnMouseClicked(e -> {
            circulateState();
            rectangle.getStyleClass().clear();
            rectangle.getStyleClass().add(myType + state.get());

            controller.updateOneCell(y, x, state.get());
        });
    }

    private void circulateState() {
        if (state.get() < numStates - 1)
            state.set(state.get() + 1);
        else
            state.set(0);
    }

    /**
     * Updates the visual representation of the cell
     *
     * @param state
     */
    public void updateState(Integer state) {
        rectangle.getStyleClass().remove(0);
        this.state.setValue(state);
        rectangle.getStyleClass().add(myType + this.state.get());
        label.setText(String.valueOf(state));
    }

    public void updateSize(double size) {
        rectangle.setWidth(size);
        rectangle.setHeight(size);
    }

    //For test purpose
    public Rectangle getRectangle() {
        return rectangle;
    }
}

