package cellsociety.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class CellView extends StackPane {
    private Color color;
    public static Map<Integer, Color> colorMap = Map.of(0, Color.DARKGRAY, 1, Color.GREEN);
    Rectangle rectangle;


    public CellView(Integer state, double width) {

        // create rectangle
        Rectangle rectangle = new Rectangle(width, width);
        rectangle.setStroke(Color.BLACK);

        rectangle.setFill(colorMap.get(state));

        // create label
        Label label = new Label(state + "");

        // set position
//            setTranslateX(x);
//            setTranslateY(y);

        getChildren().addAll(rectangle, label);

    }

    public void updateState(Integer state) {
        rectangle.setFill(colorMap.get(state));
    }
}

