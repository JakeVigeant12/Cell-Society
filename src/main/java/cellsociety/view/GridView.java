package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import cellsociety.view.CellView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class GridView{
    GridPane grid;
    private double size;
    private int n;
    private int m;
    CellView[][] cells;
    CellSocietyController controller;
    final double rem = new Text("").getLayoutBounds().getHeight();

    public GridView(double size) {
        this.size = size;
        grid = new GridPane();

        n = 20;
        m = 20;
        int width = (int) Math.min((size - 2 * rem - 250) / n, (size - 1.6 * rem) / m);

        cells = new CellView[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(1, x * width, 50 + y * width, width, Color.AZURE);
                // add cells to group
                grid.add(node, x * width, 50 + y * width);
                // add to grid for further reference using an array
                cells[y][x] = node;
            }
        }
    }

    public void setUpView(List<List<Integer>> gridData) {
        n = gridData.size();
        m = gridData.get(0).size();
        int width = (int) Math.min((size - 2 * rem - 50) / n, (size - 1.6 * rem) / m);

        cells = new CellView[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(gridData.get(y).get(x), x * width, 50 + y * width, width, Color.AZURE);
                // add cells to group
                grid.add(node, x * width, 50 + y * width);
                // add to grid for further reference using an array
                cells[y][x] = node;
            }
        }
    }

    public GridPane getGrid() {
        return grid;
    }
}
