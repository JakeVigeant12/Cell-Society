package cellsociety.view;

import static cellsociety.view.SplashScreen.DEFAULT_RESOURCE_PACKAGE;

import cellsociety.controller.CellSocietyController;

import java.awt.Point;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


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
  private final String[] stateStyles;
  private final ResourceBundle resourceBundle = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, "CellView"));

  /**
   * Constructor for GridView, sets up the grid and the cells
   */
  public GridView(CellSocietyController controller) {
    grid = new GridPane();
    myController = controller;
    grid.setId("gridView");
    Properties properties = controller.getProperties();
    if(properties.containsKey("StateImages")) {
      stateStyles = properties.get("StateImages").toString().split(",");
    } else if (properties.containsKey("StateColors")) {
      stateStyles = properties.get("StateColors").toString().split(",");
    } else {
      stateStyles = resourceBundle.getString(String.format("%sStateColors", properties.get("Type"))).split(",");
    }
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
  public void setUpView(GridWrapper gridData) {
    n = gridData.getColumnSize();
    m = gridData.getRowSize(0);
    gridStates = new GridWrapper(n, m);

    cells = new CellView[n][m];
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < m; x++) {
        CellView node = new CellView(gridData.getState(y, x),
            myController.getProperties(), y, x, stateStyles);
        node.setId("cell" + y + "," + x);
        // add cells to group
        grid.add(node, x, y);
        // add to grid for further reference using an array
        gridStates.setState(y, x, node.getState());
        cells[y][x] = node;

        setCellListener(node, new Point(x, y));
      }
    }
  }

  private void setCellListener(CellView node, Point point) {
    node.isClickedProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal) {
          myController.updateOneCell(point.y, point.x, node.getState());
          node.isClickedProperty().set(false);
        }
      }
    );
  }

  /**
   * Updates the grid
   *
   * @param gridData
   */
  public void updateGrid(GridWrapper gridData) {
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < m; x++) {
        cells[y][x].updateState(gridData.getState(y, x));
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
