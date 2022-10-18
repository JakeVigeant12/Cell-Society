package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SceneCreator {
    public double mySize;
    public Scene myScene;
    public Scene previousScene;

    public CellSocietyController myController;
    public SceneCreator(double size){
        mySize = size;
    }


    public Scene createScene(Stage stage, Pane myPane, String css){
        previousScene = stage.getScene();
        Scene scene  = new Scene(myPane, mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    public void nextScreen(Stage stage){
        stage.setScene(myScene);
        stage.setHeight(mySize);
        stage.setWidth(mySize);
        stage.show();
    }

    public void previousScreen(Stage stage){
        stage.setScene(previousScene);
        stage.show();
    }

//    public void nextScreen(Stage stage){
//        stage.setScene(myScene);
//        stage.show();
//    }
}
