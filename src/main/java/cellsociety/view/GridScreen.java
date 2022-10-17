package cellsociety.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GridScreen extends SceneCreator{

    private Pane gridPane;
    private Button play;
    private Button step;
    private Rectangle about; 

    public GridScreen(double size){
        super(size);
        gridPane = new Pane();
    }

    public Pane createGridScreen(Stage stage){
        play = new Button(">");
        step = new Button(">|");
        HBox controls = new HBox(play,step);
        controls.setLayoutX(130);
        controls.setLayoutY(490);
        controls.getStyleClass().add("allbuttons");
        gridPane.getChildren().addAll(controls);
        return gridPane;
    }

}
