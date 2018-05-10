package GUI_FX_Server;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class UsageChart extends LineChart {

    public UsageChart(Axis axis, Axis axis2) {
        super(axis, axis2);
    }

    public BorderPane setGraph() {
        BorderPane b = new BorderPane();

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Giorno");

        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Utilizzo macchinetta");

        XYChart.Series series = new XYChart.Series();
        series.setName("2018");

        // Raccolta dati (random)
        for (int i = 0; i < 10; i++) {
            series.getData().add(new XYChart.Data(i, (i+1)));
        }

        lineChart.getData().add(series);

        b.setCenter(lineChart);

        return b;
    }
}