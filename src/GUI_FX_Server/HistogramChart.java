package GUI_FX_Server;

import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HistogramChart extends BarChart {

    private ArrayList<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    private final String[] MONETE = {"0.05", "0.10", "0.20", "0.50", "1", "2"};

    public HistogramChart(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    public BorderPane setBars(ArrayList<String> coinsString){
        BorderPane tabMainPane = new BorderPane();

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> coinsChart = new BarChart<>(xAxis, yAxis);
        coinsChart.setTitle("Coins");

        xAxis.setLabel("Numero monete rimaste");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(45);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        yAxis.setLabel("Tagli di monete [€]");
        coinsChart.setLegendVisible(false);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Serie 1");

        // Ottenimento numero di monete
//        /*String numCoins = coins.moneyOnFile();
        int money[] = new int[MONETE.length];

        if(!coinsString.isEmpty()) {
            StringTokenizer tokenizer = new StringTokenizer(coinsString.get(0), "\t");

            for(int i = 0; i < MONETE.length; i++) {
                if (tokenizer.hasMoreTokens()) {
                    money[i] = Integer.parseInt(tokenizer.nextToken());
                }
            }

            // Aggiunta dei dati alla serie
            for (int i = 0; i < MONETE.length; i++) {
                series1.getData().add(i, new XYChart.Data(money[i], MONETE[i]));
            }

            coinsChart.getData().add(series1);

            for (int i = 0; i < MONETE.length; i++) {
                colorChartBars(coinsChart, i, money);
            }
        } else {
            // Aggiunta dei dati alla serie
            for (int i = 0; i < MONETE.length; i++) {
                series1.getData().add(new XYChart.Data(10, MONETE[i]));
            }
            coinsChart.getData().add(series1);
        }

//        StringTokenizer tokenizer = new StringTokenizer(numCoins, "\t");
//
//        for(int i = 0; i < Monete.length; i++) {
//            if (tokenizer.hasMoreTokens()) {
//                money[i] = Integer.parseInt(tokenizer.nextToken());
//            }
//        }
//
//        // Aggiunta dei dati alla serie
//        for (int i = 0; i < Monete.length; i++) {
//            series1.getData().add(new XYChart.Data(money[i], Monete[i]));
//        }
//        coinsChart.getData().add(series1);
//
//        for (int i = 0; i < Monete.length; i++) {
//            colorChartBars(coinsChart, i, money);
//        }*/



        tabMainPane.setCenter(coinsChart);
        return tabMainPane;
    }

    /**
     * Funzione per visualizzare livelli di 'warning' delle monete
     * @param chart istogramma
     * @param i indice della barra da colorare
     * @param money quantità di monete
     */
    public void colorChartBars(BarChart chart, int i, int money[]) {
        String cssBarNumber = ".data" + i + ".chart-bar";

        Node node = chart.lookup(cssBarNumber);

        if (money[i] > 22) {
            node.setStyle("-fx-bar-fill: springgreen");
        } else if (money[i] > 15) {
            node.setStyle("-fx-bar-fill: gold");
        } else {
            node.setStyle("-fx-bar-fill: orangered");
        }
    }
}