package GUI_FX_Server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChart extends Application {
    final static String coins = "0.05";
    final static String coins2 = "0.10";
    final static String coins3 = "0.20";
    final static String coin4 = "0.50";
    final static String coins5 = "1";
    final static String coins6 = "2";

    @Override
    public void start(Stage stage) {
        stage.setTitle("Monete rimanenti:");
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final javafx.scene.chart.BarChart<Number, String> bc =
                new javafx.scene.chart.BarChart<Number, String>(xAxis, yAxis);
        bc.setTitle("Coins");
        xAxis.setLabel("Numero monete rimaste");
        yAxis.setLabel("Tagli di monete [€]");

        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(100, coins));
        series1.getData().add(new XYChart.Data(176, coins2));
        series1.getData().add(new XYChart.Data(100, coins3));
        series1.getData().add(new XYChart.Data(200, coin4));
        series1.getData().add(new XYChart.Data(50, coins5));
        series1.getData().add(new XYChart.Data(300, coins6));

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
}