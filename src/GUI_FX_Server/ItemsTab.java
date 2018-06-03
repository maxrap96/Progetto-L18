package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class ItemsTab {
    private ObservableList<String> obsvData;
    private final double SUGARMAX = 1000.0;
    private final double MILKMAX = 1000.0;
    private final int CUPMAX = 500;
    private final int SPOONMAX = 1000;
    private final double VODKAMAX = 1000;

//    private Button[] refill = new Button[5];

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
        b.setStyle("-fx-background-color: green");

        if (obsvData.isEmpty())
            return b;

        VBox mainBox = new VBox();
        GridPane Pane = new GridPane();
        mainBox.setStyle("-fx-background-color: royalblue");
        Pane.setStyle("-fx-background-color: yellow");

        ArrayList<String> items = new ArrayList<>();
        ArrayList<Double> quantity = new ArrayList<>();

        // Acquisizione nomi e quantità rimanenti delle bevande
        int numRows = 0;

        for ( int i = 0; i < obsvData.size(); i++) {
            if ((!obsvData.get(i).startsWith("*")) && !(obsvData.get(i).startsWith("0")) ) {
                String[] splitted = obsvData.get(i).split("\t");
                items.add(splitted[0]);
                quantity.add(Double.parseDouble(splitted[1]));
                numRows++;
            }
        }

        label0.setText(items.get(0) + ": ");
        label0.setFont(Font.font("", FontWeight.BOLD, 25));
        label1.setText(items.get(1) + ": ");
        label1.setFont(Font.font("", FontWeight.BOLD, 25));
        label2.setText(items.get(2) + ": ");
        label2.setFont(Font.font("default", FontWeight.BOLD, 25));
        label3.setText(items.get(3) + ": ");
        label3.setFont(Font.font("", FontWeight.BOLD, 25));
        label4.setText(items.get(4) + ": ");
        label4.setFont(Font.font("", FontWeight.BOLD, 25));

        ProgressBar progressBar0 = new ProgressBar(quantity.get(0) / MILKMAX);
        progressBar0.setMinSize(200, 30);
        ProgressBar progressBar1 = new ProgressBar(quantity.get(1) / SUGARMAX);
        progressBar1.setMinSize(200, 30);
        ProgressBar progressBar2 = new ProgressBar(quantity.get(2) / SPOONMAX);
        progressBar2.setMinSize(200, 30);
        ProgressBar progressBar3 = new ProgressBar(quantity.get(3) / CUPMAX);
        progressBar3.setMinSize(200, 30);
        ProgressBar progressBar4 = new ProgressBar(quantity.get(4) / VODKAMAX);
        progressBar4.setMinSize(200, 30);

        ProgressIndicator p0 = new ProgressIndicator();
        p0.setProgress(progressBar0.getProgress());
        ProgressIndicator p1 = new ProgressIndicator();
        p1.setProgress(progressBar1.getProgress());
        ProgressIndicator p2 = new ProgressIndicator();
        p2.setProgress(progressBar2.getProgress());
        ProgressIndicator p3 = new ProgressIndicator();
        p3.setProgress(progressBar3.getProgress());
        ProgressIndicator p4 = new ProgressIndicator();
        p4.setProgress(progressBar4.getProgress());

//        Button[] refill = new Button[numRows];
//
//        for (int i = 0; i < numRows; i++) {
//            refill[i].setText("Ricarica");
//        }

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

        Pane.add(p0, 2, 0);
        Pane.add(p1, 2, 1);
        Pane.add(p2, 2, 2);
        Pane.add(p3, 2, 3);
        Pane.add(p4, 2, 4);

//        Pane.add(refill[0], 3, 0);
//        Pane.add(refill[1], 3, 1);
//        Pane.add(refill[2], 3, 2);
//        Pane.add(refill[3], 3, 3);
//        Pane.add(refill[4], 3, 4);

        Pane.prefHeightProperty().bind(mainBox.heightProperty());
        Pane.setHgap(25);
        Pane.setTranslateX(150);
        Pane.setGridLinesVisible(true);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            rc.setFillHeight(true);
            Pane.getRowConstraints().add(rc);
        }

        mainBox.getChildren().addAll(Pane);
        b.setCenter(mainBox);

        return b;
    }
}
