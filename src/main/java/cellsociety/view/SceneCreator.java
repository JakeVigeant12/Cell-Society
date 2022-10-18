package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneCreator {
    public double mySize;
    public Scene myScene;
    protected Stage stage;
    protected SceneController sceneController;
    public CellSocietyController myController;
    public SceneCreator(double size){
        mySize = size;
    }

    public Scene getScene() {
        return myScene;
    }
}
