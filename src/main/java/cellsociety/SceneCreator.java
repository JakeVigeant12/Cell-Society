package cellsociety;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneCreator {
    public double mySize;
    public Scene myScene;
    public SceneCreator(double size){
        mySize = size;
    }

    public Scene createScene(Stage stage, Pane myPane, String css){
        Scene scene  = new Scene(myPane, mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    public Scene createScene(Stage stage, Pane myPane){
        Scene scene  = new Scene(myPane, mySize, mySize);
        myScene = scene;
        return scene;
    }

//    public void nextScreen(Stage stage){
//        stage.setScene(myScene);
//        stage.show();
//    }
}
