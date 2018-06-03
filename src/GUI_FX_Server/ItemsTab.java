package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class ItemsTab {
    private ObservableList<String> obsvData;
    private final double SUGARMAX = 1000.0;
    private final double MILKMAX = 1000.0;
    private final int CUPMAX = 500;
    private final int SPOONMAX = 1000;
    private final double VODKAMAX = 1000;

    private Label label0 = new Label();
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();
    private Label label4 = new Label();

    public ItemsTab(ObservableList<String> obsvData) {
        this.obsvData = obsvData;
    }

    public BorderPane setProgressBar() {
        BorderPane b = new BorderPane();

        if (obsvData.isEmpty())
            return b;

        VBox mainBox = new VBox();
        GridPane Pane = new GridPane();

        ArrayList<String> items = new ArrayList<>();
        ArrayList<Double> quantity = new ArrayList<>();

        // Acquisizione nomi e quantità rimanenti delle bevande
        for ( int i = 0; i < obsvData.size(); i++) {
            if ((!obsvData.get(i).startsWith("*")) && !(obsvData.get(i).startsWith("0")) ) {
                String[] splitted = obsvData.get(i).split("\t");
                items.add(splitted[0]);
                quantity.add(Double.parseDouble(splitted[1]));
            }
        }

        label0.setText(items.get(0));
        label1.setText(items.get(1));
        label2.setText(items.get(2));
        label3.setText(items.get(3));
        label4.setText(items.get(4));

        ProgressBar progressBar0 = new ProgressBar(quantity.get(0) / MILKMAX);
        ProgressBar progressBar1 = new ProgressBar(quantity.get(1) / SUGARMAX);
        ProgressBar progressBar2 = new ProgressBar(quantity.get(2) / SPOONMAX);
        ProgressBar progressBar3 = new ProgressBar(quantity.get(3) / CUPMAX);
        ProgressBar progressBar4 = new ProgressBar(quantity.get(4) / VODKAMAX);

        Pane.add(label0, 0, 0);
        Pane.add(label1, 0, 1);
        Pane.add(label2, 0, 2);
        Pane.add(label3, 0, 3);
        Pane.add(label4, 0, 4);

        Pane.add(progressBar0, 1, 0);
        Pane.add(progressBar1, 1, 1);
        Pane.add(progressBar2, 1, 2);
        Pane.add(progressBar3, 1, 3);
        Pane.add(progressBar4, 1, 4);

        mainBox.getChildren().addAll(Pane);
        b.setCenter(mainBox);

        return b;
    }
}
