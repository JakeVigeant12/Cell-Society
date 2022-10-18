package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ResourceBundle;

public class FileInput extends SceneCreator {
    public double screenSize;
    public Pane inputPane;
    public Button input;
    // kind of data files to look for
    public static final String DATA_FILE_SIM_EXTENSION = "*.sim";
    // default to start in the data folder to make it easy on the user to find
    public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
    // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
    private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_SIM_EXTENSION);

    private ResourceBundle label;
    private Rectangle fileBackground;
    public FileInput(double size){
        super(size);
        inputPane = new Pane();
    }

    public Pane createFileInput(Stage stage, String language){
        //add back button
        label = ResourceBundle.getBundle(language);

        input = new Button(label.getString("buttonText"));
        input.getStyleClass().add("button");
        input.setLayoutY(450);
        input.setLayoutX(250);

        Text title = new Text(label.getString("titleText"));
        title.getStyleClass().add("mainText");
        title.setLayoutY(200);
        title.setLayoutX(90);

        fileBackground = new Rectangle(mySize - 100, mySize - 100, Color.LIGHTGRAY);
        fileBackground.setY(50);
        fileBackground.setX(50);


        inputPane.getChildren().addAll(fileBackground, input,title);
        buttonPress(stage);
        return inputPane;
    }

    private void buttonPress(Stage stage) {
        //add go back button
        mySize = 800;
        input.setOnAction(event -> {
            filePick(stage);
            nextScreen(stage);
        });
    }
    public void filePick(Stage stage) {
        try {
            File dataFile = FILE_CHOOSER.showOpenDialog(stage);
            if (dataFile != null) {
                CellSocietyController controller = new CellSocietyController(dataFile, stage);
                controller.loadSimulation(stage);
                GridScreen firstgrid = new GridScreen(mySize);
                stage.setScene(createScene(stage, firstgrid.createGridScreen(stage, label, controller), "gridscreen.css"));
            }
        }
        catch (IOException e) {
            // should never happen since user selected the file
            showMessage(Alert.AlertType.ERROR, "Invalid Data File Given");
        } catch (CsvValidationException e) {
            showMessage(Alert.AlertType.ERROR, "Invalid CSV File Given");
        }
    }
    private void showMessage (Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }
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
            showMessage(Alert.AlertType.ERROR, "Invalid CSV Data");
            return 0;
        }
    }

    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(DATA_FILE_FOLDER));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("SIM Files", extensionAccepted));
        return result;
    }


}
