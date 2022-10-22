package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

import static cellsociety.view.FileInput.FILE_CHOOSER;

public class GridScreen extends SceneCreator {
    private BorderPane borderPane;
    private Button playButton;
    private Button stepButton;
    private Button pauseButton;
    private Button resetButton;
    private Button exitButton;
    private Button backButton;
    private Button saveButton;
    private Slider speedSlider;
    private Label sliderLabel;
    private Text fileTitle;
    private Text simulationType;
    private Text author;
    private TextArea descriptionBox;
    private TextArea statusBox;
    private Text aboutTitle;
    private Paint mainColor = Color.LIGHTGRAY;
    public static final String DEFAULT_RESOURCE_PACKAGE = StartSplash.class.getPackageName() + ".";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    private GridView gridView;
    private Timeline timeline;
    private CellSocietyController myController;
    private double refreshRate = 1;


    /**
     * Constructor for GridScreen, sets up the root, borderPane and the timeline
     *
     * @param size
     */
    public GridScreen(double size, CellSocietyController controller) {
        super(size);
        this.myController = controller;
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
     * Sets up the grid with properties
     */
    public Pane setScene(Stage stage) {
        createButtons();

        createLeftPanel();
        createBottomPanel();
        createTopPanel();

        handleButtons(stage);

        gridView = new GridView();
        gridView.setUpView(myController.getViewGrid(), (String) myController.getProperties().get("Type"));
        GridPane grid = gridView.getGrid();
        grid.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridView.getGrid());
        gridView.setUpGridViewSize();
        borderPane.setPadding(new Insets(10));

        return borderPane;
    }

    /**
     * Sets up the left panel of the Grid Screen UI
     */
    private void createLeftPanel() {
        aboutTitle = createAndStyleText(myResource.getString("aboutText"), "title");
        fileTitle = createAndStyleText(myResource.getString("title") + myController.getProperties().get("Title"), "info");
        simulationType = createAndStyleText(myResource.getString("typeText") + myController.getProperties().get("Type"), "info");
        author = createAndStyleText(myResource.getString("authorText") + myController.getProperties().get("Author"), "info");

        statusBox = createAndStyleTextBox(myResource.getString("statusText"), "info");
        statusBox.setBackground(Background.fill(mainColor));
        statusBox.setEditable(false);
        statusBox.setWrapText(true);

        descriptionBox = createAndStyleTextBox(myResource.getString("descriptionText") + myController.getProperties().get("Description"), "info");
        descriptionBox.setBackground(Background.fill(mainColor));
        descriptionBox.setEditable(false);
        descriptionBox.setWrapText(true);

        VBox fileInfoBox = new VBox(aboutTitle, fileTitle, simulationType, author, descriptionBox, statusBox);
        fileInfoBox.setBackground(Background.fill(mainColor));
        fileInfoBox.getStyleClass().add("aboutBox");
        borderPane.setLeft(fileInfoBox);
    }

    /**
     * Creates the bottom panel with the buttons
     */
    private void createBottomPanel() {
        HBox sliderBox = new HBox(sliderLabel, speedSlider);
        sliderBox.getStyleClass().add("slider");
        HBox controls = new HBox(playButton, pauseButton, stepButton, resetButton, sliderBox, saveButton);
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
     *
     * @param title
     * @return text
     */
    private Text createAndStyleText(String myLabels, String title) {
        Text text = new Text(myLabels);
        text.getStyleClass().add(title);
        return text;
    }

    /**
     * Creates and stylizes the text box based on a resource bundle label
     * @param myLabels resource bundle label
     * @param title title of the text box
     * @return text box
     */
    private TextArea createAndStyleTextBox(String myLabels, String title) {
        TextArea textBox = new TextArea(myLabels);
        textBox.getStyleClass().add(title);
        return textBox;
    }

    /**
     * Creates the buttons for the Grid Screen
     */
    private void createButtons() {
        playButton = makeButton("playText");
        pauseButton = makeButton("pauseText");
        stepButton = makeButton("stepText");
        resetButton = makeButton("resetText");
        exitButton = makeButton("exitText");
        backButton = makeButton("backText");
        saveButton = makeButton("saveText");
        speedSlider = makeSlider("speedText");
    }

    /**
     * Method that creates and stylizes a slider
     * @param property resource bundle label
     * @return slider
     */
    private Slider makeSlider(String property) {
        Slider slider = new Slider();
        sliderLabel = new Label(myResource.getString(property));
        slider.setMin(0);
        slider.setMax(10);
        slider.setValue(1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setSnapToTicks(true);
        slider.setId(property);
        return slider;
    }

    /**
     * Creates a button based on a resource bundle property
     * @param property - property of the resource bundle
     * @return button
     */
    public Button makeButton(String property) {
        Button result = new Button();
        String labelText = myResource.getString(property);
        result.setText(labelText);
        result.setId(property);
        return result;
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
        resetButton.setOnAction(event -> {
            try {
                myController.resetController();
                gridView.updateGrid(myController.getViewGrid());
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        pauseButton.setOnAction(event -> timeline.pause());
        exitButton.setOnAction(event -> {
            StartSplash beginning = new StartSplash(600.0);
            stage.setScene(beginning.createScene(stage, "startSplash.css"));
        });
        backButton.setOnAction(event -> {
            FileInput backInput = new FileInput(600);
            stage.setScene(backInput.createScene(stage, language, "fileInput.css"));
        });
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            refreshRate = newValue.doubleValue();
            timeline.setRate(refreshRate);
        });
        saveButton.setOnAction(event -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files", "*.csv");
            FILE_CHOOSER.getExtensionFilters().add(extFilter);
            File file = FILE_CHOOSER.showSaveDialog(stage);
            if (file != null) {
                try {
                    myController.saveGrid(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
