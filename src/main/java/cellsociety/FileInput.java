package cellsociety;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class FileInput {
    public double screenSize;
    public Pane inputPane;
    public Button input;

    private ResourceBundle label = ResourceBundle.getBundle("");
    public FileInput(double size){
        super();
        inputPane = new Pane();
    }

    public createFileInput(Stage stage, String language){
        label = ResourceBundle.getBundle(language);
        input = new Button(label.getString());

    }


}
