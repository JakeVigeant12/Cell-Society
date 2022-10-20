package cellsociety.view;

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
    public BorderPane startPane;
    private Text mainTitle;
    private Text selectLanguage;
    private Button englishButton;
    private Button spanishButton;
    private Button frenchButton;
    private ImageView myBackground;

    public static final String DEFAULT_RESOURCE_PACKAGE = StartSplash.class.getPackageName() + ".";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    ResourceBundle startInfo = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "startInfo");


    public StartSplash(double size){
        super(size);
        startPane = new BorderPane();
        myBackground = new ImageView();
    }

    public Pane createStart(Stage stage){
        englishButton = makeButton("englishText");
        spanishButton = makeButton("spanishText");
        frenchButton = makeButton("frenchText");

        mainTitle = new Text("Team 10");
        mainTitle.getStyleClass().add("mainTitle");
        VBox vBoxTop = new VBox(mainTitle);
        vBoxTop.setAlignment(Pos.CENTER);


        selectLanguage = new Text("Select Language");
        selectLanguage.getStyleClass().add("startSelectLanguage");


        myBackground.setImage(new Image(startInfo.getString("startgif")));
        myBackground.setFitWidth(mySize);
        myBackground.setFitHeight(mySize);

        HBox buttons = new HBox(englishButton, spanishButton, frenchButton);
        buttons.getStyleClass().add("allbuttons");

        VBox vBoxBot = new VBox(selectLanguage, buttons);
        vBoxBot.setSpacing(20);
        vBoxBot.setAlignment(Pos.CENTER);

        startPane.getChildren().add(myBackground);
        startPane.setTop(vBoxTop);
        startPane.setBottom(vBoxBot);
        startPane.setPadding(new Insets(25));
        handleEvents(stage);

        return startPane;
    }

    public Button makeButton(String property) {
        Button result = new Button();
        String label = startInfo.getString(property);
        result.setText(label);
        result.setId(property);
        return result;
    }

    public void handleEvents(Stage stage){
        FileInput fi = new FileInput(mySize);
        englishButton.setOnAction(event->{
            stage.setScene(createScene(stage, fi.createFileInput(stage, "EnglishLabels"), "fileinput.css"));
        });
        spanishButton.setOnAction(event ->{
            stage.setScene(createScene(stage, fi.createFileInput(stage, "SpanishLabels"), "fileinput.css"));
        });
        frenchButton.setOnAction(event -> {
            stage.setScene(createScene(stage, fi.createFileInput(stage, "FrenchLabels"), "fileinput.css"));
        });
    }

}
