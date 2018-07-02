package ServerGUI;

import ServerSide.ServerConnection;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

import static ServerSide.StringCommandList.REFILL_INGREDIENTS;

/**
 * Classe che si occupa del grafico a barre delle bevande.
 */

public class DrinkChart extends BarChart {
    private ObservableList<String> data;
    private ObservableList<String> menu;
    private ServerConnection serverConnection;

    public DrinkChart(Axis xAxis, Axis yAxis, ObservableList<String> data, ObservableList<String> menu) {
        super(xAxis, yAxis);
        this.data = data;
        this.menu = menu;
    }

    /**
     *Funzione che inizializza la chart.
     */
    protected BorderPane initChart() {
        BorderPane mainTabPane = new BorderPane();

        if (data == null || menu == null) {
            return mainTabPane;
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> drinksChart = new BarChart<>(xAxis, yAxis);

        drinksChart.setTitle("Quantità rimanenti bevande");
        drinksChart.setLegendVisible(false);

        xAxis.setLabel("Bevanda");
        yAxis.setLabel("Quantità");

        XYChart.Series series1 = new XYChart.Series();

        ArrayList<String> drinkName = new ArrayList<>();
        ArrayList<Double> drinkQty = new ArrayList<>();

        analyzeData(drinkName, drinkQty, data, menu);
        if (!data.isEmpty() && !menu.isEmpty()) {
            for (int j = 0; j < drinkName.size(); j++) {
                if (j < drinkQty.size()) {
                    series1.getData().add(new XYChart.Data(drinkName.get(j), drinkQty.get(j)));
                } else {
                    series1.getData().add(new XYChart.Data(drinkName.get(j), 0)); //prova risolutiva del bug
                }
            }
        }
        drinksChart.getData().add(series1);

        if (!data.isEmpty() && !menu.isEmpty()) {
            for (int i = 0; i < drinkName.size(); i++) {
                if (i < drinkQty.size()) {
                    colorChartBars(drinksChart, i, drinkQty.get(i));
                }
                else {
                    colorChartBars(drinksChart, i, 0);
                }
            }
        }
        HBox buttonBox = new HBox();

        Button refill = new Button("Ricarica bevande");
        refill.setOnAction(event -> serverConnection.chooseCommandExecutedByThread(REFILL_INGREDIENTS));

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(refill);

        mainTabPane.setCenter(drinksChart);
        mainTabPane.setBottom(buttonBox);

        return mainTabPane;
    }

    /**
     * Funzione che che confronta il menù e il file data.
     */
    private void analyzeData(ArrayList<String> drinkName, ArrayList<Double> drinkQty,
                             ObservableList<String> data, ObservableList<String> menu) {

        for (int rows = 0; rows < menu.size(); rows++) {
            if (menu.get(rows).startsWith("0") && !data.isEmpty()) {
                String[] splitMenu = menu.get(rows).split("\t");
                drinkName.add(splitMenu[2]);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).startsWith("0")) {
                String[] splitData = data.get(i).split("\t");
                drinkQty.add(Double.parseDouble(splitData[1]));
            }
        }
    }

    /**
     * Funzione che colora le barre del grafico.
     */
    private void colorChartBars(BarChart barChart, int i, double qty) {
        String st = ".data" + i + ".chart-bar";
        Node node = barChart.lookup(st);

        if (qty > 35) {
            node.setStyle("-fx-bar-fill: springgreen");
        } else if (qty > 20) {
            node.setStyle("-fx-bar-fill: gold");
        } else {
            node.setStyle("-fx-bar-fill: crimson");
        }
    }

    protected void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }
}
