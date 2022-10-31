package cellsociety.view;

import static cellsociety.Main.START_SPLASH_CSS;

import cellsociety.controller.CellSocietyController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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

public class FileInput extends SceneCreator {

  public static final String OPEN_DATA_FILE = "Open Data File";
  public static final String SIM_FILES = "SIM Files";
  public static final String GRID_SCREEN_CSS = "gridScreen.css";
  public static final String TITLE_TEXT = "titleText";
  public static final String MAIN_TEXT = "mainText";
  public static final String UPLOAD_GIF = "uploadGif";
  public static final String UPLOAD_BOX = "uploadBox";
  public static final String FILE_UPLOAD_ERROR = "fileUploadError";
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
    Text title = new Text(getResource().getString(TITLE_TEXT));
    title.getStyleClass().add(MAIN_TEXT);

    inputBackground.setImage(new Image(getResource().getString(UPLOAD_GIF)));
    inputBackground.setFitHeight(getMySize());
    inputBackground.setFitWidth(getMySize());
    inputPane.getChildren().addAll(inputBackground);

    VBox upload = new VBox(title);
    for (String button : buttonList) {
      upload.getChildren().add(makeButton(button));
    }
    upload.setAlignment(Pos.CENTER);
    upload.getStyleClass().add(UPLOAD_BOX);
    inputPane.setTop(upload);
    return inputPane;
  }

  /**
   * Make a button and sets properties
   *
   * @param property
   * @return
   */
  protected Button makeButton(String property) {
    Button result = new Button();
    String labelText = getResource().getString(property);
    result.setText(labelText);
    result.setId(property);
    result.getStyleClass().add(BUTTON);
    result.setOnAction(event -> {
      try {
        Method m = this.getClass().getDeclaredMethod(getMyCommands().getString(property));
        m.invoke(this);
      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
        showMessage(Alert.AlertType.ERROR, getResource().getString(e.getCause().getMessage()), e);
      }
    });
    return result;
  }

  private void goBack() {
    SplashScreen beginning = new SplashScreen(600, myStage);
    myStage.setScene(beginning.createScene(START_SPLASH_CSS));
  }

  /**
   * Sets up the file picker
   */
  public void uploadFile() throws IllegalStateException {
    setMyDataFile(FILE_CHOOSER.showOpenDialog(myStage));
    if (getLanguage() != null) {
      CellSocietyController controller = new CellSocietyController(getMyDataFile());
      controller.loadSimulation(myStage);
      GridScreen firstGrid = new GridScreen(800, myStage, controller);
      myStage.setScene(firstGrid.createScene(getLanguage(), GRID_SCREEN_CSS));
    }
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
    result.getExtensionFilters()
        .setAll(new FileChooser.ExtensionFilter(SIM_FILES, extensionAccepted));
    return result;
  }
}

