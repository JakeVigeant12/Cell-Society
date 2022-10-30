package cellsociety.view;

import static cellsociety.Main.CELL_SOCIETY;
import static cellsociety.Main.START_SPLASH_CSS;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ResourceBundle;

public class SplashScreen extends SceneCreator {

  public static final String FILE_INPUT_CSS = "fileInput.css";
  public static final String TEAM_10 = "Team 10";
  public static final String MAIN_TITLE = "mainTitle";
  public static final String SELECT_LANGUAGE = "Select Language";
  public static final String START_SELECT_LANGUAGE = "startSelectLanguage";
  public static final String START_GIF = "startGif";
  public static final String ALL_BUTTONS = "allButtons";
  public static final String ENGLISH = "English";
  public static final String SPANISH = "Spanish";
  public static final String FRENCH = "French";
  private final List<String> buttonList = List.of("englishButton", "spanishButton", "frenchButton", "newWindowScreenButton");
  public BorderPane startPane;
  private final ImageView myBackground;
  private final Stage myStage;

  public static final String DEFAULT_RESOURCE_PACKAGE = SplashScreen.class.getPackageName() + ".";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  ResourceBundle startInfo = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "startInfo");


  public SplashScreen(double size, Stage stage) {
    super(size, stage);
    myStage = stage;
    startPane = new BorderPane();
    myBackground = new ImageView();
    myStage.setMinWidth(200);
    myStage.setMinHeight(200);
  }

  public Pane setUpRootPane() {

    mainTitle = new Text(TEAM_10);
    mainTitle.getStyleClass().add(MAIN_TITLE);
    VBox vBoxTop = new VBox(mainTitle);
    vBoxTop.setAlignment(Pos.CENTER);


    selectLanguage = new Text(SELECT_LANGUAGE);
    selectLanguage.getStyleClass().add(START_SELECT_LANGUAGE);


    myBackground.setImage(new Image(startInfo.getString(START_GIF)));
    myBackground.setFitWidth(getMySize());
    myBackground.setFitHeight(getMySize());

    HBox buttons = new HBox();
    for(String button : buttonList) {
      buttons.getChildren().add(makeButton(button));
    }
    buttons.getStyleClass().add(ALL_BUTTONS);

    VBox vBoxBot = new VBox(selectLanguage, buttons);
    vBoxBot.setSpacing(20);
    vBoxBot.setAlignment(Pos.CENTER);

    startPane.getChildren().add(myBackground);
    startPane.setTop(vBoxTop);
    startPane.setBottom(vBoxBot);
    startPane.setPadding(new Insets(25));

    return startPane;
  }

  public Button makeButton(String property) {
    Button result = new Button();
    String label = startInfo.getString(property);
    result.setText(label);
    result.setId(property);
    result.setOnAction(event -> {
      try {
        Method m = this.getClass().getDeclaredMethod(getMyCommands().getString(property));
        m.invoke(this);
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IllegalStateException e) {
        new Alert(AlertType.ERROR, e.getCause().getMessage());
      }
    });
    return result;
  }

  private void createEnglishScreen() {
    createLanguageScreen(ENGLISH);
  }

  private void createSpanishScreen() {
    createLanguageScreen(SPANISH);
  }

  private void createFrenchScreen() {
    createLanguageScreen(FRENCH);
  }

  private void createLanguageScreen(String property) {
    FileInput fileInput = new FileInput(getMySize(), myStage);
    myStage.setScene(fileInput.createScene(property, FILE_INPUT_CSS));
  }

  private void openNewWindow() {
    // New window (Stage)
    Stage newStage = new Stage();
    newStage.setTitle(CELL_SOCIETY);
    SplashScreen newSplashScreen = new SplashScreen(600.0, newStage);
    newStage.setScene(newSplashScreen.createScene(START_SPLASH_CSS));

    // Set position of second window, related to primary window.
    newStage.setX(myStage.getX() + 200);
    newStage.setY(myStage.getY() + 100);

    newStage.show();
  }
}
