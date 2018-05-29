package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;

public class DrinkChart extends BarChart {

    private ObservableList<String> data;
    private ObservableList<String> menu;

    public DrinkChart(Axis xAxis, Axis yAxis, ObservableList<String> data, ObservableList<String> menu) {
        super(xAxis, yAxis);
        this.data = data;
        this.menu = menu;
    }

    public BorderPane initChart() {
        BorderPane mainTabPane = new BorderPane();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> itemsChart = new BarChart<>(xAxis, yAxis);
        itemsChart.setTitle("Oggetti vari:");
        itemsChart.setLegendVisible(false);

        xAxis.setLabel("Items");
        yAxis.setLabel("Quantità");

        XYChart.Series series1 = new XYChart.Series();

        series1.getData().add(new XYChart.Data("Latte caldo", 61));
        series1.getData().add(new XYChart.Data("Cappuccino", 358));
        series1.getData().add(new XYChart.Data("Espresso", 99));
        series1.getData().add(new XYChart.Data("The ai mirtilli", 207));

        itemsChart.getData().add(series1);

        mainTabPane.setCenter(itemsChart);

        return mainTabPane;
    }
}
