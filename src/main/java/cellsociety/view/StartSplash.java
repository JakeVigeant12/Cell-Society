package cellsociety.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ResourceBundle;

public class StartSplash extends SceneCreator {
    public Pane startPane;
    private Text mainTitle;
    private Text selectLanguage;
    private Button englishButton;
    private Button spanishButton;
    private Button frenchButton;

    private ImageView myBackground;

    ResourceBundle startInfo = ResourceBundle.getBundle("startInfo");


    public StartSplash(double size){
        super(size);
        startPane = new Pane();
        myBackground = new ImageView();
    }

    public Pane createStart(Stage stage){
        englishButton = makeButton("englishText");
        spanishButton = makeButton("spanishText");
        frenchButton = makeButton("frenchText");

        mainTitle = new Text("Team 10");
        mainTitle.getStyleClass().add("mainTitle");
        mainTitle.setLayoutX(170);
        mainTitle.setLayoutY(70);

        selectLanguage = new Text("Select Language");
        selectLanguage.getStyleClass().add("startSelectLanguage");
        selectLanguage.setLayoutX(240);
        selectLanguage.setLayoutY(460);

        myBackground.setImage(new Image(startInfo.getString("startgif")));
        myBackground.setFitWidth(mySize);
        myBackground.setFitHeight(mySize);

        HBox buttons = new HBox(englishButton, spanishButton, frenchButton);
        buttons.setLayoutY(490);
        buttons.setLayoutX(130);
        buttons.getStyleClass().add("allbuttons");

        startPane.getChildren().addAll(myBackground, buttons, mainTitle, selectLanguage);
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
            stage.setScene(createScene(stage, fi.createFileInput(stage, "LangLabels"), "fileinput.css"));
        });
        spanishButton.setOnAction(event ->{
            stage.setScene(createScene(stage, fi.createFileInput(stage, "SpanishLabels"), "fileinput.css"));
        });
        frenchButton.setOnAction(event -> {
            stage.setScene(createScene(stage, fi.createFileInput(stage, "FrenchLabels"), "fileinput.css"));
        });
    }

}
