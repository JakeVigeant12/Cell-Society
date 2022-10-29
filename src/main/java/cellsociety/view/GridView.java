package cellsociety.view;

import static cellsociety.view.SplashScreen.DEFAULT_RESOURCE_FOLDER;
import static cellsociety.view.SplashScreen.DEFAULT_RESOURCE_PACKAGE;

import cellsociety.controller.CellSocietyController;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class GridView {
  private GridPane grid;
  private final DoubleProperty widthProperty = new SimpleDoubleProperty();
  private final DoubleProperty heightProperty = new SimpleDoubleProperty();
  private final DoubleProperty sizeProperty = new SimpleDoubleProperty();
  private CellSocietyController myController;
  private IntegerProperty row;
  private IntegerProperty column;
  //the 2D array cells is not refactored into a wrapper class for the time being since it is used only in this class, and will not be passed to other classes.
  private List<List<CellView>> cells;
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
    cells = new ArrayList<>();
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

  public void setUpGridViewSize() {
    widthProperty.bind(grid.widthProperty().subtract(50).divide(column));
    heightProperty.bind(grid.heightProperty().subtract(50).divide(row));
    sizeProperty.bind(Bindings.min(widthProperty, heightProperty));
    int rows = row.get();
    int cols = column.get();
    sizeProperty.addListener((obs, oldVal, newVal) -> {
      for (int y = 0; y < rows; y++) {
        for (int x = 0; x <cols; x++) {
          updateCellWidth(x, y, (Double) newVal);
        }
      }
    });
  }
  public GridWrapper getGridStates() {
    return gridStates;
  }

  public void updateCellWidth(int x, int y, double size) {
    cells.get(y).get(x).updateSize(size);
  }


  /**
   * Sets up the grid and the cells
   *
   * @param gridData
   */
  public void setUpView(GridWrapper gridData) {
    row = new SimpleIntegerProperty(gridData.getRowCount());
    column = new SimpleIntegerProperty(gridData.getRowSize(0));
    gridStates = new GridWrapper(row.get(), column.get());

    for (int y = 0; y < row.get(); y++) {
      cells.add(new ArrayList<>());
      for (int x = 0; x < column.get(); x++) {
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
        cells.get(y).add(node);

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
    for (int y = 0; y < row.get(); y++) {
      for (int x = 0; x < column.get(); x++) {
        cells.get(y).get(x).updateState(gridData.getState(y, x));
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
