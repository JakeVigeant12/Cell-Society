package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.File;
import java.io.IOException;

class GridScreenTest extends DukeApplicationTest {
    Stage stage;
    @Override
    public void start(Stage stage) throws CsvValidationException, IOException {
        this.stage = stage;
        File myDataFile = new File("./data/game_of_life/blinkers.sim");
        CellSocietyController controller = new CellSocietyController(myDataFile);
        controller.loadSimulation(stage);
        GridScreen firstGrid = new GridScreen(800, controller);
        stage.setScene(firstGrid.createScene(stage, "EnglishLabels", "gridScreen.css"));
    }

    @Test
    void testPlayButton() {
        Button button = lookup("#playText").query();
        clickOn(button);
    }
    @Test
    void testPauseButton() {
        Button button = lookup("#pauseText").query();
        clickOn(button);
    }
    @Test
    void testStepButton() {
        Button button = lookup("#stepText").query();
        clickOn(button);
    }
    @Test
    void testResetButton() {
        Button button = lookup("#resetText").query();
        clickOn(button);
    }
    @Test
    void testExitButton() {
        Button button = lookup("#exitText").query();
        clickOn(button);
    }
    @Test
    void testBackButton() {
        Button button = lookup("#backText").query();
        clickOn(button);
    }
    @Test
    void testResize() {
        stage.setHeight(600);
        stage.setWidth(600);
    }

}
