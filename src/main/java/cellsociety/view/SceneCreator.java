package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneCreator {
    public double mySize;
    public Scene myScene;
    protected Stage stage;
    protected CellSocietyController cellSocietyController;
    public SceneCreator(double size){
        mySize = size;
    }

    public Scene createScene(Stage stage, Pane myPane, String css){
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

//    public void nextScreen(Stage stage){
//        stage.setScene(myScene);
//        stage.show();
//    }
}
