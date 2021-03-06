package ServerGUI;

import ServerSide.ServerConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import HotDrinkVendingMachine.MaxValue;

import static ServerSide.StringCommandList.REFILL_ITEMS;

/**
 * Classe che gestisce la tab degli items.
 */

public class ItemsTab {
    private ObservableList<String> obsvData;
    private ServerConnection serverConnection;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<Double> quantity = new ArrayList<>();
    private Label label0 = new Label();
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Label label3 = new Label();
    private Label label4 = new Label();

    public ItemsTab(ObservableList<String> obsvData) {
        this.obsvData = obsvData;
    }

    /**
     * Funzione che imposta le progress bar.
     */
    protected BorderPane setProgressBar() {
        BorderPane mainPanel = new BorderPane();

        if (obsvData.isEmpty()) {
            return mainPanel;
        }

        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();

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

        ProgressBar progressBar0 = new ProgressBar(quantity.get(0) / MaxValue.MILK_MAX);
        progressBar0.setMinSize(200, 30);
        ProgressBar progressBar1 = new ProgressBar(quantity.get(1) / MaxValue.SUGAR_MAX);
        progressBar1.setMinSize(200, 30);
        ProgressBar progressBar2 = new ProgressBar(quantity.get(2) / MaxValue.SPOON_MAX);
        progressBar2.setMinSize(200, 30);
        ProgressBar progressBar3 = new ProgressBar(quantity.get(3) / MaxValue.CUP_MAX);
        progressBar3.setMinSize(200, 30);
        ProgressBar progressBar4 = new ProgressBar(quantity.get(4) / MaxValue.VODKA_MAX);
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

        gridPane.add(label0, 0, 0);
        gridPane.add(label1, 0, 1);
        gridPane.add(label2, 0, 2);
        gridPane.add(label3, 0, 3);
        gridPane.add(label4, 0, 4);

        gridPane.add(progressBar0, 1, 0);
        gridPane.add(progressBar1, 1, 1);
        gridPane.add(progressBar2, 1, 2);
        gridPane.add(progressBar3, 1, 3);
        gridPane.add(progressBar4, 1, 4);

        gridPane.add(p0, 2, 0);
        gridPane.add(p1, 2, 1);
        gridPane.add(p2, 2, 2);
        gridPane.add(p3, 2, 3);
        gridPane.add(p4, 2, 4);

        Button RefillAll = new Button("Ricarica\nmacchinetta");

        RefillAll.setOnAction(event -> {
            progressBar0.setProgress(MaxValue.MILK_MAX);
            progressBar1.setProgress(MaxValue.SUGAR_MAX);
            progressBar2.setProgress(MaxValue.SPOON_MAX);
            progressBar3.setProgress(MaxValue.CUP_MAX);
            progressBar4.setProgress(MaxValue.VODKA_MAX);

            p0.setProgress(MaxValue.MILK_MAX);
            p1.setProgress(MaxValue.SUGAR_MAX);
            p2.setProgress(MaxValue.SPOON_MAX);
            p3.setProgress(MaxValue.CUP_MAX);
            p4.setProgress(MaxValue.VODKA_MAX);

            this.serverConnection.chooseCommandExecutedByThread(REFILL_ITEMS);
        });

        gridPane.add(RefillAll, 3, 2);

        gridPane.prefHeightProperty().bind(vBox.heightProperty());
        gridPane.setHgap(25);
        gridPane.setTranslateX(150);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            rc.setFillHeight(true);
            gridPane.getRowConstraints().add(rc);
        }

        vBox.getChildren().add(gridPane);
        mainPanel.setCenter(vBox);

        return mainPanel;
    }

    protected void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }
}
