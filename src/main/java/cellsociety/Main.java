package cellsociety;

import cellsociety.view.SplashScreen;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {

  public static final String CELL_SOCIETY = "CellSociety";
  public static final String START_SPLASH_CSS = "startSplash.css";

  /**
   * @see Application#start(Stage)
   */

  public void start(Stage stage) {
    SplashScreen ss = new SplashScreen(600.0, stage);
    stage.setTitle(CELL_SOCIETY);
    stage.setScene(ss.createScene(START_SPLASH_CSS));
    stage.show();
  }
}
