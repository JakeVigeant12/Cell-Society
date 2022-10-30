package cellsociety.view;

import java.util.HashMap;
import java.util.Set;

import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Histogram extends GridScreen{
    private Stage myStage;
    private HashMap cellNumbers;
    private GridView myGrid;

    private Histogram(){
        myStage = new Stage();
        myStage.setTitle("Current Cell Numbers");
        myGrid = getCurrentView();
        cellNumbers = myGrid.getCurrentStates();
    }

    private void createGraph(Stage stage){
       XYChart.Series series1 = new XYChart.Series<>();

    }

}
