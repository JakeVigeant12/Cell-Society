package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.beans.binding.Bindings;

public class GridViewHexagon extends GridView {
  /**
   * Constructor for GridView, sets up the grid and the cells
   *
   * @param controller
   */
  public GridViewHexagon(CellSocietyController controller) {
    super(controller);
  }

  @Override
  protected void createCell(GridWrapper gridData, int y, int x) {
    CellView node;
    if (isUsingColors()) {
      node = new CellViewHexagon(gridData.getState(y, x), getColors());
    } else {
      node = new CellViewHexagon(gridData.getState(y, x), getImages());
    }
    if (y % 2 == 0) {
      node.setTranslateX(18);
    }
    node.setTranslateY(-10 * y);
    node.updateSize(20);
    if (isSetBorder()) {
      node.showBorder();
    }
    node.setId(CELL + y + REGEX + x);
    // add cells to group
    getGrid().add(node, x, y);
    // add to grid for further reference using an array
    gridStates.setState(y, x, node.getState());
    getCells().get(y).add(node);
    node.setOnMouseClicked(e -> {
      node.setOnClick();
      getController().updateOneCell(y, x, node.getState());
    });
  }

  @Override
  public void setUpGridViewSize() {
    getWidthProperty().bind(getGrid().widthProperty().subtract(50).divide(getColumn()));
    getHeightProperty().bind(getGrid().heightProperty().subtract(150).divide(getRow()));
    getSizeProperty().bind(Bindings.min(getWidthProperty().divide(2), getHeightProperty().divide(2)));
    int rows = getRow().get();
    int cols = getColumn().get();
    getSizeProperty().addListener((obs, oldVal, newVal) -> {
      for (int y = 0; y < rows; y++) {
        for (int x = 0; x < cols; x++) {
          updateCellWidth(x, y, (Double) newVal);
          if (y % 2 == 0)
            getCells().get(y).get(x).setTranslateX(((Double)newVal/20) * 18);
          getCells().get(y).get(x).setTranslateY(-((Double)newVal/20) * 10 * y);
        }
      }
    });
  }

}
