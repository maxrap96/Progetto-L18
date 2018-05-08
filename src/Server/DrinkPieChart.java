package Server;

import PersonalExceptions.FileNotReadable;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class DrinkPieChart extends PieChart {
    Distributore.Data data = new Distributore.Data("src/File_Testo_Server/serverStats.txt");

    public BorderPane setChart() {
        BorderPane b = new BorderPane();
        ArrayList<String[]> a;
        String[] a1;

        try {
            a = data.readFile();
        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }

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