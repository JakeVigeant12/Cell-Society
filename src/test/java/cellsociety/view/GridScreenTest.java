package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import cellsociety.model.Grid;
import com.opencsv.exceptions.CsvValidationException;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridScreenTest extends DukeApplicationTest {
    Stage stage;
    @Override
    public void start(Stage stage)
        throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.stage = stage;
        File myDataFile = new File("./data/game_of_life/blinkers.sim");
        CellSocietyController controller = new CellSocietyController(myDataFile);
        controller.loadSimulation(stage);

        GridScreen firstGrid = new GridScreen(800, stage, controller);
        stage.setScene(firstGrid.createScene("EnglishLabels", "gridScreen.css"));

    }

    @Test
    void testPlayButton() {
        Button button = lookup("#playText").query();
        clickOn(button);
    }
    @Test
    void testClickCell() {
        CellView cell = lookup("#cell1,2").query();
        clickOn(cell);
        assertEquals(1, cell.stateProperty().get());
    }
    @Test
    void testClickCell2() {
        CellView cell = lookup("#cell3,0").query();
        clickOn(cell);
        assertEquals(0, cell.stateProperty().get());
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
    void testGridSize() {
        GridPane gridView = lookup("#gridView").query();
        CellView cellView = (CellView)gridView.getChildren().get(0);
        double actual = cellView.getRectangle().getHeight();
        double expected = Math.min((gridView.getWidth() - 50) / 10, (gridView.getHeight() - 50) / 10);
        assertEquals(expected, actual);
    }

}
