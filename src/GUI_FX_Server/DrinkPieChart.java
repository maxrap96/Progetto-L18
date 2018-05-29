package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class DrinkPieChart extends PieChart {

    private ObservableList<String> obsvstats;

    public DrinkPieChart(ObservableList<String> obsvstats) {
        this.obsvstats = obsvstats;
    }

    /**
     * funzione per inizializzare il borderpane
     * @return restituisce il borderpane
     */

    public BorderPane setChart() {

        BorderPane b = new BorderPane();
        if (obsvstats == null){
            return b;
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        ArrayList<String> beverageNames = new ArrayList<>();
        ArrayList<Integer> beverageQty = new ArrayList<>();
        statsAnalysis(obsvstats, beverageNames, beverageQty);

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
     * funzione che si occupa del conteggio delle bevande e capire quanto son state richieste
     * @param statsRows è l'arraylist contenente il file di statistiche
     * @param beverageNames è l'arraylist contenente i nomi delle bevande
     * @param beverageQty è la quantità delle bevande selezionate
     */

    private void statsAnalysis(ObservableList<String> statsRows, ArrayList<String> beverageNames, ArrayList<Integer> beverageQty) {

        for (int i = 0; i < statsRows.size(); i++){
            if (!statsRows.get(i).contains("*")){
                String row = statsRows.get(i);
                String[] splitted =  row.split("\t");
                checkBeverage(splitted[0], beverageNames, beverageQty);
            }
        }
    }

    /**
     * è la funzione che si occupa di verificare che la bevanda sia presente
     * @param beverage è la bevanda presa in osservazione
     * @param beverageNames è l'arraylist contenente i nomi delle bevande
     * @param beverageQty è la quantità di bevanda chiesta
     */
    private void checkBeverage(String beverage, ArrayList<String> beverageNames, ArrayList<Integer> beverageQty) {

        if (beverageNames.contains(beverage)) {
            for (int i = 0; i < beverageNames.size(); i++) {
                if (beverage.equals(beverageNames.get(i))) {
                    int quantity = beverageQty.get(i);
                    quantity++;
                    beverageQty.set(i, quantity);
                    break; // se ho trovato l'indice della bevanda corrisondente esco
                }
            }
        }
        else {
            //se arrivo qui significa che non l'ho trovata
            beverageNames.add(beverage);
            beverageQty.add(1);
        }
    }


}
