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

public class StartSplash extends SceneCreator {

    public static final String FILE_INPUT_CSS = "fileInput.css";
    private final List<String> languageList = List.of("English", "Spanish", "French");
    public BorderPane startPane;
    private Text mainTitle;
    private Text selectLanguage;
    private Button englishButton;
    private Button spanishButton;
    private Button frenchButton;
    private ImageView myBackground;
    private final Stage myStage;

    public static final String DEFAULT_RESOURCE_PACKAGE = StartSplash.class.getPackageName() + ".";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    ResourceBundle startInfo = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "startInfo");


    public StartSplash(double size, Stage stage) {
        super(size, stage);
        myStage = stage;
        startPane = new BorderPane();
        myBackground = new ImageView();
    }

    public Pane setUpRootPane() {

        mainTitle = new Text("Team 10");
        mainTitle.getStyleClass().add("mainTitle");
        VBox vBoxTop = new VBox(mainTitle);
        vBoxTop.setAlignment(Pos.CENTER);


        selectLanguage = new Text("Select Language");
        selectLanguage.getStyleClass().add("startSelectLanguage");


        myBackground.setImage(new Image(startInfo.getString("startGif")));
        myBackground.setFitWidth(mySize);
        myBackground.setFitHeight(mySize);

        HBox buttons = new HBox();
        for(String language : languageList) {
            buttons.getChildren().add(makeButton(language));
        }
        buttons.getStyleClass().add("allButtons");

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
            FileInput fileInput = new FileInput(mySize, myStage);
            myStage.setScene(fileInput.createScene(property, FILE_INPUT_CSS));
        });
        return result;
    }
}
