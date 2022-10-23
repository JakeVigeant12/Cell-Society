package cellsociety;

import cellsociety.view.StartSplash;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * @see Application#start(Stage)
     */

    public void start(Stage stage){
        StartSplash ss = new StartSplash(600.0, stage);
        stage.setTitle("CellSociety");
        stage.setScene(ss.createScene("startSplash.css"));
        stage.show();
    }

    // display given message to user using the given type of Alert dialog box
    private void showMessage (AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }
}
