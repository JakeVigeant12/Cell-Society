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
    private final Rectangle rectangle;
    private final Label label;
    private final String myType;

    /**
     * Constructor for CellView
     * @param state
     */
    public CellView(Integer state, String simulationType) {
        myType = simulationType;
        // create rectangle
        rectangle = new Rectangle();
        rectangle.setStroke(Color.BROWN);
        rectangle.getStyleClass().add(myType + state);

        // create label
        label = new Label(String.valueOf(state));

        getChildren().addAll(rectangle);

        this.setOnMouseClicked(e -> rectangle.setFill(Color.RED));

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

    public void updateSize(double size){
        rectangle.setWidth(size);
        rectangle.setHeight(size);
    }
}

