package cellsociety.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class CellView extends StackPane {
    public static Map<Integer, Color> colorMap = Map.of(0, Color.DARKGRAY, 1, Color.GREEN);
    private Rectangle rectangle;
    private Label label;

    /**
     * Constructor for CellView
     * @param state
     * @param width
     */
    public CellView(Integer state, double width) {

        // create rectangle
        rectangle = new Rectangle(width, width);
        rectangle.setStroke(Color.BROWN);

        rectangle.setFill(colorMap.get(state));

        // create label
        label = new Label(String.valueOf(state));

        // set position
//            setTranslateX(x);
//            setTranslateY(y);

        getChildren().addAll(rectangle, label);

    }

    /**
     * Updates the visual representation of the cell
     * @param state
     */
    public void updateState(Integer state) {
        rectangle.setFill(colorMap.get(state));
        label.setText(String.valueOf(state));
    }
}

