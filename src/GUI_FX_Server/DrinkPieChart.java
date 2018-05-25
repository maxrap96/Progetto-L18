package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.HashMap;

public class DrinkPieChart extends PieChart {
    private ArrayList<String> statsRows;

    public DrinkPieChart(ArrayList<String> statsRow) {
        this.statsRows = statsRow;
    }

    public BorderPane setChart() {
        BorderPane b = new BorderPane();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        ArrayList<String> beverageNames = new ArrayList<>();
        ArrayList<Integer> beverageQty = new ArrayList<>();
        statsAnalisys(statsRows, beverageNames, beverageQty);

        // Raccolta dati
        for (int i = 0; i < statsRows.size(); i++) {
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
     * funzione che si occupa del conteggio delle bevande e capire quanto son state richieste
     * @param statsRows è l'arraylist contenente il file di statistiche
     * @param beverageNames è l'arraylist contenente i nomi delle bevande
     * @param beverageQty è la quantità delle bevande selezionate
     * @return da una hashmap nel quale associa le stringhe alle quantità
     */

    private void statsAnalisys(ArrayList<String> statsRows, ArrayList<String> beverageNames, ArrayList<Integer> beverageQty) {

        for (int i = 0; i < statsRows.size(); i++){
            String row = statsRows.get(i);
            String[] splitted =  row.split("\t");
            checkBeverage(splitted[0], beverageNames, beverageQty);
        }
    }

    /**
     * è la funzione che si occupa di verificare che la bevanda
     * @param beverage è la bevanda presa in osservazione
     * @param beverageNames è l'arraylist contenente i nomi delle bevande
     * @param beverageQty è la quantità di bevanda chiesta
     */
    private void checkBeverage(String beverage, ArrayList<String> beverageNames, ArrayList<Integer> beverageQty) {
        boolean found = false;
        for (int i = 0; i < beverageNames.size(); i++) {
            if (beverage.equals(beverageNames.get(i))) {
                int quantity = beverageQty.get(i);
                quantity++;
                beverageQty.set(i, quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            //se arrivo qui significa che non lo ho trovata
            beverageNames.add(beverage);
            beverageQty.add(1);
        }
    }


}
