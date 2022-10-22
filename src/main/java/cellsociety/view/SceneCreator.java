package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;


public abstract class SceneCreator {
    public double mySize;
    public Scene myScene;
    public Scene previousScene;
    public CellSocietyController myController;
    protected File myDataFile;
    protected Pane root;
    protected String language;
    protected ResourceBundle myResource;

    /**
     * Constructor for SceneCreator
     *
     * @param size
     */
    public SceneCreator(double size) {
        mySize = size;
    }

    /**
     * Sets up the scene
     *
     * @param stage
     * @param css
     * @return
     */
    public Scene createScene(Stage stage, String css) {
//        previousScene = stage.getScene();
        Scene scene = new Scene(setScene(stage), mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    public Scene createScene(Stage stage, String language, String css) {
//        previousScene = stage.getScene();
        this.language = language;
        myResource = ResourceBundle.getBundle(language);
        Scene scene = new Scene(setScene(stage), mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    protected Pane setScene(Stage stage) {
        return root;
    }

    /**
     * Sets a new stage to display
     *
     * @param stage
     */
    public void nextScreen(Stage stage) {
        stage.setScene(myScene);
        stage.setHeight(mySize);
        stage.setWidth(mySize);
        stage.show();
    }

    /**
     * Sets the previous scene to display
     *
     * @param stage
     */
    public void previousScreen(Stage stage) {
        stage.setScene(previousScene);
        stage.show();
    }
}
