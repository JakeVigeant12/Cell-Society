package cellsociety.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GridScreen extends SceneCreator{

    private Pane gridPane;
    private Button play;
    private Button step;

    public GridScreen(double size){
        super(size);
        gridPane = new Pane();
    }

    public Pane createGridScreen(Stage stage){
        play = new Button(">");
        step = new Button(">|");
        gridPane.getChildren().addAll(play, step);
        return gridPane;

    }

}
