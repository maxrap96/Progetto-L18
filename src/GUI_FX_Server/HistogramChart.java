package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.*;

public class HistogramChart extends BarChart {

    final static String[] Monete = {"0.05", "0.10", "0.20", "0.50", "1", "2"};
    private ObservableList<String> coins;

    public HistogramChart(Axis xAxis, Axis yAxis, ObservableList<String> coins) {
        super(xAxis, yAxis);
        this.coins = coins;
    }

    public BorderPane setBars(){
        BorderPane b = new BorderPane();

        if (coins == null) {
            return b;
        }

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);

//        setChart(bc, xAxis, yAxis);

        bc.setTitle("Coins");

        xAxis.setLabel("Numero monete rimaste");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(45);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        yAxis.setLabel("Tagli di monete [€]");
        bc.setLegendVisible(false);

        XYChart.Series series1 = new XYChart.Series();

        // Ottenimento numero di monete
        int money[] = new int[Monete.length];

//        StringTokenizer tokenizer = new StringTokenizer(numCoins, "\t");
//
//        for(int i = 0; i < Monete.length; i++) {
//            if (tokenizer.hasMoreTokens()) {
//                money[i] = Integer.parseInt(tokenizer.nextToken());
//            }
//        }

        analyzeData(money, coins);

        // Aggiunta dei dati alla serie
        for (int i = 0; i < Monete.length; i++) {
            series1.getData().add(new XYChart.Data(money[i], Monete[i]));
        }
        bc.getData().add(series1);

        for (int i = 0; i < Monete.length; i++) {
            colorChartBars(bc, i, money);
        }

        b.setCenter(bc);
        return b;
    }

    private void analyzeData(int[] money, ObservableList<String> coins) {
        if(!coins.isEmpty()) {

            String s = coins.get(coins.size() - 1);
            String[] split = s.split("\t");

            for(int i = 0; i < Monete.length; i++) {
                money[i] = Integer.parseInt(split[i]);
            }
        }
    }

//    /**
//     * Funzione che permette di modificare alcuni parametri del grafico
//     * @param bc grafico
//     * @param xAxis asse x
//     * @param yAxis asse y
//     */
//    public void setChart(BarChart<Number, String> bc, NumberAxis xAxis, CategoryAxis yAxis) {
//        bc.setTitle("Coins");
//
//        xAxis.setLabel("Numero monete rimaste");
//        xAxis.setAutoRanging(false);
//        xAxis.setLowerBound(0);
//        xAxis.setUpperBound(45);
//        xAxis.setTickUnit(1);
//        xAxis.setMinorTickVisible(false);
//
//        yAxis.setLabel("Tagli di monete [€]");
//        bc.setLegendVisible(false);
//    }

    public void colorChartBars(BarChart bc, int i, int money[]) {
        String st = ".data" + i + ".chart-bar";

        Node node = bc.lookup(st);

        if (money[i] > 22) {
            node.setStyle("-fx-bar-fill: springgreen");
        } else if (money[i] > 15) {
            node.setStyle("-fx-bar-fill: gold");
        } else {
            node.setStyle("-fx-bar-fill: orangered");
        }
    }
}