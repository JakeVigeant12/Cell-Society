package cellsociety;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GridView extends SceneCreator {
    GridPane grid;
    private double size;
    private int n = 20;
    private int m = 20;

    CellView[][] cells = new CellView[n][m];

    public GridView(double size) {
        super(size);
        this.size = size;
        grid = new GridPane();
    }

    public GridPane setUpGrid(Stage primaryStage) {

        double width = size / 20;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(y + "," + x, y * width, +x * width, width, Color.AZURE);
                // add cells to group
                grid.getChildren().add(node);
                // add to grid for further reference using an array
                cells[y][x] = node;
            }
        }
        return grid;
    }
}
