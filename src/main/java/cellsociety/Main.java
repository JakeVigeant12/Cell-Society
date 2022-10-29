package cellsociety;

import cellsociety.view.SplashScreen;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    /**
     * @see Application#start(Stage)
     */

    public void start(Stage stage){
        SplashScreen ss = new SplashScreen(600.0, stage);
        stage.setTitle("CellSociety");
        stage.setScene(ss.createScene("startSplash.css"));
        stage.show();
    }
}
