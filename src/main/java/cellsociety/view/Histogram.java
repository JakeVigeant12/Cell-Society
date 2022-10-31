package cellsociety.view;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Histogram{
    private Stage myStage;
    private HashMap cellNumbers;
    private GridView myGrid;
    private BarChart barChart;
    private LineChart lineChart;
    private XYChart.Series<String, Integer> barSeries;
    private XYChart.Series<Number, Integer> lineSeries1;
    private XYChart.Series<Number, Integer> lineSeries2;
    private XYChart.Series<Number, Integer> lineSeries3;
    List<XYChart.Series<Number,Integer>> SERIES_LIST;
    private Set cellKeys;
    private VBox myPane;
    private int updateTime;

    /**
     * Sets up the main variable for the function of the Histogram class
     * @param theGrid
     */
    public Histogram(GridView theGrid){
        myStage = new Stage();
        myStage.setTitle("Current Cell Numbers");
        myGrid = theGrid;
        cellNumbers = myGrid.getCurrentStates();
        cellKeys = cellNumbers.keySet();
    }


    /**
     * Createes the Bar Graph
     */
    public void makeBarGraph(){
        //create new series
        setSeries();

        cellKeys.forEach( (n) -> {
            barSeries.getData().add(new XYChart.Data<String, Integer>("State " + n, (Integer) cellNumbers.get(n), true));
        });
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("States");
        yAxis.setLabel("Cell Population");

        myPane = new VBox();

        barChart = new BarChart(xAxis, yAxis);
        barChart.getData().addAll(barSeries);
        barChart.setTitle("Current Cell States");
        barChart.setLegendVisible(false);

        myPane.getChildren().add(barChart);

        showGraph();
    }

    //Sets the stage and shows it with the graph

    private void showGraph(){
        Scene scene = new Scene(myPane, 500, 400);
        myStage.setScene(scene);
        myStage.setX(0);
        myStage.setY(0);
        myStage.show();
    }

    //Initializes the 4 series needed for this class
    private void setSeries(){
        barSeries = new XYChart.Series<>();
        lineSeries1 = new XYChart.Series<>();
        lineSeries2 = new XYChart.Series<>();
        lineSeries3 = new XYChart.Series<>();
    }

    /**
     * creates a line graph using the parameter time to create x axis
     * @param time
     */
    public void makeLineGraph(Timeline time){
        setSeries();
        SERIES_LIST = List.of(lineSeries1, lineSeries2, lineSeries3);
        cellKeys.forEach( (n) -> {

            SERIES_LIST.get((Integer) n).getData().add(new XYChart.Data<Number, Integer>(0, (Integer) cellNumbers.get(n), true));
            SERIES_LIST.get((Integer) n).setName("State " + n);
        });
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Step");
        yAxis.setLabel("Cell Population");
        myPane = new VBox();

        lineChart = new LineChart(xAxis, yAxis);
        lineChart.getData().addAll(lineSeries1, lineSeries2, lineSeries3);
        lineChart.setTitle("Current Cell States");
        lineChart.setLegendVisible(true);
        myPane.getChildren().add(lineChart);
        showGraph();
    }

    /**
     * updates bar graph by replacing series
     */
    public void updateBarGraph(){
        barSeries.getData().clear();
        cellKeys.forEach( (n) -> {
            barSeries.getData().add(new XYChart.Data<String, Integer>("State " + n, (Integer) cellNumbers.get(n), true));
        });

    }

    /**
     * updates line graph by adding a data point every time the grid is refreshed
     * @param timeline
     */

    public void updateLineGraph(Timeline timeline){
        updateTime += 1;
        cellKeys.forEach( (n) -> {

            SERIES_LIST.get((Integer) n).getData().add(new XYChart.Data<Number, Integer>(updateTime, (Integer) cellNumbers.get(n), true));
        });
    }

    /**
     * used to close the stage
     */
    public void shutDown(){
        setSeries();
        myStage.close();
    }


}
