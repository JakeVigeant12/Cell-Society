package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileInput extends SceneCreator {

  public static final String OPEN_DATA_FILE = "Open Data File";
  public static final String SIM_FILES = "SIM Files";
  public static final String GRID_SCREEN_CSS = "gridScreen.css";
  public static final String START_SPLASH_CSS = "startSplash.css";
  public BorderPane inputPane;
  // kind of data files to look for
  public static final String DATA_FILE_SIM_EXTENSION = "*.sim";
  // default to start in the data folder to make it easy on the user to find
  public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
  // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
  public final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_SIM_EXTENSION);

  private ImageView inputBackground;
  private final Stage myStage;
  private final List<String> buttonList = List.of("uploadButton", "backButton");

  /**
   * Constructor for FileInput
   *
   * @param size
   */
  public FileInput(double size, Stage stage) {
    super(size, stage);
    inputPane = new BorderPane();
    inputBackground = new ImageView();
    myStage = stage;
  }

  /**
   * Sets up the file input screen
   *
   * @return
   */
  public Pane setUpRootPane() {
    Text title = new Text(getMyResource().getString("titleText"));
    title.getStyleClass().add("mainText");

    inputBackground.setImage(new Image(getMyResource().getString("uploadGif")));
    inputBackground.setFitHeight(getMySize());
    inputBackground.setFitWidth(getMySize());
    inputPane.getChildren().addAll(inputBackground);

    VBox upload = new VBox(title);
    for(String button : buttonList) {
      upload.getChildren().add(makeButton(button));
    }
    upload.setAlignment(Pos.CENTER);
    upload.getStyleClass().add("uploadBox");
    inputPane.setTop(upload);
    return inputPane;
  }

  private void goBack() {
    SplashScreen beginning = new SplashScreen(600, myStage);
    myStage.setScene(beginning.createScene(START_SPLASH_CSS));
  }

  /**
   * Sets up the file picker
   *
   */
  public void uploadFile() {
    try {
      setMyDataFile(FILE_CHOOSER.showOpenDialog(myStage));
      if (getLanguage() != null) {
        CellSocietyController controller = new CellSocietyController(getMyDataFile());
        controller.loadSimulation(myStage);
        GridScreen firstGrid = new GridScreen(800, myStage, controller);
        myStage.setScene(firstGrid.createScene(getLanguage(), GRID_SCREEN_CSS));
      }
    } catch (IOException | CsvValidationException e) {
      new Alert(AlertType.ERROR, getMyResource().getString("fileUploadError")).showAndWait();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException e) {
      showMessage(AlertType.ERROR, e.getCause().getMessage());
    }
  }

  /**
   * Sets up the alert message
   *
   * @param type
   * @param message
   */
  private void showMessage(Alert.AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  /**
   * Sets up the file chooser
   *
   * @param extensionAccepted
   * @return
   */
  private static FileChooser makeChooser(String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle(OPEN_DATA_FILE);
    // pick a reasonable place to start searching for files
    result.setInitialDirectory(new File(DATA_FILE_FOLDER));
    result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(SIM_FILES, extensionAccepted));
    return result;
  }

  /**
   * Make a button and sets properties
   *
   * @param property
   * @return
   */
  public Button makeButton(String property) {
    Button result = new Button();
    String labelText = getMyResource().getString(property);
    result.setText(labelText);
    result.setId(property);
    result.getStyleClass().add("button");
    result.setOnAction(event -> {
      try {
        Method m = this.getClass().getDeclaredMethod(getMyCommands().getString(property));
        m.invoke(this);
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IllegalStateException e) {
        showMessage(AlertType.ERROR, e.getCause().getMessage());
      }
    });
    return result;
  }
}

