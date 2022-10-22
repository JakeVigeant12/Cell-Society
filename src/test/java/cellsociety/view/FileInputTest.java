package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class FileInputTest extends DukeApplicationTest {
    @Override
    public void start(Stage stage) {

        FileSelectionView fi = new FileSelectionView(600);
            stage.setScene(fi.createScene(stage, "EnglishLabels", "fileinput.css"));
        stage.show();
    }

    @Test
    void testTextFieldAction() {
        Button button = lookup("#buttonText").query();
        clickOn(button);
    }

}
