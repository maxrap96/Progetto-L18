package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemsHistogram extends BarChart {
    private ObservableList<String> obsvData;
    double SUGARMAX = 1000.0;
    double MILKMAX = 1000.0;
    int CUPMAX = 500;
    int SPOONMAX = 1000;
    double VODKAMAX = 1000;

    Label label0 = new Label();
    Label label1 = new Label();
    Label label2 = new Label();
    Label label3 = new Label();
    Label label4 = new Label();
    final HBox hBoxes[] = new HBox [5];

    public ItemsHistogram(Axis xAxis, Axis yAxis, ObservableList<String> obsvData) {
        super(xAxis, yAxis);
        this.obsvData = obsvData;
    }

    public BorderPane setBars() {
        BorderPane b = new BorderPane();

        if (obsvData.isEmpty())
            return b;

        VBox box = new VBox();

        for (int i = 0; i < 5; i++) {
            hBoxes[i] = new HBox();
        }

        ProgressBar progressBar0 = new ProgressBar(0.6);
        ProgressBar progressBar1 = new ProgressBar(0.8);
        ProgressBar progressBar2 = new ProgressBar(0.3);
        ProgressBar progressBar3 = new ProgressBar(0.1);
        ProgressBar progressBar4 = new ProgressBar(0.2);

        label0.setText("Zucchero");
        label1.setText("Latte");
        label2.setText("Bicc");
        label3.setText("Cucch");
        label4.setText("Vodka");

        hBoxes[0].getChildren().addAll(label0, progressBar0);
        hBoxes[1].getChildren().addAll(label1, progressBar1);
        hBoxes[2].getChildren().addAll(label2, progressBar2);
        hBoxes[3].getChildren().addAll(label3, progressBar3);
        hBoxes[4].getChildren().addAll(label4, progressBar4);

        box.getChildren().addAll(hBoxes[0], hBoxes[1], hBoxes[2], hBoxes[3], hBoxes[4]);

//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//
//        final BarChart<String,Number> itemsChart = new BarChart<>(xAxis, yAxis);
//        itemsChart.setTitle("Oggetti vari:");
//        itemsChart.setLegendVisible(false);
//
//        xAxis.setLabel("Items");
//        yAxis.setLabel("Quantità");
//
//        ArrayList<String> items = new ArrayList<>();
//        ArrayList<Double> quantity = new ArrayList<>();
//        // Analizza la quantità rimanente
//        for ( int i = 0; i < obsvData.size(); i++) {
//            //cerco solo i dati degli items
//            if ((!obsvData.get(i).startsWith("*")) && !(obsvData.get(i).startsWith("0")) ) {
//                String[] splitted = obsvData.get(i).split("\t");
//                items.add(splitted[0]);
//                quantity.add(parseDouble(splitted[1]));
//            }
//        }
//
//        XYChart.Series series = new XYChart.Series();
//
//        for (int i = 0; i < items.size(); i++ ) {
//            series.getData().add(new XYChart.Data(items.get(i), quantity.get(i)));
//        }
//
//        itemsChart.getData().add(series);

        b.setCenter(box);

        return b;
    }
}
