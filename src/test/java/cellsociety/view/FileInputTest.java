package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class FileInputTest extends DukeApplicationTest {
    @Override
    public void start(Stage stage) {
<<<<<<< HEAD

        FileInput fi = new FileInput(600);
            stage.setScene(fi.createScene(stage, "EnglishLabels", "fileinput.css"));
=======
        FileInput fi = new FileInput(600, stage);
            stage.setScene(fi.createScene("EnglishLabels", "fileInput.css"));
>>>>>>> master
        stage.show();
    }

    @Test
    void testTextFieldAction() {
        Button button = lookup("#buttonText").query();
        clickOn(button);
    }

}
