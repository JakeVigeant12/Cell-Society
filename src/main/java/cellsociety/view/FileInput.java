package cellsociety.view;

import cellsociety.controller.CellSocietyController;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ResourceBundle;

public class FileInput extends SceneCreator {
    public BorderPane inputPane;
    public Button input;
    public Button back;
    // kind of data files to look for
    public static final String DATA_FILE_SIM_EXTENSION = "*.sim";
    // default to start in the data folder to make it easy on the user to find
    public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
    // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
    private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_SIM_EXTENSION);

    private ImageView inputBackground;
    private ResourceBundle label;

    /**
     * Constructor for FileInput
     * @param size
     */
    public FileInput(double size){
        super(size);
        inputPane = new BorderPane();
        inputBackground = new ImageView();
    }

    /**
     * Sets up the file input screen
     * @param stage
     * @param language
     * @return
     */
    public Pane createFileInput(Stage stage, String language){
        //add back button
        label = ResourceBundle.getBundle(language);
        input = makeButton("buttonText");
        input.getStyleClass().add("button");

        back = makeButton("backText");

        Text title = new Text(label.getString("titleText"));
        title.getStyleClass().add("mainText");

        inputBackground.setImage(new Image(label.getString("uploadgif")));
        inputBackground.setFitHeight(mySize);
        inputBackground.setFitWidth(mySize);
        inputPane.getChildren().addAll(inputBackground);

        VBox upload = new VBox(title, input,back);
        upload.setAlignment(Pos.CENTER);
        upload.getStyleClass().add("uploadbox");
        inputPane.setTop(upload);

        buttonPress(stage);
        return inputPane;
    }

    /**
     * Sets up the button press handling
     * @param stage
     */
    private void buttonPress(Stage stage) {
        //add go back button
        input.setOnAction(event -> {
            mySize = 800;
            filePick(stage);
//            stage.setScene(createScene(stage, firstgrid.createGridScreen(stage, label, myController), "gridscreen.css"));
            nextScreen(stage);
        });
        back.setOnAction(event -> {
            StartSplash beginning = new StartSplash(600.0);
            stage.setScene(createScene(stage, beginning.createStart(stage), "startSplash.css"));
        });
    }

    /**
     * Sets up the file picker
     * @param stage
     */
    public void filePick(Stage stage) {
        try {
            File dataFile = FILE_CHOOSER.showOpenDialog(stage);
            if (dataFile != null) {
                CellSocietyController controller = new CellSocietyController(dataFile);
                controller.loadSimulation(stage);
                GridScreen firstgrid = new GridScreen(mySize);
                stage.setScene(createScene(stage, firstgrid.createGridScreen(stage, label, controller), "gridscreen.css"));
            }
        } catch (IOException e) {
            // should never happen since user selected the file
            showMessage(Alert.AlertType.ERROR, "Invalid Data File Given");
        } catch (CsvValidationException e) {
        }
    }

    /**
     * Sets up the alert message
     * @param type
     * @param message
     */
    private void showMessage(Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    /**
     * CSV reader method
     * @param dataReader
     * @return
     */
    public int sumCSVData(Reader dataReader) {
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
        } catch (IOException | CsvValidationException e) {
            showMessage(Alert.AlertType.ERROR, "Invalid CSV Data");
            return 0;
        }
    }

    /**
     * Sets up the file chooser
     * @param extensionAccepted
     * @return
     */
    private static FileChooser makeChooser(String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(DATA_FILE_FOLDER));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("SIM Files", extensionAccepted));
        return result;
    }

    /**
     * Make a button and sets properties
     * @param property
     * @return
     */
    public Button makeButton(String property) {
        Button result = new Button();
        String labelText = label.getString(property);
        result.setText(labelText);
        result.setId(property);
        return result;
    }
}
