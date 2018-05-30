package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UsageChart extends LineChart {

    private ObservableList<String> stats;

    public UsageChart(Axis axis, Axis axis2, ObservableList<String> obsvStats) {
        super(axis, axis2);
        this.stats = obsvStats;
    }

    public BorderPane setGraph() {
        BorderPane b = new BorderPane();
        if (stats.isEmpty()){
            return b;
        }
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Giorno");
        yAxis.setLabel("Ordini effettuati");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(31);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        final LineChart<Number,Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Utilizzo macchinetta");

        ArrayList<XYChart.Series> series = new ArrayList<>();
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("2018");

        Map<String, Long> counter = dataAnalysis(stats);
        createseries(counter,series);

        for (int i = 0; i < series.size(); i++) {
            lineChart.getData().add(series.get(i));
        }

        b.setCenter(lineChart);

        return b;
    }

    private Map<String, Long> dataAnalysis(ObservableList<String> stats) {
        //  formato tipo delle stats Tè ai mirtilli	Transazione avvenuta il:	16-04-2018 00:33:26
        Map<String, Long> jj = stats.stream().map(s -> s.split("\t")[2].substring(0, 10))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return jj;
/*
        Map<String, Integer> counter = null;
        for (int i =0; i < stats.size(); i++ ) {
            for (int month = 1; month <= 12; month++){
                for (int day = 1; day <= 31; day++){
                    String currentDate = (day + "-0" + month + "-2018");
                    if (stats.get(i).contains( currentDate)) { // singifica che ho trovato la data di interesse
                        // mi passo giorno per giorno le date
                        if (counter.containsKey(currentDate)){
                            //singifica che avevo già trovato questa data
                            int count = counter.get(currentDate);
                            count++;
                            counter.replace(currentDate,count);
                        }
                        else {
                            counter.put(currentDate, 1);
                        }
                    }
                }
            }
        }
        return counter;
*/    }

    /**
     * funziona che crea la serie in base ai dati presenti nella mappa
     * @param counter è la mappa che contiene i conteggi per i relativi giorni
     * @param series sono le serie di dati per ogni mese
     */
    private void createseries(Map<String, Long> counter, ArrayList<Series> series) {

        for (int mese =1; mese <=12; mese++){
            boolean foundAny = false;
            Series series1 = new Series();
            String month;
            if (mese < 10){
                month = "-0" + mese;
            }
            else {
                month = String.valueOf(mese);
            }
            for (int day = 1; day <= 31; day++){
                String currentDate;
                if (day < 10) {
                    currentDate = ("0" + day + month+ "-2018");
                }
                else {
                    currentDate = (day + month + "-2018");
                }

                if (counter.containsKey(currentDate)){
                    series1.getData().add(new XYChart.Data( day ,counter.get(currentDate)));
                    series1.setName(String.valueOf(mese));
                    foundAny = true;
                }
            }
            if (foundAny){ //se non ne ho trovata nessuna non ha senso aggiungerle
                series.add(series1);
            }
        }
    }
}