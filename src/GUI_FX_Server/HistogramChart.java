package GUI_FX_Server;

import Distributore.Coins;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import java.util.StringTokenizer;

public class HistogramChart extends BarChart {

    final static String[] Monete = {"0.05", "0.10", "0.20", "0.50", "1", "2"};
    Coins coins = new Coins();

    public HistogramChart(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    public BorderPane setBars(){
        BorderPane b = new BorderPane();

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);
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
        String numCoins = coins.moneyOnFile();
        int money[] = new int[Monete.length];
        StringTokenizer tokenizer = new StringTokenizer(numCoins, "\t");

        for(int i = 0; i < Monete.length; i++) {
            if (tokenizer.hasMoreTokens()) {
                money[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }

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