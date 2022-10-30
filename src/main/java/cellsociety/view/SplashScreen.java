package cellsociety.view;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
  private final List<String> languageList = List.of("English", "Spanish", "French");
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

    Text mainTitle = new Text("Team 10");
    mainTitle.getStyleClass().add("mainTitle");
    VBox vBoxTop = new VBox(mainTitle);
    vBoxTop.setAlignment(Pos.CENTER);


    Text selectLanguage = new Text("Select Language");
    selectLanguage.getStyleClass().add("startSelectLanguage");


    myBackground.setImage(new Image(startInfo.getString("startGif")));
    myBackground.setFitWidth(getMySize());
    myBackground.setFitHeight(getMySize());

    HBox buttons = new HBox();
    for(String language : languageList) {
      buttons.getChildren().add(makeButton(language));
    }
    buttons.getStyleClass().add("allButtons");

    Button newWindow = new Button("New Window");
    newWindow.setOnAction(e -> openNewWindow());
    newWindow.setId("newWindowButton");
    buttons.getChildren().add(newWindow);
    //TODO: Create this button with reflection

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
      FileInput fileInput = new FileInput(getMySize(), myStage);
      myStage.setScene(fileInput.createScene(property, FILE_INPUT_CSS));
    });
    return result;
  }

  private void openNewWindow() {
    // New window (Stage)
    Stage newStage = new Stage();
    newStage.setTitle("CellSociety");
    SplashScreen newSplashScreen = new SplashScreen(600.0, newStage);
    newStage.setScene(newSplashScreen.createScene("startSplash.css"));

    // Set position of second window, related to primary window.
    newStage.setX(myStage.getX() + 200);
    newStage.setY(myStage.getY() + 100);

    newStage.show();
  }
}
