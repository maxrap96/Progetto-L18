package GUI_FX_Server;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class HistogramChart extends BarChart {
    private final static String[] MONETE = {"0.05", "0.10", "0.20", "0.50", "1", "2"};
    private ObservableList<String> coins;

    public HistogramChart(Axis xAxis, Axis yAxis, ObservableList<String> coins) {
        super(xAxis, yAxis);
        this.coins = coins;
    }

    public BorderPane setBars(){
        BorderPane b = new BorderPane();

        if (coins == null)
            return b;

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);

        setChart(bc, xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();

        // Ottenimento numero di monete
        int money[] = new int[MONETE.length];

        analyzeData(money, coins);

        // Aggiunta dei dati alla serie
        for (int i = 0; i < MONETE.length; i++) {
            series1.getData().add(new XYChart.Data(money[i], MONETE[i]));
        }
        bc.getData().add(series1);

        // Colorazione barre in base alla quantità di monete
        for (int i = 0; i < MONETE.length; i++) {
            colorChartBars(bc, i, money);
        }

        HBox buttonBox = new HBox();
        Button refreshBtn = new Button();

        Image img = new Image("ServerImages/RefreshBtn.jpg");
        Circle circle = new Circle(23);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);

        refreshBtn.setGraphic(circle);

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(refreshBtn);

        b.setCenter(bc);
        b.setBottom(buttonBox);
        return b;
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

            for (int i = 0; i < MONETE.length; i++) {
                money[i] = Integer.parseInt(split[i]);
            }
        }
    }

    /**
     * Funzione che permette di modificare alcuni parametri di base del grafico.
     * @param bc grafico.
     * @param xAxis asse x.
     * @param yAxis asse y.
     */
    public void setChart(BarChart<Number, String> bc, NumberAxis xAxis, CategoryAxis yAxis) {
        bc.setTitle("Coins");

        xAxis.setLabel("Numero monete rimaste");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(45);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        yAxis.setLabel("Tagli di monete [€]");
        bc.setLegendVisible(false);
    }

    /**
     * Funzione per colorare in modo diverso le barre del grafico in base alla quantità presente.
     * @param bc istogramma.
     * @param i contatore che indica la barra presa in considerazione.
     * @param money quantità di monete.
     */
    public void colorChartBars(BarChart bc, int i, int money[]) {
        String st = ".data" + i + ".chart-bar";

        Node node = bc.lookup(st);

        if (money[i] > 22) {
            node.setStyle("-fx-bar-fill: springgreen");
        } else if (money[i] > 15) {
            node.setStyle("-fx-bar-fill: gold");
        } else {
            node.setStyle("-fx-bar-fill: orangered");
        }
    }
}
