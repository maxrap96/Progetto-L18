package GUI_FX_Server;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class HistogramChart extends BarChart {

    final static String[] Coins = {"0.05", "0.10", "0.20", "0.50", "1", "2"};
    final double a = 30.0;  // Valore a cazzo

    public HistogramChart(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }


    public void recallChart() {

    }


    public void start(Stage stage) {
        MenuBar1 mb = new MenuBar1();
        VBox vBox = new VBox(mb);
        vBox.setFillWidth(true);
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
        GridPane gPane = new GridPane();                //Creazione GridPanel per aggiungere menubar e BarChart
        gPane.setPrefSize(800, 550);
        gPane.setMinSize(800, 550);
        ColumnConstraints Col = new ColumnConstraints();
        Col.setHgrow(Priority.ALWAYS);
        gPane.getColumnConstraints().addAll(Col);
        gPane.addRow(0,vBox);
        gPane.addRow(1,bc);

        Scene scene = new Scene(gPane, 800, 600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
}