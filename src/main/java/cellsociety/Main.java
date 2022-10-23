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
    // kind of data files to look for
    public static final String DATA_FILE_SIM_EXTENSION = "*.sim";
    // default to start in the data folder to make it easy on the user to find
    public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
    // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
    private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_SIM_EXTENSION);
    // internal configuration file
    public static final String INTERNAL_CONFIGURATION = "cellsociety.Configuration";

    /**
     * @see Application#start(Stage)
     */

    public void start(Stage stage){
        StartSplash ss = new StartSplash(600.0, stage);
        stage.setTitle("CellSociety");
        stage.setScene(ss.createScene("startSplash.css"));
        stage.show();
    }

    /**
     * Filer picker method
     * @param primaryStage
     */
    public void filePick(Stage primaryStage) {
        try {
            File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
            if (dataFile != null) {
                CellSocietyController controller = new CellSocietyController(dataFile);
                controller.loadSimulation(primaryStage);
            }
        }
        catch (IOException e) {
            // should never happen since user selected the file
            showMessage(AlertType.ERROR, "Invalid Data File Given");
        } catch (CsvValidationException e) {
            showMessage(AlertType.ERROR, "Invalid CSV Format");
        }
    }

    /**
     * Returns sum of values in the given CSV data source (could be anything that is readable).
     */
    public int sumCSVData (Reader dataReader) {
        // this syntax automatically close file resources if an exception occurs
        try (CSVReader csvReader = new CSVReader(dataReader)) {
            int total = 0;
            // get headers separately
            String[] headers = csvReader.readNext();
            // read rest of data line by line
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String value : nextRecord) {
                    total += Integer.parseInt(value);
                }
            }
            return total;
        }
        catch (IOException | CsvValidationException e) {
            showMessage(AlertType.ERROR, "Invalid CSV Data");
            return 0;
        }
    }

    /**
     * A method to test getting internal resources.
     */
    public double getVersion () {
        ResourceBundle resources = ResourceBundle.getBundle(INTERNAL_CONFIGURATION);
        return Double.parseDouble(resources.getString("Version"));
    }

    // display given message to user using the given type of Alert dialog box
    private void showMessage (AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    // set some sensible defaults when the FileChooser is created


    /**
     * Default version of main() is actually included within JavaFX!
     */
    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(DATA_FILE_FOLDER));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("CSV Files", extensionAccepted));
        return result;
    }
}
