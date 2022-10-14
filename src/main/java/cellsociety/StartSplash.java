package cellsociety;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class StartSplash {
    public double screenSize;
    public Pane startPane;
    private Text mainTitle;
    private Text selectLanguage;
    private Button english;
    private Button spanish;
    private Button anotherlanguage;

    public StartSplash(double size){
        screenSize = size;
        startPane = new Pane();
    }

    public Scene createScene(Stage stage, double width, double height){
        Scene scene = new Scene(startPane, width, height);
        scene.getStylesheets().add("startsplash.css");
        english = new Button(("English"));
        spanish = new Button("Español");
        anotherlanguage = new Button("3rd Language");
        mainTitle = new Text("Cell Society");
        mainTitle.getStyleClass().add("mainTitle");
        mainTitle.setLayoutX(160);
        mainTitle.setLayoutY(70);
        selectLanguage = new Text("Select Language");
        selectLanguage.getStyleClass().add("startSelectLanguage");
        selectLanguage.setLayoutX(250);
        selectLanguage.setLayoutY(430);
        Text subtitle = new Text("Team 10");
        subtitle.getStyleClass().add("startSelectLanguage");
        subtitle.setLayoutY(100);
        subtitle.setLayoutX(280);

        HBox buttons = new HBox(english, spanish, anotherlanguage);
        buttons.setLayoutY(490);
        buttons.setLayoutX(130);
        buttons.getStyleClass().add("allbuttons");

        startPane.getChildren().addAll(buttons, mainTitle, selectLanguage, subtitle);
        handleEvents(stage);

        return scene;
    }

    public void handleEvents(Stage stage){
        english.setOnAction(event->{
            nextScreen(stage);
        });
        spanish.setOnAction(event ->{
            nextScreen(stage);
        });
        anotherlanguage.setOnAction(event -> {
            nextScreen(stage);
        });
    }

    public void nextScreen(Stage stage){
        stage.show();
    }
}
