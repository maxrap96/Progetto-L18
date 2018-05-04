package GUI_FX_Server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChart extends Application {
    final static String[] Coins = {"0.05", "0.10", "0.20", "0.50", "1", "2"};

    double a = 30.0;    // Valore a cazzo

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

        // Inizializzazione valori a random, giusto per vedere il grafico
        for (int i = 0; i < Coins.length; i++) {
            series1.getData().add(new XYChart.Data((i + 1) * a, Coins[i]));
        }

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
}