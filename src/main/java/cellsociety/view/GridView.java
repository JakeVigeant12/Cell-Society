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
    final double rem = new Text("").getLayoutBounds().getHeight();

    /**
     * Constructor for GridView, sets up the grid and the cells
     * @param size
     */
    public GridView(double size) {
        this.size = size;
        grid = new GridPane();
    }

    /**
     * Sets up the grid and the cells
     * @param gridData
     */
    public void setUpView(List<List<Integer>> gridData, String simultionGenre) {
        n = gridData.size();
        m = gridData.get(0).size();
        int width = (int) Math.min(600 / n, 570 / m);

        cells = new CellView[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(gridData.get(y).get(x), width, simultionGenre);
                // add cells to group
                grid.add(node, x * width, 50 + y * width);
                // add to grid for further reference using an array
                cells[y][x] = node;
            }
        }
    }

    /**
     * Updates the grid
     * @param gridData
     */
    public void updateGrid(List<List<Integer>> gridData) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                cells[y][x].updateState(gridData.get(y).get(x));
            }
        }
    }

    /**
     * Returns the grid
     * @return grid
     */
    public GridPane getGrid() {
        return grid;
    }
}
