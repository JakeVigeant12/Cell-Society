package cellsociety.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.GridPane;

public class GridView {
    GridPane grid;
    private final DoubleProperty widthProperty = new SimpleDoubleProperty();
    private final DoubleProperty heightProperty = new SimpleDoubleProperty();
    private final DoubleProperty sizeProperty = new SimpleDoubleProperty();
    private int n;
    private int m;
    //the 2D array cells is not refactored into a wrapper class for the time being since it is used only in this class, and will not be passed to other classes.
    private CellView[][] cells;

    /**
     * Constructor for GridView, sets up the grid and the cells
     *
     */
    public GridView() {
        grid = new GridPane();
    }

    public void setUpGridViewSize() {
        widthProperty.bind(grid.widthProperty().subtract(50).divide(m));
        heightProperty.bind(grid.heightProperty().subtract(50).divide(n));
        sizeProperty.bind(Bindings.min(widthProperty, heightProperty));
        sizeProperty.addListener((obs, oldVal, newVal) -> {
            updateCellsWidth((Double) newVal);
        });
    }

    public void updateCellsWidth(double size) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                cells[y][x].updateSize(size);
            }
        }
    }


    /**
     * Sets up the grid and the cells
     *
     * @param gridData
     */
    public void setUpView(GridWrapper gridData, String simulationGenre) {
        n = gridData.row();
        m = gridData.column();

        cells = new CellView[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(gridData.get(y, x), simulationGenre);
                // add cells to group
                grid.add(node, x, y);
                // add to grid for further reference using an array
                cells[y][x] = node;
            }
        }
    }

    /**
     * Updates the grid
     *
     * @param gridData
     */
    public void updateGrid(GridWrapper gridData) {
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                cells[y][x].updateState(gridData.get(y, x));
            }
        }
    }

    /**
     * Returns the grid
     *
     * @return grid
     */
    public GridPane getGrid() {
        return grid;
    }
}
