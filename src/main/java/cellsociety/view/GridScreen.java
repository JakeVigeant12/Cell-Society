package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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
import static cellsociety.view.FileInput.GRID_SCREEN_CSS;

public class GridScreen extends SceneCreator {
    private BorderPane borderPane;
    private Text fileTitle;
    private Text simulationType;
    private Text author;
    private TextArea descriptionBox;
    private TextArea statusBox;
    private Text aboutTitle;
    private Paint mainColor = Color.LIGHTGRAY;
    private GridView gridView;
    private Timeline timeline;
    private CellSocietyController myController;
    private double refreshRate = 1;
    private final Stage myStage;
    private static final List<List<String>> BUTTONS_LIST = List.of(List.of("backButton", "exitButton","uploadButton"), List.of("playButton", "pauseButton", "stepButton", "resetButton", "saveButton"));

    /**
     * Constructor for GridScreen, sets up the root, borderPane and the timeline
     *
     * @param size
     */
    public GridScreen(double size, Stage stage, CellSocietyController controller) {
        super(size, stage);
        this.myController = controller;
        borderPane = new BorderPane();
        myStage = stage;
        setUpTimeline();
    }

    /**
     * Sets up the timeline for the animation
     */
    private void setUpTimeline() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(refreshRate), e -> {
            stepSimulation();
        }));
        pauseSimulation();
    }

    /**
     * Sets up the grid with properties
     */
    public Pane setUpRootPane() {

        createLeftPanel();
        createBottomPanel();
        createTopPanel();


        gridView = new GridView(myController);
        gridView.setUpView(myController.getViewGrid(), (String) myController.getProperties().get("Type"));
        GridPane grid = gridView.getGrid();
        grid.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridView.getGrid());
        gridView.setUpGridViewSize();
//        gridView.updateControllerFromListeners();
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
        HBox controls = new HBox();
        for(String button : BUTTONS_LIST.get(1)) {
            controls.getChildren().add(makeButton(button));
        }
        controls.getChildren().add(makeSlider("speedSlider"));
        controls.setBackground(Background.fill(mainColor));
        controls.getStyleClass().add("allButtons");
        borderPane.setBottom(controls);
    }

    /**
     * Creates the top panel with the title and the exit button
     */
    private void createTopPanel() {
        HBox topPanel = new HBox();
        for(String button : BUTTONS_LIST.get(0)) {
            topPanel.getChildren().add(makeButton(button));
        }
        topPanel.setBackground(Background.fill(mainColor));
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
     * Method that creates and stylizes a slider
     * @param property resource bundle label
     * @return slider
     */
    private HBox makeSlider(String property) {
        HBox sliderBox = new HBox();
        sliderBox.getChildren().add(new Label(myResource.getString(property)));
        sliderBox.getStyleClass().add("speedSlider");
        Slider slider = new Slider();
        sliderBox.getChildren().add(slider);
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
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Method m = this.getClass().getDeclaredMethod(myCommands.getString(property),
                    Number.class);
                m.invoke(this, newValue);
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return sliderBox;
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
        result.setOnAction(event -> {
            try {
                Method m = this.getClass().getDeclaredMethod(myCommands.getString(property));
                m.invoke(this);
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    private void saveSimulation() {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files", "*.csv");
        FILE_CHOOSER.getExtensionFilters().add(extFilter);
        File file = FILE_CHOOSER.showSaveDialog(myStage);
        if (file != null) {
            try {
                myController.saveGrid(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void changeSpeed(Number newValue) {
        refreshRate = newValue.doubleValue();
        timeline.setRate(refreshRate);
    }

    private void goBack() {
        FileInput backInput = new FileInput(600, myStage);
        myStage.setScene(backInput.createScene(language, "fileInput.css"));
    }

    /**
     * Sets up the file picker
     *
     */
    private void uploadFile() {
        try {
            myDataFile = FILE_CHOOSER.showOpenDialog(myStage);
            if (myDataFile != null) {
                myController = new CellSocietyController(myDataFile);
                myController.loadSimulation(myStage);
                GridScreen firstGrid = new GridScreen(800, myStage, myController);
                myStage.setScene(firstGrid.createScene(language, GRID_SCREEN_CSS));
            }
        } catch (IOException e) {
            // should never happen since user selected the file
            new Alert(Alert.AlertType.ERROR, "Invalid Data File Given").showAndWait();//TODO: Use a resource bundle for error string
        } catch (CsvValidationException ignored) {
        }
    }

    private void exitSimulation() {
        StartSplash beginning = new StartSplash(600.0, myStage);
        myStage.setScene(beginning.createScene("startSplash.css"));
    }

    private void pauseSimulation() {
        timeline.pause();
    }

    private void resetSimulation() {
        try {
            myController.resetController();
            gridView.updateGrid(myController.getViewGrid());
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void stepSimulation() {
        gridView.updateGrid(myController.updateGrid());
    }

    private void playSimulation() {
        timeline.play();
    }
}
