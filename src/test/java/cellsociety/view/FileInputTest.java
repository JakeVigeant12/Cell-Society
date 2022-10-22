package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class FileInputTest extends DukeApplicationTest {
    @Override
    public void start(Stage stage) {

<<<<<<< HEAD
        FileInput fileInput = new FileInput(600);
        SceneCreator current = new SceneCreator(600.0);
        stage.setScene(current.createScene(stage, fileInput.createFileInput(stage, "LangLabels"),
            "fileInput.css"));
=======
        FileInput fi = new FileInput(600);
            stage.setScene(fi.createScene(stage, "EnglishLabels", "fileinput.css"));
>>>>>>> master
        stage.show();
    }

    @Test
    void testTextFieldAction() {
        Button button = lookup("#buttonText").query();
        clickOn(button);
    }

}
