package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;


public class GridView {
    GridPane grid;
    private final DoubleProperty widthProperty = new SimpleDoubleProperty();
    private final DoubleProperty heightProperty = new SimpleDoubleProperty();
    private final DoubleProperty sizeProperty = new SimpleDoubleProperty();
    private CellSocietyController myController;
    private int n;
    private int m;
    //the 2D array cells is not refactored into a wrapper class for the time being since it is used only in this class, and will not be passed to other classes.
    private CellView[][] cells;
    final double rem = new Text("").getLayoutBounds().getHeight();
    GridWrapperObservable gridStates;

    /**
     * Constructor for GridView, sets up the grid and the cells
     */
    public GridView(CellSocietyController controller) {
        grid = new GridPane();
        myController = controller;
    }

    public void setUpGridViewSize() {
        widthProperty.bind(grid.widthProperty().subtract(50).divide(m));
        heightProperty.bind(grid.heightProperty().subtract(50).divide(n));
        sizeProperty.bind(Bindings.min(widthProperty, heightProperty));
        sizeProperty.addListener((obs, oldVal, newVal) -> {
            updateCellsWidth((Double) newVal);
        });
    }

    public GridWrapperObservable GetGridWrapperObservable() {
        return gridStates;
    }

    public void updateControllerFromListeners() {
        gridStates.setListener(data -> {
            try {
                myController.update(data);
            } catch (CsvValidationException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public GridWrapperObservable getGridStates() {
        return gridStates;
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
    public void setUpView(GridWrapperObservable gridData, String simultionGenre) {
        n = gridData.row();
        m = gridData.column();
//        gridStates = new GridWrapper(n, m);
        gridStates = new GridWrapperObservable(n, m);

        cells = new CellView[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(gridData.get(y, x), simultionGenre, y, x, myController);
                node.setId("cell" + y + "," + x);
                // add cells to group
                grid.add(node, x, y);
                // add to grid for further reference using an array
                gridStates.set(y, x, node.stateProperty());
                cells[y][x] = node;
            }
        }
    }

    /**
     * Updates the grid
     *
     * @param gridData
     */
    public void updateGrid(GridWrapperObservable gridData) {
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
