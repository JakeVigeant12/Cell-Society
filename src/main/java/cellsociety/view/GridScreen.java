package cellsociety.view;

import cellsociety.controller.CellSocietyController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;


import static cellsociety.Main.START_SPLASH_CSS;
import static cellsociety.view.FileInput.FILE_CHOOSER;
import static cellsociety.view.FileInput.GRID_SCREEN_CSS;
import static cellsociety.view.SplashScreen.FILE_INPUT_CSS;

public class GridScreen extends SceneCreator {
  public static final String ABOUT = "About";
  public static final String TITLE = "Title";
  public static final String INFO = "Info";
  public static final String TYPE = "Type";
  public static final String AUTHOR = "Author";
  public static final String STATUS = "Status";
  public static final String DESCRIPTION = "Description";
  public static final String ABOUT_BOX = "aboutBox";
  public static final String SPEED_SLIDER = "speedSlider";
  public static final String ALL_BUTTONS = "allButtons";
  public static final String CREATE_SLIDER_ERROR = "createSliderError";
  public static final String CREATE_BUTTON_ERROR = "createButtonError";
  public static final String CSV_FILES = "CSV Files";
  public static final String CSV = "*.csv";
  public static final String SAVE_SIMULATION_STATUS = "saveSimulationStatus";
  public static final String SAVE_SIMULATION_ERROR = "saveSimulationError";
  public static final String SPEED_STATUS = "speedStatus";
  public static final String CREATE_CELL_ERROR = "createCellError";
  public static final String PAUSED_STATUS = "pausedStatus";
  public static final String RESET_STATUS = "resetStatus";
  public static final String STEP_STATUS = "stepStatus";
  public static final String PLAYING_STATUS = "playingStatus";
  private final BorderPane borderPane;
  private TextArea statusBox;
  private static final Paint MAIN_COLOR = Color.LIGHTGRAY;
  private GridView gridView;
  private Timeline timeline;
  private CellSocietyController myController;
  private double refreshRate = 1;
  private final Stage myStage;
  private Histogram myChart;
  private static final List<List<String>> BUTTONS_LIST = List.of(
      List.of("backButton", "exitButton", "uploadButton", "linegraphButton", "bargraphButton"),
      List.of("playButton", "pauseButton", "stepButton", "resetButton", "saveButton"));

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
  private void setUpTimeline() throws IllegalStateException {
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
    Text aboutTitle = createAndStyleText(getResource().getString(ABOUT), TITLE);
    Text fileTitle = createAndStyleText(
        getResource().getString(TITLE) + myController.getProperties().get(TITLE), INFO);
    Text simulationType = createAndStyleText(
        getResource().getString(TYPE) + myController.getProperties().get(TYPE), INFO);
    Text author = createAndStyleText(
        getResource().getString(AUTHOR) + myController.getProperties().get(AUTHOR),
        INFO);

    statusBox = createAndStyleTextBox(String.format(getResource().getString(STATUS),
        simulationType), INFO);
    statusBox.setId(STATUS);
    statusBox.setBackground(Background.fill(MAIN_COLOR));
    statusBox.setEditable(false);
    statusBox.setWrapText(true);

    TextArea descriptionBox = createAndStyleTextBox(
        getResource().getString(DESCRIPTION) + myController.getProperties()
            .get(DESCRIPTION), INFO);
    descriptionBox.setBackground(Background.fill(MAIN_COLOR));
    descriptionBox.setEditable(false);
    descriptionBox.setWrapText(true);

    VBox fileInfoBox = new VBox(aboutTitle, fileTitle, simulationType, author, descriptionBox,
        statusBox);
    fileInfoBox.setBackground(Background.fill(MAIN_COLOR));
    fileInfoBox.getStyleClass().add(ABOUT_BOX);
    borderPane.setLeft(fileInfoBox);
  }

  /**
   * Creates the bottom panel with the buttons
   */
  private void createBottomPanel() {
    HBox controls = new HBox();
    for (String button : BUTTONS_LIST.get(1)) {
      controls.getChildren().add(makeButton(button));
    }
    controls.getChildren().add(makeSlider(SPEED_SLIDER));
    controls.setBackground(Background.fill(MAIN_COLOR));
    controls.getStyleClass().add(ALL_BUTTONS);

    borderPane.setBottom(controls);
  }

