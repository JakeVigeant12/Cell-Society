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
    private TextArea descriptionBox;
    private TextArea statusBox;
    private Text aboutTitle;
    private Paint mainColor = Color.LIGHTGRAY;
    private ResourceBundle myLabels;
    private GridView gridView;
    private Timeline timeline;
    private CellSocietyController myController;
    private double refreshRate = 1;

    /**
     * Constructor for GridScreen, sets up the root, borderPane and the timeline
     * @param size
     */
    public GridScreen(double size) {
        super(size);
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
        gridView.updateGrid(myController.updateGrid());
        }));
        timeline.pause();
    }

    /**
     * Sets up the buttons, labels, text boxes, the Grid Screen UI
     * @param stage
     * @param label
     * @return borderPane
     */
    public Pane createGridScreen(Stage stage, ResourceBundle label, CellSocietyController controller) {
        this.myController = controller;
        myLabels = label;

        createButtons();

        createLeftPanel();
        createRightPanel();
        createBottomPanel();
        createTopPanel();

        setupGrid();

        handleButtons(stage);

        return borderPane;
    }

    /**
     * Sets up the grid with properties
     */
    private void setupGrid() {
        gridView = new GridView(800);
        gridView.setUpView(myController.getViewGrid());
        GridPane grid = gridView.getGrid();
        grid.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridView.getGrid());
        borderPane.setPadding(new Insets(10));
    }

    /**
     * Sets up the left panel of the Grid Screen UI
     */
    private void createLeftPanel() {
        aboutTitle = createAndStyleText(myLabels.getString("aboutText"), "title");
        fileTitle = createAndStyleText(myLabels.getString("title") + myController.getProperties().get("Title"), "info");
        simulationType = createAndStyleText(myLabels.getString("typeText") + myController.getProperties().get("Type"), "info");
        author = createAndStyleText(myLabels.getString("authorText") + myController.getProperties().get("Author"), "info");

        statusBox = createAndStyleTextBox(myLabels.getString("statusText"), "info");
        statusBox.setBackground(Background.fill(mainColor));
        statusBox.setEditable(false);
        statusBox.setWrapText(true);

        descriptionBox = createAndStyleTextBox(myLabels.getString("descriptionText") + myController.getProperties().get("Description"), "info");
        descriptionBox.setBackground(Background.fill(mainColor));
        descriptionBox.setEditable(false);
        descriptionBox.setWrapText(true);

        VBox fileInfoBox = new VBox(aboutTitle, fileTitle, simulationType, author, descriptionBox, statusBox);
        fileInfoBox.setBackground(Background.fill(mainColor));
        fileInfoBox.getStyleClass().add("aboutBox");
        borderPane.setLeft(fileInfoBox);
    }

    /**
     * Creates the right panel of the Grid Screen
     */
    private void createRightPanel() {
        // TODO: FIX THIS TO DISPLAY ON RIGHT SIDE
        VBox rightUIElement = new VBox();
        rightUIElement.setBackground(Background.fill(mainColor));
        rightUIElement.getStyleClass().add("rightBox");
        borderPane.setRight(rightUIElement);
    }

    /**
     * Creates the bottom panel with the buttons
     */
    private void createBottomPanel() {
        HBox controls = new HBox(playButton, pauseButton, stepButton, resetButton);
        controls.setBackground(Background.fill(mainColor));
        controls.getStyleClass().add("allButtons");
        borderPane.setBottom(controls);
    }

    /**
     * Creates the top panel with the title and the exit button
     */
    private void createTopPanel() {
        AnchorPane topPanel = new AnchorPane();
        AnchorPane.setRightAnchor(exitButton, 0d);
        AnchorPane.setTopAnchor(backButton, 0d);
        topPanel.getChildren().addAll(backButton, exitButton);
        topPanel.setBackground(Background.fill(mainColor));
        topPanel.getStyleClass().add("exitBox");
        borderPane.setTop(topPanel);
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
        stepButton.setOnAction(event -> {
            gridView.updateGrid(myController.updateGrid());
        });
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
            stage.setScene(createScene(stage, backInput.createFileInput(stage, myLabels.getBaseBundleName()),
                "fileInput.css"));
            stage.setHeight(600);
            stage.setWidth(600);
        });
    }
}
