package cellsociety;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GridView extends Application {

    private double paneHeight = 800;
    private double paneWidth = 700;
    private int n = 20;
    private int m = 20;

    CellView[][] grid = new CellView[n][m];

    @Override
    public void start(Stage primaryStage) {

        int width = 700 / 20;

        GridPane root = new GridPane();

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {

                CellView node = new CellView(y + "," + x, y * width, 100 + x * width, width);

                // add cells to group
                root.getChildren().add(node);

                // add to grid for further reference using an array
                grid[y][x] = node;


            }
        }

        Scene scene = new Scene(root, paneWidth, paneHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
