package GUI_FX_Server;

import PersonalExceptions.FileNotReadable;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class DrinkPieChart extends PieChart {
    Distributore.Data data = new Distributore.Data("src/File_Testo_Server/serverStats.txt");
    String[] tmp = {"Cioccolata", "Latte calvo", "Caffe lungo", "Te al limone", "Espresso",
            "Cappuccino", "Arabica", "Ginseng", "Corretto", "Top"};

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

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Raccolta dati (casuali al momento)
        for (int i = 0; i < tmp.length; i++) {
            pieChartData.add(new Data(tmp[i], (i+2)));
        }

        // Creazione grafico con relative impostazioni
        PieChart pie = new PieChart(pieChartData);
        pie.setTitle("Acquisto bevande");

        pie.setClockwise(true);
        pie.setLabelLineLength(50);
        pie.setLabelsVisible(true);
        pie.setStartAngle(180);

        b.setCenter(pie);

        return b;
    }
}