package cellsociety.view;

import static cellsociety.view.StartSplash.DEFAULT_RESOURCE_FOLDER;
import static cellsociety.view.StartSplash.DEFAULT_RESOURCE_PACKAGE;

import cellsociety.controller.CellSocietyController;

import java.awt.Point;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class GridView {
  private GridPane grid;
  private final DoubleProperty widthProperty = new SimpleDoubleProperty();
  private final DoubleProperty heightProperty = new SimpleDoubleProperty();
  private final DoubleProperty sizeProperty = new SimpleDoubleProperty();
  private CellSocietyController myController;
  private IntegerProperty n;
  private int m;
  //the 2D array cells is not refactored into a wrapper class for the time being since it is used only in this class, and will not be passed to other classes.
  private CellView[][] cells;
  final double rem = new Text("").getLayoutBounds().getHeight();
  GridWrapper gridStates;
  private final ColorMap colors;
  private final ImageMap images;
  private boolean isUsingColors;
  private final ResourceBundle resourceBundle = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, "CellView"));

  /**
   * Constructor for GridView, sets up the grid and the cells
   */
  public GridView(CellSocietyController controller) {
    grid = new GridPane();
    myController = controller;
    grid.setId("gridView");
    Properties properties = controller.getProperties();
    colors = new ColorMap();
    images = new ImageMap();
    applyColors(properties);
  }

  private void applyColors(Properties properties) {
    if (properties.containsKey("StateImages")) {
      for (String imageString : properties.get("StateImages").toString().split(",")) {
        Image image = new Image(DEFAULT_RESOURCE_FOLDER + imageString);
        images.addImage(image);
        isUsingColors = false;
      }
    } else if (properties.containsKey("StateColors")) {
      for (String colorString : properties.get("StateColors").toString().split(",")) {
        Color color = Color.web(colorString);
        colors.addColor(color);
        isUsingColors = true;
      }
    } else {
      for (String colorString : resourceBundle.getString(String.format("%sStateColors", properties.get("Type"))).split(",")) {
        Color color = Color.web(colorString);
        colors.addColor(color);
        isUsingColors = true;
      }
    }
  }

  public void enlarge() {
    n.set(n.get() + 1);
    CellView node;
    for (int x = 0; x < m; x++) {
      if (isUsingColors)
        node = new CellView(0, colors);
      else
        node = new CellView(0, images);
//      node.setId("cell" + n + "," + x);
      // add cells to group
      grid.add(node, x, n.get() - 1);
      // add to grid for further reference using an array
//      gridStates.setState(n + 1, x, node.getState());
//      cells[n + 1][x] = node;
      int finalX = x;
      CellView finalNode = node;
      updateCellSize(finalNode, sizeProperty.get());
      sizeProperty.addListener((obs, oldVal, newVal) -> {
        updateCellSize(finalNode, (Double)newVal);
//        System.out.println(newVal);
      });
      setCellListener(node, new Point(x, n.get() - 1));
    }
  }

  public void setUpGridViewSize() {
    widthProperty.bind(grid.widthProperty().subtract(50).divide(m));
    heightProperty.bind(grid.heightProperty().subtract(50).divide(n));
    sizeProperty.bind(Bindings.min(widthProperty, heightProperty));
    sizeProperty.addListener((obs, oldVal, newVal) -> {
//      System.out.println(newVal);
      updateCellsWidth((Double) newVal);
    });
  }

  public void updateCellSize (CellView cellView, double size) {
    cellView.updateSize(size);
  }

  public GridWrapper getGridStates() {
    return gridStates;
  }

  public void updateCellsWidth(double size) {
    for (int y = 0; y < 8; y++) {
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
    n = new SimpleIntegerProperty(gridData.getColumnSize());
    m = gridData.getRowSize(0);
    gridStates = new GridWrapper(n.get(), m);

    cells = new CellView[n.get()][m];
    for (int y = 0; y < n.get(); y++) {
      for (int x = 0; x < m; x++) {
        CellView node;
        if (isUsingColors)
          node = new CellView(gridData.getState(y, x), colors);
        else
          node = new CellView(gridData.getState(y, x), images);
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
    for (int y = 0; y < n.get(); y++) {
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
