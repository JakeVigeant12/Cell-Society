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

    private Stage myStage;
    /**
     * Constructor for SceneCreator
     *
     * @param size
     */
    public SceneCreator(double size, Stage stage) {
        myStage = stage;
        mySize = size;
    }

    /**
     * Sets up the scene
     *
     * @param css
     * @return
     */
    public Scene createScene(String css) {
//        previousScene = stage.getScene();
        Scene scene = new Scene(setScene(), mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    public Scene createScene(String language, String css) {
//        previousScene = stage.getScene();
        this.language = language;
        myResource = ResourceBundle.getBundle(language);
        Scene scene = new Scene(setScene(), mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    protected Pane setScene() {
        return root;
    }

    /**
     * Sets a new stage to display
     *
     */
    public void nextScreen() {
        myStage.setScene(myScene);
        myStage.setHeight(mySize);
        myStage.setWidth(mySize);
        myStage.show();
    }

    /**
     * Sets the previous scene to display
     *
     */
    public void previousScreen() {
        myStage.setScene(previousScene);
        myStage.show();
    }
}
