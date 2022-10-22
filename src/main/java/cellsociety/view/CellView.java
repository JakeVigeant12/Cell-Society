package cellsociety.view;

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

public class CellView extends StackPane {
    private Rectangle rectangle;
    private Label label;
    private String myType;
    private final IntegerProperty state;

    /**
     * Constructor for CellView
     *
     * @param state
     */
    public CellView(int state, String simulationType) {
        myType = simulationType;
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

        setOnClick();
    }

    public IntegerProperty stateProperty() {
        return state;
    }

    /**
     * Change the state of the cell on click
     */
    public void setOnClick() {
        this.setOnMouseClicked(e -> {
            if (state.get() == 0)
                state.setValue(1);
            else
                state.setValue(0);
            rectangle.getStyleClass().clear();
            rectangle.getStyleClass().add(myType + state.get());
        });
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
}

