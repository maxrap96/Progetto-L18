package ServerGUI;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class DrinkPieChart extends PieChart {
    private ObservableList<String> obsvStats;

    public DrinkPieChart(ObservableList<String> obsvStats) {
        this.obsvStats = obsvStats;
    }

    /**
     * Funzione per inizializzare il borderpane.
     * @return borderpane.
     */
    public BorderPane setChart() {
        BorderPane b = new BorderPane();

        if (obsvStats == null) {
            return b;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        ArrayList<String> beverageNames = new ArrayList<>();
        ArrayList<Integer> beverageQty = new ArrayList<>();
        statsAnalysis(obsvStats, beverageNames, beverageQty);

        for (int i = 0; i < beverageNames.size(); i++) {
            pieChartData.add(new Data(beverageNames.get(i), beverageQty.get(i)));
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

    /**
     * Funzione che si occupa del conteggio delle bevande e capire quanto son state richieste.
     * @param statsRows contiene il file di statistiche.
     * @param beverageNames continene i nomi delle bevande.
     * @param beverageQty quantità delle bevande selezionate.
     */
    private void statsAnalysis(ObservableList<String> statsRows, ArrayList<String> beverageNames,
                               ArrayList<Integer> beverageQty) {

        for (int i = 0; i < statsRows.size(); i++) {
            if (!statsRows.get(i).contains("*")) {
                String row = statsRows.get(i);
                String[] splitted =  row.split("\t");
                checkBeverage(splitted[0], beverageNames, beverageQty);
            }
        }
    }

    /**
     * Funzione che si occupa di verificare che la bevanda sia presente.
     * @param beverage bevanda presa in osservazione.
     * @param beverageNames contiene i nomi delle bevande.
     * @param beverageQty quantità di bevanda chiesta.
     */
    private void checkBeverage(String beverage, ArrayList<String> beverageNames, ArrayList<Integer> beverageQty) {
        if (beverageNames.contains(beverage)) {
            for (int i = 0; i < beverageNames.size(); i++) {
                if (beverage.equals(beverageNames.get(i))) {
                    int quantity = beverageQty.get(i);
                    quantity++;
                    beverageQty.set(i, quantity);
                    break; // Se si trova l'indice della bevanda corrispondente esce
                }
            }
        }
        else {
            // Se si arriva qui significa che non l'ha trovata
            beverageNames.add(beverage);
            beverageQty.add(1);
        }
    }
}
