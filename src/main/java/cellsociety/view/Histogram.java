package cellsociety.view;

import java.util.HashMap;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Histogram{
    private Stage myStage;
    private HashMap cellNumbers;
    private GridView myGrid;
    private HashMap<Integer, Integer> testGrid;

    public Histogram(GridView theGrid){
        myStage = new Stage();
        myStage.setTitle("Current Cell Numbers");
        myGrid = theGrid;
        cellNumbers = myGrid.getCurrentStates();
        System.out.println("created Histo");
    }

    public void createGraph(){
        System.out.println("Oh boy this ran");
        XYChart.Series<Integer, Integer> series1 = new XYChart.Series<>();
        Set keys = cellNumbers.keySet();
        System.out.println(keys);
        System.out.println(cellNumbers);
        keys.forEach( (n) -> {
            series1.getData().add(new XYChart.Data(n, cellNumbers.get(n)));
        });
        FlowPane myPane = new FlowPane();
        Scene scene = new Scene(myPane, 600, 600);
        myStage.setScene(scene);
        myStage.show();
    }

    public void testGrid(){
        cellNumbers.put(0,1);
        Text hghg = new Text("mgm");
        Pane hgh = new Pane();
        Scene scene = new Scene(hgh, 600, 600);
        myStage.setScene(scene);
        myStage.show();
    }

}
