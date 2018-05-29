package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ItemsHistogram extends BarChart {

    private ObservableList<String> obsvData;

    public ItemsHistogram(Axis xAxis, Axis yAxis, ObservableList<String> obsvData) {
        super(xAxis, yAxis);
        this.obsvData = obsvData;
    }

    public BorderPane setBars() {
        BorderPane b = new BorderPane();
        if (obsvData.isEmpty()) {
            return b;
        }


        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> itemsChart = new BarChart<>(xAxis, yAxis);
        itemsChart.setTitle("Oggetti vari:");
        itemsChart.setLegendVisible(false);

        xAxis.setLabel("Items");
        yAxis.setLabel("Quantità");

        ArrayList<String> items = new ArrayList<>();
        ArrayList<Double> quantity = new ArrayList<>();
        //analizzo la quntità rimanente
        for ( int i = 0; i < obsvData.size(); i++){
            //cerco solo i dati degli items
            if ((!obsvData.get(i).startsWith("*")) && !(obsvData.get(i).startsWith("0")) ){
                String[] splitted = obsvData.get(i).split("\t");
                items.add(splitted[0]);
                quantity.add(parseDouble(splitted[1]));
            }
        }

        XYChart.Series series = new XYChart.Series();

        for (int i = 0; i < items.size(); i++ ){
            series.getData().add(new XYChart.Data(items.get(i), quantity.get(i)));
        }

        itemsChart.getData().add(series);

        b.setCenter(itemsChart);

        return b;
    }
}