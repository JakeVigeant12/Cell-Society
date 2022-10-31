package cellsociety.view;

import java.util.HashMap;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Histogram{
    private Stage myStage;
    private HashMap cellNumbers;
    private GridView myGrid;
    private BarChart barChart;
    private XYChart.Series<String, Integer> series1;
    private Set cellKeys;
    private FlowPane myPane;
    public Histogram(GridView theGrid){
        myStage = new Stage();
        myStage.setTitle("Current Cell Numbers");
        myGrid = theGrid;
        cellNumbers = myGrid.getCurrentStates();
    }


    public void makeBarGraph(){
        myStage.show();
        series1 = new XYChart.Series<>();
        cellKeys = cellNumbers.keySet();

        series1.setName("Real Data");
        cellKeys.forEach( (n) -> {
            series1.getData().add(new XYChart.Data<String, Integer>("State " + n, (Integer) cellNumbers.get(n), true));
        });

        myPane = new FlowPane();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart(xAxis, yAxis);
        barChart.getData().addAll(series1);
        xAxis.setLabel("State");
        yAxis.setLabel("Cell Population");
        myPane.getChildren().add(barChart);
        Scene scene = new Scene(myPane, 600, 600);
        myStage.setScene(scene);
        myStage.show();
    }

    public void updateBarGraph(){
        cellKeys.forEach( (n) -> {
            series1.getData().add(new XYChart.Data<String, Integer>("State " + n, (Integer) cellNumbers.get(n), true));
        });

    }

    public void shutDown(){
        myStage.close();
    }


}
