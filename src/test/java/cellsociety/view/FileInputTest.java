package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class FileInputTest extends DukeApplicationTest {
    @Override
    public void start(Stage stage) {

        FileInput fileInput = new FileInput(600);
        SceneCreator current = new SceneCreator(600.0);
        stage.setScene(current.createScene(stage, fileInput.createFileInput(stage, "LangLabels"), "fileinput.css"));
        stage.show();
    }

    @Test
    void testTextFieldAction() {
        Button button = lookup("#buttonText").query();
        clickOn(button);
    }

}
