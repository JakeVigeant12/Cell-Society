package cellsociety.view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

public abstract class SceneCreator {

  private double mySize;
  private File myDataFile;
  private String language;
  private ResourceBundle myResource;
  private ResourceBundle myCommands = ResourceBundle.getBundle(
      String.format("%s%s", DEFAULT_RESOURCE_PACKAGE, COMMAND_PROPERTIES));
  public static final String COMMAND_PROPERTIES = "Command";
  public static final String BUTTON = "button";
  public static final String DEFAULT_RESOURCE_PACKAGE = String.format("%s.",
      SceneCreator.class.getPackageName());
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
      throw new IllegalStateException("noFileInput");
    this.myDataFile = myDataFile;
  }

  protected double getMySize() {
    return mySize;
  }

  /**
   * Sets up the alert message
   *
   * @param type
   * @param message
   */
  protected void showMessage(Alert.AlertType type, String message, Exception e) {
    new Alert(type, message).showAndWait();
  }

  protected String getLanguage() {
    return language;
  }

  protected ResourceBundle getResource() {
    return myResource;
  }

  protected void setResource(ResourceBundle myResource) {
    this.myResource = myResource;
  }

  protected ResourceBundle getMyCommands() {
    return myCommands;
  }


}