  /**
   * Creates the top panel with the title and the exit button
   */
  private void createTopPanel() {
    HBox topPanel = new HBox();
    for (String button : BUTTONS_LIST.get(0)) {
      topPanel.getChildren().add(makeButton(button));
    }
    topPanel.setBackground(Background.fill(MAIN_COLOR));
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
   *
   * @param myLabels resource bundle label
   * @param title    title of the text box
   * @return text box
   */
  private TextArea createAndStyleTextBox(String myLabels, String title) {
    TextArea textBox = new TextArea(myLabels);
    textBox.getStyleClass().add(title);
    return textBox;
  }

  /**
   * Method that creates and stylizes a slider
   *
   * @param property resource bundle label
   * @return slider
   */
  private HBox makeSlider(String property) {
    HBox sliderBox = new HBox();
    sliderBox.getChildren().add(new Label(getResource().getString(property)));
    sliderBox.getStyleClass().add(SPEED_SLIDER);
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
        showMessage(AlertType.ERROR, getResource().getString(e.getCause().getMessage()), e);
      }
    });
    return sliderBox;
  }

  /**
   * Make a button and sets properties
   *
   * @param property
   * @return
   */
  protected Button makeButton(String property) {
    Button result = new Button();
    String labelText = getResource().getString(property);
    result.setText(labelText);
    result.setId(property);
    result.getStyleClass().add(BUTTON);
    result.setOnAction(event -> {
      try {
        Method m = this.getClass().getDeclaredMethod(getMyCommands().getString(property));
        m.invoke(this);
      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
        showMessage(Alert.AlertType.ERROR, getResource().getString(e.getCause().getMessage()), e);
      }
    });
    return result;
  }

  private void saveSimulation() throws IllegalStateException {
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(CSV_FILES, CSV);
    FILE_CHOOSER.getExtensionFilters().add(extFilter);
    File file = FILE_CHOOSER.showSaveDialog(myStage);
    if (file != null) {
      myController.saveGrid(file);
      statusBox.setText(getResource().getString(SAVE_SIMULATION_STATUS));
    }
  }


  private void changeSpeed(Number newValue) {
    refreshRate = newValue.doubleValue();
    timeline.setRate(refreshRate);
    statusBox.setText(String.format(getResource().getString(SPEED_STATUS), refreshRate));
  }

  private void goBack() {
    closeGraph();
    FileInput backInput = new FileInput(600, myStage);
    myStage.setScene(backInput.createScene(getLanguage(), FILE_INPUT_CSS));
  }

  /**
   * Sets up the file picker
   */
  private void uploadFile() throws IllegalStateException {
    setMyDataFile(FILE_CHOOSER.showOpenDialog(myStage));
    if (getMyDataFile() != null) {
      myController = new CellSocietyController(getMyDataFile());
      myController.loadSimulation(myStage);
      GridScreen firstGrid = new GridScreen(800, myStage, myController);
      myStage.setScene(firstGrid.createScene(getLanguage(), GRID_SCREEN_CSS));
    }
  }

  private void exitSimulation() {
    closeGraph();
    SplashScreen beginning = new SplashScreen(600.0, myStage);
    myStage.setScene(beginning.createScene(START_SPLASH_CSS));
  }

  private void pauseSimulation() {
    timeline.pause();
    statusBox.setText(getResource().getString(PAUSED_STATUS));
  }

  private void resetSimulation() throws IllegalStateException {
    statusBox.setText(getResource().getString(RESET_STATUS));
    myController.resetController();
    gridView.updateGrid(myController.getViewGrid());
  }

  private void stepSimulation() throws IllegalStateException {
    gridView.updateGrid(myController.updateGrid());
    statusBox.setText(getResource().getString(STEP_STATUS));
  }

  private void playSimulation() {
    timeline.play();
    statusBox.setText(getResource().getString(PLAYING_STATUS));
  }
  public GridView getCurrentView(){return gridView;}

  private void createBarGraph() {
    closeGraph();
    myChart = new Histogram(gridView);
    myChart.makeBarGraph();
    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(refreshRate), e -> {
      if(myChart!=null){myChart.updateBarGraph();}
    }));
  }

  private void createLineGraph() {
    closeGraph();
    myChart = new Histogram(gridView);
    myChart.makeLineGraph(timeline);
    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(refreshRate), e -> {
      if(myChart!=null){myChart.updateLineGraph(timeline);}
    }));
  }

  private void closeGraph(){
    if(myChart!=null){
      myChart.shutDown();
      timeline.getKeyFrames().remove(0);
    }
  }

}
