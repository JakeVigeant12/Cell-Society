package cellsociety;

import cellsociety.controller.CellSocietyController;
import cellsociety.view.StartSplash;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


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
}
