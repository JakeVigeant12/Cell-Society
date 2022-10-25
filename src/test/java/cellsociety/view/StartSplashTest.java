package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class StartSplashTest extends DukeApplicationTest {
    @Override
    public void start(Stage stage) {
        StartSplash ss = new StartSplash(600.0, stage);
        stage.setTitle("CellSociety");
        stage.setScene(ss.createScene("startSplash.css"));
        stage.show();
    }

    @Test
    void testEnglish() {
        Button button = lookup("#English").query();
        clickOn(button);
        Button button1 =  lookup("#uploadButton").query();
        Assertions.assertEquals(button1.getText(), "Upload");
    }

}
