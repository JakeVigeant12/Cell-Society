package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;

public class GridScreen extends SceneCreator {

    private Pane root;
    private BorderPane borderPane;
    private Button play;
    private Button step;
    private Button pause;
    private Button reset;
    private Button exit;

    private Rectangle about;
    private Text title;
    private Text type;
    private Text author;
    private TextArea description;
    private Text Abouttitle;
    private Paint maincolor = Color.LIGHTGRAY;
    private ResourceBundle myLabels;
    private GridView gridView;
    private Timeline timeline;
    private double refreshRate = 1;

    public GridScreen(double size) {
        super(size);
        root = new Pane();
        borderPane = new BorderPane();
        setUpTimeline();
    }

    private void setUpTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(refreshRate), e -> {
            //TODO: update grid once:  gridView.updateGrid(myController.get2DArrayList);
        }));
        timeline.pause();
    }

    public Pane createGridScreen(Stage stage, ResourceBundle label, CellSocietyController myController) {
        this.myController = myController;
        myLabels = label;
        play = new Button(myLabels.getString("playText"));
        pause = new Button(myLabels.getString("pauseText"));
        step = new Button(myLabels.getString("stepText"));
        reset = new Button(myLabels.getString("resetText"));
        exit = new Button(myLabels.getString("exitText"));

        Abouttitle = new Text(myLabels.getString("aboutText"));
        title = new Text(myLabels.getString("title") + myController.getSimMap().get("Title"));
        type = new Text(myLabels.getString("typeText") + myController.getSimMap().get("Type"));
        author = new Text(myLabels.getString("authorText") + myController.getSimMap().get("Author"));
        description = new TextArea();
        description.setText(myLabels.getString("descriptionText") + myController.getSimMap().get("Description"));
        description.setEditable(false);
        description.setWrapText(true);
        Abouttitle.getStyleClass().add("title");
        title.getStyleClass().add("info");
        type.getStyleClass().add("info");
        author.getStyleClass().add("info");
        description.getStyleClass().add("info");

        VBox fileinfo = new VBox(Abouttitle, title, type, author, description);
        fileinfo.setBackground(Background.fill(maincolor));
        fileinfo.getStyleClass().add("aboutbox");
        borderPane.setTop(fileinfo);
        HBox controls = new HBox(play, pause, step, reset, exit);
        borderPane.setBottom(controls);
        controls.getStyleClass().add("allbuttons");
        handleButtons(stage);

        gridView = new GridView(600);
        gridView.setUpView(myController.getViewGrid());
        GridPane grid = gridView.getGrid();
        grid.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridView.getGrid());

        borderPane.setPadding(new Insets(10));

        return borderPane;
    }

    public void handleButtons(Stage stage) {
        play.setOnAction(event -> timeline.play());
        step.setOnAction(event -> {//TODO: update grid once:  gridView.updateGrid(myController.get2DArrayList);
                }
        );
        reset.setOnAction(event -> timeline.playFromStart());
        pause.setOnAction(event -> timeline.pause());
        exit.setOnAction(event -> {
            StartSplash beginning = new StartSplash(600.0);
            stage.setScene(createScene(stage, beginning.createStart(stage), "startsplash.css"));
            stage.setHeight(600);
            stage.setWidth(600);
        });
    }


}
