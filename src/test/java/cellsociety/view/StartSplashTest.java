package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class StartSplashTest extends DukeApplicationTest {
    @Override
    public void start (Stage stage) {

        StartSplash ss = new StartSplash(600.0);
        SceneCreator current = new SceneCreator(600.0);
        stage.setScene(current.createScene(stage,ss.createStart(stage),"startsplash.css" ));
        stage.show();
    }

    @Test
    void testTextFieldAction () {
        Button button =lookup("#englishText").query();
        clickOn(button);
    }

}