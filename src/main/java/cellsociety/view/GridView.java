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

  public static final String GRID_VIEW = "gridView";
  public static final String STATE_IMAGES = "StateImages";
  public static final String STATE_COLORS = "StateColors";
  public static final String TYPE = "Type";
  public static final String REGEX = ",";
  public static final String CELL = "cell";
  private GridPane grid;
  private final DoubleProperty widthProperty = new SimpleDoubleProperty();
  private final DoubleProperty heightProperty = new SimpleDoubleProperty();
  private final DoubleProperty sizeProperty = new SimpleDoubleProperty();
  private CellSocietyController myController;
  private IntegerProperty row;
  private IntegerProperty column;
  //the 2D array cells is not refactored into a wrapper class for the time being since it is used only in this class, and will not be passed to other classes.
  private List<List<CellView>> cells;
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
    grid.setId(GRID_VIEW);
    Properties properties = controller.getProperties();
    cells = new ArrayList<>();
    colors = new ColorMap();
    images = new ImageMap();
    applyColors(properties);
  }

  private void applyColors(Properties properties) {
    if (properties.containsKey(STATE_IMAGES)) {
      for (String imageString : properties.get(STATE_IMAGES).toString().split(REGEX)) {
        Image image = new Image(DEFAULT_RESOURCE_FOLDER + imageString);
        images.addImage(image);
        isUsingColors = false;
      }
    } else if (properties.containsKey(STATE_COLORS)) {
      for (String colorString : properties.get(STATE_COLORS).toString().split(REGEX)) {
        Color color = Color.web(colorString);
        colors.addColor(color);
        isUsingColors = true;
      }
    } else {
      for (String colorString : resourceBundle.getString(String.format("%s%s", properties.get(TYPE),STATE_COLORS)).split(
          REGEX)) {
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
        for (int x = 0; x < cols; x++) {
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
        node.setId(CELL + y + REGEX + x);
        // add cells to group
        grid.add(node, x, y);
        // add to grid for further reference using an array
        gridStates.setState(y, x, node.getState());
        cells.get(y).add(node);
        int finalX = x;
        int finalY = y;
        node.setOnMouseClicked(e -> {
          node.setOnClick();
          myController.updateOneCell(finalY, finalX, node.getState());
        });
      }
    }
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
