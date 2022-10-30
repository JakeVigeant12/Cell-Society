package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;


public abstract class SceneCreator {
  private double mySize;
  private File myDataFile;
  private String language;
  private ResourceBundle myResource;
  private ResourceBundle myCommands;
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
    Scene scene = new Scene(setUpRootPane(), mySize, mySize);
    scene.getStylesheets().add(css);
    return scene;
  }

  public Scene createScene(String language, String css) {
    this.language = language;
    myResource = ResourceBundle.getBundle(language);
    myCommands = ResourceBundle.getBundle(String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, COMMAND_PROPERTIES));
    Scene scene = new Scene(setUpRootPane(), mySize, mySize);
    scene.getStylesheets().add(css);
    return scene;
  }

  protected Pane setUpRootPane() {
    return new Pane();
  }

  protected File getMyDataFile() {
    return myDataFile;
  }

  protected void setMyDataFile(File myDataFile) {
    if (myDataFile == null)
      throw new IllegalStateException();
    this.myDataFile = myDataFile;
  }

  protected double getMySize() {
    return mySize;
  }

  protected void setMySize(double size) {
    if (size <= 0)
      throw new IllegalStateException();
    mySize = size;
  }

  protected String getLanguage() {
    return language;
  }

  protected ResourceBundle getMyResource() {
    return myResource;
  }

  protected ResourceBundle getMyCommands() {
    return myCommands;
  }


}
