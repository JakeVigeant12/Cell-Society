package cellsociety.model.grids;

import cellsociety.view.GridWrapper;
import java.util.Properties;

public class RockPaperScissorGraphGrid extends GraphGrid{

  /**
   * Constructor for GraphGrid class
   *
   * @param gridParsing is the layout of the grid
   * @param properties
   */
  public RockPaperScissorGraphGrid(GridWrapper gridParsing,
      Properties properties) {
    super(gridParsing, properties);
  }
}
