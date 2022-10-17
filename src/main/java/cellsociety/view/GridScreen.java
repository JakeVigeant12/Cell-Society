package cellsociety.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ResourceBundle;

public class GridScreen extends SceneCreator{

    private Pane gridPane;
    private Button play;
    private Button step;
    private Rectangle about;
    private Text title;
    private Text type;
    private Text author;
    private Text description;
    private Text Abouttitle;
    private Paint jid = Color.GRAY;
    private ResourceBundle myLabels;


    public GridScreen(double size){
        super(size);
        gridPane = new Pane();
    }

    public Pane createGridScreen(Stage stage, ResourceBundle label){
        myLabels = label;
        play = new Button(">");
        step = new Button(">|");
        play.getStyleClass().add("buttons");

        Rectangle about = new Rectangle(200,600);
        about.setX(600);
        about.setFill(jid);

        Abouttitle = new Text(myLabels.getString("aboutText"));
        title = new Text(myLabels.getString("title"));
        type = new Text(myLabels.getString("typeText"));
        author = new Text(myLabels.getString("authorText"));
        description = new Text(myLabels.getString("descriptionText"));

        VBox fileinfo = new VBox(Abouttitle, title, type, author, description);
        fileinfo.setBackground(Background.fill(jid));
        HBox controls = new HBox(play,step);
        controls.setLayoutX(400);
        controls.setLayoutY(690);
        controls.getStyleClass().add("allbuttons");
        gridPane.getChildren().addAll(controls, about, fileinfo);
        return gridPane;
    }



}
