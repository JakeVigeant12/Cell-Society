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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ResourceBundle;

public class GridScreen extends SceneCreator {
    private Pane root;
    private BorderPane borderPane;
    private Button playButton;
    private Button stepButton;
    private Button pauseButton;
    private Button resetButton;
    private Button exitButton;
    private Button backButton;
    private Text fileTitle;
    private Text simulationType;
    private Text author;
    private TextArea description;
    private Text aboutTitle;
    private Paint mainColor = Color.LIGHTGRAY;
    private ResourceBundle myLabels;
    private GridView gridView;
    private Timeline timeline;
    private double refreshRate = 1;

    /**
     * Constructor for GridScreen, sets up the root, borderPane and the timeline
     * @param size
     */
    public GridScreen(double size) {
        super(size);
        root = new Pane();
        borderPane = new BorderPane();
        setUpTimeline();
    }

    /**
     * Sets up the timeline for the animation
     */
    private void setUpTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(refreshRate), e -> {
            //TODO: update grid once:
        gridView.updateGrid(myController.updateGrid());
        }));
        timeline.pause();
    }

    /**
     * Sets up the buttons, labels, text boxes, the Grid Screen UI
     * @param stage
     * @param label
     * @param myController
     * @return borderPane
     */
    public Pane createGridScreen(Stage stage, ResourceBundle label, CellSocietyController myController) {
        this.myController = myController;
        myLabels = label;

        createButtons();

        aboutTitle = createAndStyleText(myLabels.getString("aboutText"), "title");
        fileTitle = createAndStyleText(myLabels.getString("title") + myController.getSimMap().get("Title"), "info");
        simulationType = createAndStyleText(myLabels.getString("typeText") + myController.getSimMap().get("Type"), "info");
        author = createAndStyleText(myLabels.getString("authorText") + myController.getSimMap().get("Author"), "info");

        description = createAndStyleTextBox(myLabels.getString("descriptionText") + myController.getSimMap().get("Description"), "info");
        description.setEditable(false);
        description.setWrapText(true);

        VBox fileInfoBox = new VBox(aboutTitle, fileTitle, simulationType, author, description);
        fileInfoBox.setBackground(Background.fill(mainColor));
        fileInfoBox.getStyleClass().add("aboutbox");
        borderPane.setLeft(fileInfoBox);

        // TODO: FIX THIS TO DISPLAY ON RIGHT SIDE
        VBox rightUIElement = new VBox();
        rightUIElement.setBackground(Background.fill(mainColor));
        rightUIElement.getStyleClass().add("rightbox");
        borderPane.setRight(rightUIElement);

        HBox controls = new HBox(playButton, pauseButton, stepButton, resetButton);
        controls.setBackground(Background.fill(mainColor));
        controls.getStyleClass().add("allbuttons");
        borderPane.setBottom(controls);

        HBox exitButtons = new HBox(backButton, exitButton);
        exitButtons.setBackground(Background.fill(mainColor));
        exitButtons.getStyleClass().add("exitbox");
        borderPane.setTop(exitButtons);

        gridView = new GridView(600);
        gridView.setUpView(myController.getViewGrid());
        GridPane grid = gridView.getGrid();
        grid.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridView.getGrid());

        handleButtons(stage);

        borderPane.setPadding(new Insets(10));

        return borderPane;
    }

    /**
     * Creates and stylizes the text based on a resource bundle label
     * @param myLabels
     * @param title
     * @return text
     */
    private Text createAndStyleText(String myLabels, String title) {
        Text text = new Text(myLabels);
        text.getStyleClass().add(title);
        return text;
    }

    private TextArea createAndStyleTextBox(String myLabels, String title) {
        TextArea textBox = new TextArea(myLabels);
        textBox.getStyleClass().add(title);
        return textBox;
    }

    /**
     * Creates the buttons for the Grid Screen
     */
    private void createButtons() {
        playButton = new Button(myLabels.getString("playText"));
        pauseButton = new Button(myLabels.getString("pauseText"));
        stepButton = new Button(myLabels.getString("stepText"));
        resetButton = new Button(myLabels.getString("resetText"));
        exitButton = new Button(myLabels.getString("exitText"));
        backButton = new Button(myLabels.getString("backText"));
    }

    /**
     * Handles the action events for buttons
     * @param stage
     */
    public void handleButtons(Stage stage) {
        playButton.setOnAction(event -> timeline.play());
        stepButton.setOnAction(event -> {//TODO: update grid once:  gridView.updateGrid(myController.get2DArrayList);
                }
        );
        resetButton.setOnAction(event -> timeline.playFromStart());
        pauseButton.setOnAction(event -> timeline.pause());
        exitButton.setOnAction(event -> {
            StartSplash beginning = new StartSplash(600.0);
            stage.setScene(createScene(stage, beginning.createStart(stage), "startSplash.css"));
            stage.setHeight(600);
            stage.setWidth(600);
        });
        backButton.setOnAction(event -> {
            FileInput backInput = new FileInput(600);
            stage.setScene(createScene(stage, backInput.createFileInput(stage, myLabels.getBaseBundleName()), "fileinput.css"));
            stage.setHeight(600);
            stage.setWidth(600);
        });
    }
}
