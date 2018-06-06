package ServerGUI;

import ServerSide.ClassOfObservableLists;
import ServerSide.ServerConnection;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StatsPage extends GridPane {
    private GridPane mainPanel = new GridPane();
    private TabPane tabPane = new TabPane();
    private Tab tab1 = new Tab();
    private Tab tab2 = new Tab();
    private Tab tab3 = new Tab();
    private Tab tab4 = new Tab();
    private Tab tab5 = new Tab();
    private ObservableList<String> obsvStats;
    private ObservableList<String> obsvData;
    private ObservableList<String> obsvCoins;
    private ObservableList<String> obsvMenu;
    private ServerConnection serverConnection;

    public StatsPage(Stage stage, ClassOfObservableLists classOfObservableLists, ServerConnection server) {
        this.obsvStats = classOfObservableLists.getObservStats();
        this.obsvCoins = classOfObservableLists.getObservCoins();
        this.obsvData = classOfObservableLists.getObservData();
        this.obsvMenu = classOfObservableLists.getObservMenu();
        this.serverConnection = server;


        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: khaki");

        tab1.setText("Monete");
        tab2.setText("Acquisto bevande");
        tab3.setText("Utilizzo");
        tab4.setText("Bevande");
        tab5.setText("Varie");

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        CoinsChart coinsChart = new CoinsChart(new CategoryAxis(), new NumberAxis(), obsvCoins);
        coinsChart.setServerConnection(serverConnection);

        DrinkPieChart pie = new DrinkPieChart(obsvStats);
        UsageChart usage = new UsageChart(new NumberAxis(), new NumberAxis(), obsvStats);
        DrinkChart drinks = new DrinkChart(new CategoryAxis(), new NumberAxis(), obsvData, obsvMenu);
        drinks.setServerConnection(serverConnection);

        ItemsTab itemsStats = new ItemsTab(obsvData);
        itemsStats.setServerConnection(serverConnection);

        tab1.setContent(coinsChart.setBars());
        tab2.setContent(pie.setChart());
        tab3.setContent(usage.setGraph());
        tab4.setContent(drinks.initChart());
        tab5.setContent(itemsStats.setProgressBar());

        obsvStats.addListener((ListChangeListener) change -> Platform.runLater(() -> {
            // Aggiorna UI
            System.out.println("Detected a change! Stats");
            tab2.setContent(pie.setChart());
            tab3.setContent(usage.setGraph());
        }));

        obsvCoins.addListener((ListChangeListener) change -> Platform.runLater(() -> {
            // Aggiorna UI
            System.out.println("Detected a change! Coins");
            tab1.setContent(coinsChart.setBars());
        }));

        obsvData.addListener((ListChangeListener) change -> Platform.runLater(() -> {
            // Aggiorna UI
            System.out.println("Detected a change! Data");
            tab4.setContent(drinks.initChart());
            tab5.setContent(itemsStats.setProgressBar());
        }));

        obsvMenu.addListener((ListChangeListener) change -> Platform.runLater(() -> {
            // Aggiorna UI
            System.out.println("Detected a change! Menu");
            tab4.setContent(drinks.initChart());
        }));

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5);

        // Utilizzo di tutto lo spazio disponibile da parte del pannello

        borderPane.prefHeightProperty().bind(stage.heightProperty());
        borderPane.prefWidthProperty().bind(stage.widthProperty());
        borderPane.setCenter(tabPane);

        mainPanel.addRow(0, borderPane);
        mainPanel.prefHeightProperty().bind(stage.heightProperty());
        mainPanel.prefWidthProperty().bind(stage.widthProperty());
    }

    /**
     * Funzione per aprire uno specifico tab all'apertura della pagina.
     * @param i indice per selezionare un tab; valore iniziale '0'.
     */
    public void openTab(int i) {
        tabPane.getSelectionModel().select(i);
    }

    public GridPane getMainPanel() {
        return mainPanel;
    }
}
