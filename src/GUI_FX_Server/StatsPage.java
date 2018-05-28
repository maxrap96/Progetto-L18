package GUI_FX_Server;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StatsPage extends GridPane {
    TabPane tabPane = new TabPane();
    Tab tab1 = new Tab();
    Tab tab2 = new Tab();
    Tab tab3 = new Tab();
    Tab tab4 = new Tab();
    GridPane mainPanel = new GridPane();
    ObservableList<String> obsvStats;
    ObservableList<String> obsvData;
    ObservableList<String> obsvCoins;

    public StatsPage(Stage stage, ObservableList<String> obsvStats, ObservableList<String> obsvData, ObservableList<String> obsvCoins) {
        Group root = new Group();
        this.obsvStats = obsvStats;
        this.obsvCoins = obsvCoins;
        this.obsvData = obsvData;

        Scene scene;

        if (stage.isMaximized()) {
            scene = new Scene(root, Color.KHAKI);
        } else {
            scene = new Scene(root, 800, 550, Color.KHAKI);
        }

        BorderPane borderPane = new BorderPane();

        tab1.setText("Monete");
        tab2.setText("Acquisto bevande");
        tab3.setText("Utilizzo");
        tab4.setText("Items");  // Tradurre in italico
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);

        //TODO MJ: aggiungere elementi grafici ai tab
        HistogramChart coinsChart = new HistogramChart(new CategoryAxis(), new NumberAxis());
        DrinkPieChart pie = new DrinkPieChart(obsvStats);
        UsageChart usage = new UsageChart(new NumberAxis(), new NumberAxis());
        ItemsHistogram itemsChart = new ItemsHistogram(new CategoryAxis(), new NumberAxis());


        tab1.setContent(coinsChart.setBars());
        tab2.setContent(pie.setChart());
        tab3.setContent(usage.setGraph());
        tab4.setContent(itemsChart.setBars());

        obsvStats.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // Update UI here.
                        System.out.println("Detected a change! ");
                        tab2.setContent(pie.setChart());
                        tab3.setContent(usage.setGraph());
                    }
                });}
        });
        obsvCoins.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // Update UI here.
                        System.out.println("Detected a change! ");
                        tab1.setContent(coinsChart.setBars());
                    }
                });
            }
        });
        obsvData.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // Update UI here.
                        System.out.println("Detected a change! ");
                        tab4.setContent(itemsChart.setBars());
                    }
                });
            }
        });

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        // Utilizzo di tutto lo spazio disponibile da parte del pannello

        borderPane.prefHeightProperty().bind(stage.heightProperty());
        borderPane.prefWidthProperty().bind(stage.widthProperty());
        borderPane.setCenter(tabPane);

        mainPanel.addRow(0, borderPane);
       mainPanel.prefHeightProperty().bind(stage.heightProperty());
        mainPanel.prefWidthProperty().bind(stage.widthProperty());
//        root.getChildren().addAll(mainPanel);
//        stage.setScene(scene);
//        stage.show();

    }

    /**
     * Funzione per aprire uno specifico tab all'apertura della pagina
     * @param i indice per selezionare un tab; valore iniziale '0'
     */
    public void OpenTab(int i){
        tabPane.getSelectionModel().select(i);
    }

    public GridPane getMainPanel() {
        return mainPanel;
    }

}