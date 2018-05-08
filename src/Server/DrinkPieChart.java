package Server;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class DrinkPieChart extends PieChart {

    public BorderPane setChart() {
        BorderPane b = new BorderPane();

        //TODO MJ: collegare dati delle bevande al grafico tramite lettura file stats

        // Creazione array di dati
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Drink Rape", 1),
                new PieChart.Data("Drink Simone", 31),
                new PieChart.Data("Drink Dario", 10),
                new PieChart.Data("Drink Luce", 22));

        // Creazione grafico
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Acquisto bevande");

        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        b.setCenter(pieChart);

        return b;
    }
}