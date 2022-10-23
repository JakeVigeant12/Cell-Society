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
    protected ResourceBundle myCommands;
    private static final String COMMAND_PROPERTIES = "Command";
    public static final String DEFAULT_RESOURCE_PACKAGE = String.format("%s.", SceneCreator.class.getPackageName());

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
        Scene scene = new Scene(setUpRootPane(), mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    public Scene createScene(String language, String css) {
//        previousScene = stage.getScene();
        this.language = language;
        myResource = ResourceBundle.getBundle(language);
        myCommands = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, COMMAND_PROPERTIES));;
        Scene scene = new Scene(setUpRootPane(), mySize, mySize);
        scene.getStylesheets().add(css);
        myScene = scene;
        return scene;
    }

    protected Pane setUpRootPane() {
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
