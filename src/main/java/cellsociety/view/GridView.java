package cellsociety.view;

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


public class GridView {
    GridPane grid;
    private final DoubleProperty widthProperty = new SimpleDoubleProperty();
    private final DoubleProperty heightProperty = new SimpleDoubleProperty();
    private final DoubleProperty sizeProperty = new SimpleDoubleProperty();
    private int n;
    private int m;
    //the 2D array cells is not refactored into a wrapper class for the time being since it is used only in this class, and will not be passed to other classes.
    private CellView[][] cells;
    final double rem = new Text("").getLayoutBounds().getHeight();
    private GridWrapper gridStates;
    private ObservableList<ObservableList<IntegerProperty>> grids = FXCollections.observableArrayList();
    private ObservableList<IntegerProperty> gg = FXCollections.observableArrayList();
    ObservableList<IntegerProperty> list = FXCollections.observableArrayList(
            item -> new Observable[] {item});

    /**
     * Constructor for GridView, sets up the grid and the cells
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

    public void setUpGridFeedBack() {
        list.addListener((ListChangeListener.Change<? extends IntegerProperty> c) -> {
            while (c.next()) {
                if (c.wasUpdated()) {
                    System.out.println("Items from "+c.getFrom()+" to "+c.getTo()+" changed");
                }
            }
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
    public void setUpView(GridWrapper gridData, String simultionGenre) {
        n = gridData.row();
        m = gridData.column();
//        gridStates = new GridWrapper(n, m);

        cells = new CellView[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(gridData.get(y, x), simultionGenre);
                // add cells to group
                grid.add(node, x, y);
                // add to grid for further reference using an array
//                gridStates.set(y, x, gridData.get(y, x));
//                gridStates.get(y, x).bind(node.stateProperty());
                list.add(node.stateProperty());
                cells[y][x] = node;
            }
        }
        setUpGridFeedBack();
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
