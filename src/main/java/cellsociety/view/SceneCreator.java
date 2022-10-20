package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;


public class SceneCreator {
    public double mySize;
    public Scene myScene;
    public Scene previousScene;
    public CellSocietyController myController;
    protected File myDataFile;

    /**
     * Constructor for SceneCreator
     * @param size
     */
    public SceneCreator(double size){
        mySize = size;
    }

    /**
     * Sets up the scene
     * @param stage
     * @param myPane
     * @param css
     * @return
     */
    public Scene createScene(Stage stage, Pane myPane, String css){
        previousScene = stage.getScene();
        Scene scene  = new Scene(myPane, mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    /**
     * Sets a new stage to display
     * @param stage
     */
    public void nextScreen(Stage stage){
        stage.setScene(myScene);
        stage.setHeight(mySize);
        stage.setWidth(mySize);
        stage.show();
    }

    /**
     * Sets the previous scene to display
     * @param stage
     */
    public void previousScreen(Stage stage){
        stage.setScene(previousScene);
        stage.show();
    }
}
