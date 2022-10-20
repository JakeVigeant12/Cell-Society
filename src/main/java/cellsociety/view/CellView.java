package cellsociety.view;

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

    /**
     * Constructor for CellView
     * @param state
     * @param width
     */
    public CellView(Integer state, double width, String simulationType) {
        myType = simulationType;
        // create rectangle
        rectangle = new Rectangle(width, width);
        rectangle.setStroke(Color.BROWN);
        rectangle.getStyleClass().add(myType + state);

        // create label
        label = new Label(String.valueOf(state));

        // set position
        // setTranslateX(x);
        // setTranslateY(y);

        getChildren().addAll(rectangle);

    }

    /**
     * Updates the visual representation of the cell
     * @param state
     */
    public void updateState(Integer state) {
        rectangle.getStyleClass().remove(0);
        rectangle.getStyleClass().add(myType + state);
        label.setText(String.valueOf(state));
    }
}

