package GUI_FX_Server;

import Distributore.Coins;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import java.util.StringTokenizer;

public class HistogramChart extends BarChart {

    final static String[] Monete = {"0.05", "0.10", "0.20", "0.50", "1", "2"};
    Coins coins = new Coins();

    public HistogramChart(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    public BorderPane setbar(){
        BorderPane b = new BorderPane();

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Coins");
        //XYChart.Data<String, Number> data = new XYChart.Data<>();

        xAxis.setLabel("Numero monete rimaste");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(45);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        yAxis.setLabel("Tagli di monete [€]");
        bc.setLegendVisible(false);

        XYChart.Series series1 = new XYChart.Series();

        // Ottenimento numero di monete
        String numCoins = coins.moneyOnFile();
        int money[] = new int[Monete.length];
        StringTokenizer tokenizer = new StringTokenizer(numCoins, "\t");

        for(int i = 0; i < Monete.length; i++) {
            if (tokenizer.hasMoreTokens()) {
                money[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        // Aggiunta dei dati alla serie
        for (int i = 0; i < Monete.length; i++) {
            series1.getData().add(new XYChart.Data(money[i], Monete[i]));
        }
        bc.getData().add(series1);

        for (int i = 0; i < Monete.length; i++) {
            colorChartBars(bc, i, money);
        }

        b.setCenter(bc);
        return b;
    }

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

    // Vecchio grafico
    /* public void start(Stage stage) {
        Toolbar1 toolbar1 = new Toolbar1(stage);
        MenuBar1 menuBar1 = new MenuBar1(stage);
        VBox vBox = new VBox();

        vBox.getChildren().addAll(toolbar1,menuBar1);
        vBox.setFillWidth(true);

        stage.setTitle("Monete rimanenti:");

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Coins");
        xAxis.setLabel("Numero monete rimaste");
        yAxis.setLabel("Tagli di monete [€]");

        XYChart.Series series1 = new XYChart.Series();

        // Trovo numero di monete
        String numCoins = coins.moneyOnFile();
        int money[] = new int[Monete.length];
        StringTokenizer tokenizer = new StringTokenizer(numCoins, "\t");

        for(int i = 0; i < Monete.length; i++) {
            if (tokenizer.hasMoreTokens()) {
                money[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        // Inizializzazione valori a random, giusto per vedere il grafico
        for (int i = 0; i < Monete.length; i++) {
            series1.getData().add(new XYChart.Data(money[i], Monete[i]));
        }
        GridPane gPane = new GridPane();                //Creazione GridPanel per aggiungere menubar e BarChart
        gPane.setPrefSize(800, 550);
        gPane.setMinSize(800, 550);
        ColumnConstraints Col = new ColumnConstraints();
        Col.setHgrow(Priority.ALWAYS);
        gPane.getColumnConstraints().addAll(Col);
        gPane.addRow(0,vBox);
        gPane.addRow(1,bc);

        Scene scene = new Scene(gPane, 800, 600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }*/
}