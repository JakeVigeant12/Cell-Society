package cellsociety;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneCreator {
    public double mySize;
    public SceneCreator(double size){
        mySize = size;
    }

    public Scene createScene(Stage stage, Pane myPane, String css){
        Scene scene  = new Scene(myPane, mySize, mySize);
        scene.getStylesheets().add(css);
        return scene;
    }

    public static void nextScreen(Stage stage, Scene scene){
        stage.setScene(scene);
        stage.show();
    }
}
