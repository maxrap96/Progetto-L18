package ServerGUI;

import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe che gestisce il grafico dell'utilizzo.
 */

public class UsageChart extends LineChart {
    private ObservableList<String> stats;
    private final String MONTHS[] = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};

    public UsageChart(Axis axis, Axis axis2, ObservableList<String> obsvStats) {
        super(axis, axis2);
        this.stats = obsvStats;
    }

    /**
     * Funzione che inizializza il grafico.
     */
    protected BorderPane setGraph() {
        BorderPane b = new BorderPane();

        if (stats.isEmpty())
            return b;

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);

        chartSettings(lineChart, xAxis, yAxis);

        // Ottenimento dati del grafico
        ArrayList<XYChart.Series> series = new ArrayList<>();

        Map<String, Long> counter = dataAnalysis(stats);
        createSeries(counter, series);

        // Aggiunta dati alla serie
        for (int i = 0; i < series.size(); i++) {
            lineChart.getData().add(series.get(i));
        }

        b.setCenter(lineChart);
        return b;
    }

    /**
     * Funzione per modificare impostazioni di base del grafico.
     * @param lineChart Grafico.
     * @param xAxis asse x.
     * @param yAxis asse y.
     */
    private void chartSettings(LineChart<Number, Number> lineChart, NumberAxis xAxis, NumberAxis yAxis) {
        lineChart.setTitle("Utilizzo macchinetta");

        xAxis.setLabel("Giorno");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(31);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        yAxis.setLabel("Ordini effettuati");
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(34);
        yAxis.setTickUnit(2);
        yAxis.setMinorTickVisible(false);
    }

    /**
     * Funzione che conteggia utilizzo mensile.
     */
    private Map<String, Long> dataAnalysis(ObservableList<String> stats) {
        //  Formato tipo delle stats: Tè ai mirtilli	Transazione avvenuta il:	16-04-2018 00:33:26
        Map<String, Long> dailyCount = stats.stream().map(s -> s.split("\t")[2].substring(0, 10))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return dailyCount;
    }

    /**
     * Funziona che crea la serie in base ai dati presenti nella mappa.
     * @param counter mappa che contiene i conteggi per i relativi giorni.
     * @param series sono le serie di dati per ogni mese.
     */
    private void createSeries(Map<String, Long> counter, ArrayList<Series> series) {
        for (int mese = 1; mese <= 12; mese++) {
            boolean foundAny = false;
            Series series1 = new Series();
            String month;
            if (mese < 10) {
                month = "-0" + mese;
            }
            else {
                month = String.valueOf(mese);
            }
            for (int day = 1; day <= 31; day++) {
                String currentDate;
                if (day < 10) {
                    currentDate = ("0" + day + month + "-2018");
                }
                else {
                    currentDate = (day + month + "-2018");
                }

                if (counter.containsKey(currentDate)) {
                    series1.getData().add(new XYChart.Data( day ,counter.get(currentDate)));
                    series1.setName(MONTHS[mese - 1]);
                    foundAny = true;
                }
            }
            if (foundAny) {         // Se non se ne trovano non vengono aggiunte
                series.add(series1);
            }
        }
    }
}
