package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class StartSplashTest extends DukeApplicationTest {
    @Override
    public void start(Stage stage) {
        StartSplash ss = new StartSplash(600.0);
        stage.setTitle("CellSociety");
        stage.setScene(ss.createScene(stage, "startSplash.css"));
        stage.show();
    }

    @Test
    void testTextFieldAction() {
        Button button = lookup("#englishText").query();
        clickOn(button);
    }

}