package GUI_FX_Server;

import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;

public class ItemsHistogram extends BarChart {
    public ItemsHistogram(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    public BorderPane setBars() {
        BorderPane b = new BorderPane();

        final String cups = "Bicchierini";
        final String spoons = "Cucchiaini";
        final String sugar = "Zucchero";
        final String milk = "Latte";

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> itemsChart = new BarChart<>(xAxis, yAxis);
        itemsChart.setTitle("Oggetti vari:");
        itemsChart.setLegendVisible(false);

        xAxis.setLabel("Items");
        yAxis.setLabel("Quantità");

        XYChart.Series series1 = new XYChart.Series();

        series1.getData().add(new XYChart.Data(cups, 61));
        series1.getData().add(new XYChart.Data(milk, 358));
        series1.getData().add(new XYChart.Data(spoons, 99));
        series1.getData().add(new XYChart.Data(sugar, 207));

        itemsChart.getData().add(series1);

        b.setCenter(itemsChart);

        return b;
    }
}