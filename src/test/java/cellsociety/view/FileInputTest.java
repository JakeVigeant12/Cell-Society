package cellsociety.view;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class FileInputTest extends DukeApplicationTest {
  @Override
  public void start(Stage stage) {
    FileInput fi = new FileInput(600, stage);
    stage.setScene(fi.createScene("English", "fileInput.css"));
    stage.show();
  }

  @Test
  void testUpload() {
    Button button = lookup("#uploadButton").query();
    Assertions.assertEquals(button.getText(), "Upload");
  }

  @Test
  void testBack() {
    Button button = lookup("#backButton").query();
    clickOn(button);
    Button button1 = lookup("#English").query();
    Assertions.assertEquals(button1.getText(), "English");
  }
}

