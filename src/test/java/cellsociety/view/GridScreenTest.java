package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridScreenTest extends DukeApplicationTest {
    Stage stage;
    TextArea myStatusBox;
    ResourceBundle myResources;
    @Override
    public void start(Stage stage)
        throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.stage = stage;
        File myDataFile = new File("./data/game_of_life/blinkers.sim");
        CellSocietyController controller = new CellSocietyController(myDataFile);
        controller.loadSimulation(stage);
        GridScreen firstGrid = new GridScreen(800, stage, controller);
        stage.setScene(firstGrid.createScene("English", "gridScreen.css"));
        myStatusBox = lookup("#statusText").query();
        myResources = ResourceBundle.getBundle("English");
    }

    @Test
    void testPlayButton() {
        Button button = lookup("#playButton").query();
        clickOn(button);
        Assertions.assertEquals(myResources.getString("playingStatus"), myStatusBox.getText());
    }
    @Test
    void testClickCell() {
        CellView cell = lookup("#cell1,2").query();
        clickOn(cell);
        assertEquals(1, cell.stateProperty().get());
    }
    @Test
    void testPauseButton() {
        Button button = lookup("#pauseButton").query();
        clickOn(button);
        Assertions.assertEquals(myResources.getString("pausedStatus"), myStatusBox.getText());
    }
    @Test
    void testStepButton() {
        Button button = lookup("#stepButton").query();
        clickOn(button);
        Assertions.assertEquals(myResources.getString("stepStatus"), myStatusBox.getText());
    }
    @Test
    void testResetButton() {
        Button button = lookup("#resetButton").query();
        clickOn(button);
        Assertions.assertEquals(myResources.getString("resetStatus"), myStatusBox.getText());
    }
    @Test
    void testExitButton() {
        Button button = lookup("#exitButton").query();
        clickOn(button);
        Button button1 = lookup("#English").query();
        Assertions.assertEquals(button1.getText(), "English");
    }
    @Test
    void testBackButton() {
        Button button = lookup("#backButton").query();
        clickOn(button);
        Button button1 = lookup("#uploadButton").query();
        Assertions.assertEquals(button1.getText(), "Upload");
    }
    @Test
    void testGridSize() {
        stage.setHeight(600);
        stage.setWidth(600);
    }
}
