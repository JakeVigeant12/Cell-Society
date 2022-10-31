package cellsociety.view;

import static cellsociety.view.SplashScreen.DEFAULT_RESOURCE_FOLDER;
import static cellsociety.view.SplashScreen.DEFAULT_RESOURCE_PACKAGE;

import cellsociety.controller.CellSocietyController;

import java.util.*;

import cellsociety.model.GridWrapper;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public abstract class GridView {

  public static final String GRID_VIEW = "gridView";
  public static final String STATE_IMAGES = "StateImages";
  public static final String STATE_COLORS = "StateColors";
  public static final String TYPE = "Type";
  public static final String REGEX = ",";
  public static final String CELL = "Cell";
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
  private final boolean isSetBorder;
  public static final ResourceBundle CELL_VIEW_RESOURCES = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, "CellView"));
  private HashSet<Integer> stateTypes = new HashSet<>();
  private HashMap<Integer, Integer> allCurrentStates = new HashMap<>();

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
    if (properties.containsKey("Outlined")) {
      isSetBorder = Boolean.parseBoolean((String) properties.get("Outlined"));
    } else {
      isSetBorder = true;
    }
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
      for (String colorString : CELL_VIEW_RESOURCES.getString(String.format("%s%s", properties.get(TYPE), STATE_COLORS)).split(
        REGEX)) {
        Color color = Color.web(colorString);
        colors.addColor(color);
        isUsingColors = true;
      }
    }
  }

  /**
   * set up the grid size so that cells size listen to the size of the gridPane
   */
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
        createCell(gridData, y, x);
      }
    }
    updateHash();
    setCurrentStatesData(gridData);
  }

  protected void createCell(GridWrapper gridData, int y, int x) {
    CellView node;
    if (isUsingColors) {
      node = new CellViewSquare(gridData.getState(y, x), colors);
    } else {
      node = new CellViewSquare(gridData.getState(y, x), images);
    }
    if (isSetBorder) {
      node.showBorder();
    }
    node.setId(CELL + y + REGEX + x);
    // add cells to group
    grid.add(node, x, y);
    // add to grid for further reference
    gridStates.setState(y, x, node.getState());
    cells.get(y).add(node);
    node.setOnMouseClicked(e -> {
      node.setOnClick();
      myController.updateOneCell(y, x, node.getState());
    });
    stateTypes.add(node.getState());
  }

  /**
   * Updates the grid
   *
   * @param gridData
   */
  public void updateGrid(GridWrapper gridData) {
    updateHash();
    for (int y = 0; y < row.get(); y++) {
      for (int x = 0; x < column.get(); x++) {
        cells.get(y).get(x).updateState(gridData.getState(y, x));
      }
    }
    setCurrentStatesData(gridData);
  }

  protected ColorMap getColors() {
    return colors;
  }

  protected ImageMap getImages() {
    return images;
  }

  protected Boolean isUsingColors() {
    return isUsingColors;
  }

  protected Boolean isSetBorder() {
    return isSetBorder;
  }

  protected List<List<CellView>> getCells() {
    return cells;
  }

  protected CellSocietyController getController() {
    return myController;
  }

  protected DoubleProperty getWidthProperty() {
    return widthProperty;
  }

  protected DoubleProperty getHeightProperty() {
    return heightProperty;
  }

  protected DoubleProperty getSizeProperty() {
    return sizeProperty;
  }

  protected IntegerProperty getRow() {
    return row;
  }

  protected IntegerProperty getColumn() {
    return column;
  }

  /**
   * Returns the grid
   *
   * @return grid
   */
  public GridPane getGrid() {
    return grid;
  }

  //updates all the values to 0
  private void updateHash() {
    for (Integer n : stateTypes) {
      allCurrentStates.put(n, 0);
    }
  }

  //sets the Hashmap with the correct data of the current cell states
  private void setCurrentStatesData(GridWrapper gridData) {
    for (int y = 0; y < row.get(); y++) {
      for (int x = 0; x < column.get(); x++) {
        allCurrentStates.put(gridData.getState(y, x), allCurrentStates.get(gridData.getState(y, x)) + 1);
      }
    }
  }

  /**
   * Returns the HashMap with current state numbers
   *
   * @return allCurrentStates
   */
  public HashMap getCurrentStates() {
    return allCurrentStates;
  }

  protected HashSet<Integer> getStateTypes() {
    return stateTypes;
  }
}



