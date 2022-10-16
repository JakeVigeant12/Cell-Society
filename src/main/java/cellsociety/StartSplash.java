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

public class StartSplash extends SceneCreator{
    public double screenSize;
    public Pane startPane;
    private Text mainTitle;
    private Text selectLanguage;
    private Button english;
    private Button spanish;
    private Button anotherlanguage;


    public StartSplash(double size){
        super(size);
        startPane = new Pane();
    }

    public Pane createStart(Stage stage){
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

        return startPane;
    }

    public void handleEvents(Stage stage){
        FileInput fi = new FileInput(mySize);
        english.setOnAction(event->{
            stage.setScene(createScene(stage, fi.createFileInput(stage, "LangLabels"), "fileinput.css"));
            //nextScreen(stage);
        });
        spanish.setOnAction(event ->{
            stage.setScene(createScene(stage, fi.createFileInput(stage, "LangLabels_es"), "fileinput.css"));
//            nextScreen(stage);
        });
        anotherlanguage.setOnAction(event -> {
            stage.setScene(createScene(stage, fi.createFileInput(stage, "LangLabels"), "fileinput.css"));
//            nextScreen(stage);
        });
    }

}
