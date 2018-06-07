package ServerGUI;

import ServerSide.ServerConnection;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import HotDrinkVendingMachine.CoinsNumbers;
import static ServerSide.StringCommandList.REFILL_COINS;

public class CoinsChart extends BarChart {
    private final static String[] COINS = {"0.05", "0.10", "0.20", "0.50", "1", "2"};
    private ObservableList<String> coins;
    private ServerConnection serverConnection;

    public CoinsChart(Axis xAxis, Axis yAxis, ObservableList<String> coins) {
        super(xAxis, yAxis);
        this.coins = coins;
    }

    public BorderPane setBars() {
        BorderPane borderPane = new BorderPane();

        if (coins == null) {
            return borderPane;
        }

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> barChart = new BarChart<>(xAxis, yAxis);

        setChart(barChart, xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();

        // Ottenimento numero di monete
        int money[] = new int[COINS.length];
        analyzeData(money, coins);

        // Aggiunta dei dati alla serie
        for (int i = 0; i < COINS.length; i++) {
            series1.getData().add(new XYChart.Data(money[i], COINS[i]));
        }
        barChart.getData().add(series1);

        // Colorazione barre in base alla quantità di monete
        for (int i = 0; i < COINS.length; i++) {
            colorChartBars(barChart, i, money);
        }

        HBox buttonBox = new HBox();

        Button refill = new Button("Reset\nmonete");
        refill.setMaxWidth(80);
        refill.setMinHeight(50);
        refill.prefHeightProperty().bind(buttonBox.heightProperty());
        refill.prefWidthProperty().bind(buttonBox.widthProperty());

        refill.setOnAction(event -> serverConnection.chooseCommandExecutedByThread(REFILL_COINS));

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(25);
        buttonBox.getChildren().add(refill);

        borderPane.setCenter(barChart);
        borderPane.setBottom(buttonBox);
        return borderPane;
    }

    /**
     * Funzione per ottenere il numero di monete.
     * @param money quantità di monete rimanenti di un singolo taglio.
     * @param coins informazioni su tutti i tagli di monete.
     */
    private void analyzeData(int[] money, ObservableList<String> coins) {
        if (!coins.isEmpty()) {
            String s = coins.get(coins.size() - 1);
            String[] split = s.split("\t");

            for (int i = 0; i < COINS.length; i++) {
                money[i] = Integer.parseInt(split[i]);
            }
        }
    }

    /**
     * Funzione che permette di modificare alcuni parametri di base del grafico.
     * @param barChart grafico.
     * @param xAxis asse x.
     * @param yAxis asse y.
     */
    private void setChart(BarChart<Number, String> barChart, NumberAxis xAxis, CategoryAxis yAxis) {
        barChart.setTitle("Coins");

        xAxis.setLabel("Numero monete rimaste");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(CoinsNumbers.DEFAULT_MONEY[0] + 5);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        yAxis.setLabel("Tagli di monete [€]");
        barChart.setLegendVisible(false);
    }

    /**
     * Funzione per colorare in modo diverso le barre del grafico in base alla quantità presente.
     * @param barChart istogramma.
     * @param i contatore che indica la barra considerata.
     * @param money quantità di monete.
     */
    private void colorChartBars(BarChart barChart, int i, int money[]) {
        String st = ".data" + i + ".chart-bar";
        Node node = barChart.lookup(st);

        if (money[i] > 22) {
            node.setStyle("-fx-bar-fill: springgreen");
        } else if (money[i] > 14) {
            node.setStyle("-fx-bar-fill: gold");
        } else {
            node.setStyle("-fx-bar-fill: tomato");
        }
    }

    protected void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }
}
