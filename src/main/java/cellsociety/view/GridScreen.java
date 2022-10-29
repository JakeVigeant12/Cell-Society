package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
  public Pane setUpRootPane() {

    createLeftPanel();
    createBottomPanel();
    createTopPanel();


    gridView = new GridView(myController);
    gridView.setUpView(myController.getViewGrid());
    GridPane grid = gridView.getGrid();
    grid.setAlignment(Pos.CENTER);
    borderPane.setCenter(gridView.getGrid());
    gridView.setUpGridViewSize();
    borderPane.setPadding(new Insets(10));
    setUpTimeline();
    return borderPane;
  }

  /**
   * Sets up the left panel of the Grid Screen UI
   */
  private void createLeftPanel() {
    aboutTitle = createAndStyleText(getMyResource().getString("aboutText"), "title");
    fileTitle = createAndStyleText(getMyResource().getString("title") + myController.getProperties().get("Title"), "info");
    simulationType = createAndStyleText(getMyResource().getString("typeText") + myController.getProperties().get("Type"), "info");
    author = createAndStyleText(getMyResource().getString("authorText") + myController.getProperties().get("Author"), "info");

    statusBox = createAndStyleTextBox(String.format(getMyResource().getString("statusText"), simulationType), "info");
    statusBox.setId("statusText");
    statusBox.setBackground(Background.fill(mainColor));
    statusBox.setEditable(false);
    statusBox.setWrapText(true);

    descriptionBox = createAndStyleTextBox(getMyResource().getString("descriptionText") + myController.getProperties().get("Description"), "info");
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

    Button button = new Button("1");
    button.setOnAction(e -> saveSimulation());
    controls.getChildren().add(button);


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
    Button enlargeButton = new Button("enlarge");
    topPanel.getChildren().add(enlargeButton);
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
    sliderBox.getChildren().add(new Label(getMyResource().getString(property)));
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
        Method m = this.getClass().getDeclaredMethod(getMyCommands().getString(property),
            Number.class);
        m.invoke(this, newValue);
      } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
        new Alert(AlertType.ERROR, getMyResource().getString("createSliderError")).showAndWait();
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
    String labelText = getMyResource().getString(property);
    result.setText(labelText);
    result.setId(property);
    result.setOnAction(event -> {
      try {
        Method m = this.getClass().getDeclaredMethod(getMyCommands().getString(property));
        m.invoke(this);
      } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
        new Alert(AlertType.ERROR, getMyResource().getString("createButtonError")).showAndWait();
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
        statusBox.setText(getMyResource().getString("saveSimulationStatus"));
      } catch (IOException e) {
        new Alert(AlertType.ERROR, getMyResource().getString("saveSimulationError")).showAndWait();
      }
    }
  }


  private void changeSpeed(Number newValue) {
    refreshRate = newValue.doubleValue();
    timeline.setRate(refreshRate);
    statusBox.setText(String.format(getMyResource().getString("speedStatus"), refreshRate));
  }

  private void goBack() {
    FileInput backInput = new FileInput(600, myStage);
    myStage.setScene(backInput.createScene(getLanguage(), "fileInput.css"));
  }

  /**
   * Sets up the file picker
   *
   */
  private void uploadFile() {
    try {
      setMyDataFile(FILE_CHOOSER.showOpenDialog(myStage));
      if (getMyDataFile() != null) {
        myController = new CellSocietyController(getMyDataFile());
        myController.loadSimulation(myStage);
        GridScreen firstGrid = new GridScreen(800, myStage, myController);
        myStage.setScene(firstGrid.createScene(getLanguage(), GRID_SCREEN_CSS));
      }
    } catch (IOException | CsvValidationException e) {
      new Alert(AlertType.ERROR, getMyResource().getString("fileUploadError")).showAndWait();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException e) {
      new Alert(AlertType.ERROR, getMyResource().getString("createCellError")).showAndWait();
    }
  }

  private void exitSimulation() {
    StartSplash beginning = new StartSplash(600.0, myStage);
    myStage.setScene(beginning.createScene("startSplash.css"));
  }

  private void pauseSimulation() {
    timeline.pause();
    statusBox.setText(getMyResource().getString("pausedStatus"));
  }

  private void resetSimulation() {
    try {
      statusBox.setText(getMyResource().getString("resetStatus"));
      myController.resetController();
      gridView.updateGrid(myController.getViewGrid());
    } catch (CsvValidationException | IOException e) {
      new Alert(AlertType.ERROR, getMyResource().getString("fileUploadError")).showAndWait();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException e) {
      new Alert(AlertType.ERROR, getMyResource().getString("createCellError")).showAndWait();
    }
  }

  private void stepSimulation() {
    gridView.updateGrid(myController.updateGrid());
    statusBox.setText(getMyResource().getString("stepStatus"));
  }

  private void playSimulation() {
    timeline.play();
    statusBox.setText(getMyResource().getString("playingStatus"));
  }
}
