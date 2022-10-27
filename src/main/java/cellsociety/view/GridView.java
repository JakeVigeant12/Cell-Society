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
  GridWrapper gridStates;

  /**
   * Constructor for GridView, sets up the grid and the cells
   */
  public GridView(CellSocietyController controller) {
    grid = new GridPane();
    myController = controller;
    grid.setId("gridView");
  }

  public void setUpGridViewSize() {
    widthProperty.bind(grid.widthProperty().subtract(50).divide(m));
    heightProperty.bind(grid.heightProperty().subtract(50).divide(n));
    sizeProperty.bind(Bindings.min(widthProperty, heightProperty));
    sizeProperty.addListener((obs, oldVal, newVal) -> {
      updateCellsWidth((Double) newVal);
    });
  }

  public GridWrapper getGridStates() {
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
  public void setUpView(GridWrapper gridData, String simultionGenre) {
    n = gridData.row();
    m = gridData.column(0);
    gridStates = new GridWrapper(n, m);

    cells = new CellView[n][m];
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < m; x++) {
        CellView node = new CellView(gridData.get(y, x), simultionGenre,y, x);
        node.setId("cell" + y + "," + x);
        // add cells to group
        grid.add(node, x, y);
        // add to grid for further reference using an array
        gridStates.set(y, x, node.getState());
        cells[y][x] = node;
        int finalY = y;
        int finalX = x;
        node.isClickedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
              myController.updateOneCell(finalY, finalX, node.getState());
              node.isClickedProperty().set(false);
            }
          }
        );
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
