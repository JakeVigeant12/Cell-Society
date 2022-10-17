package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import cellsociety.view.CellView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


public class GridView extends SceneCreator {
    GridPane grid;
    private double size;
    private int n;
    private int m;
    BorderPane root;
    CellView[][] cells;
    CellSocietyController controller;
    final double rem = new Text("").getLayoutBounds().getHeight();

    public GridView(double size, CellSocietyController controller) {
        super(size);
        root = new BorderPane();
        this.size = size;
        grid = new GridPane();
        this.controller = controller;
        grid.setPadding(new Insets(0.8 * rem));
        grid.setGridLinesVisible(true);
        setUpButton();
    }

    public BorderPane setUpView(List<List<String>> gridData) {
        n = gridData.size();
        m = gridData.get(0).size();
        int width = (int) Math.min((size - 2 * rem - 50) / n, (size - 1.6 * rem) / m);

        cells = new CellView[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                CellView node = new CellView(gridData.get(y).get(x), x * width, 50 + y * width, width, Color.AZURE);
                // add cells to group
                grid.add(node, x * width, 50 + y * width);
                // add to grid for further reference using an array
                cells[y][x] = node;
            }
        }
        root.setBottom(grid);
        return root;
    }

    private void setUpButton() {

        Button nextButton = new Button("Next");
        nextButton.setPrefWidth(50);
        HBox hBox = new HBox(nextButton);
        hBox.setPadding(new Insets(0.8 * rem));
        root.setTop(hBox);
        //TODO: updateGrid method in controller
        nextButton.setOnAction(e -> controller.updateGrid());
    }

}
