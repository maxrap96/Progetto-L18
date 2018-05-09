package GUI_FX_Server;

import Server.DrinkPieChart;
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

    public StatsPage(Stage stage) {
        Group root = new Group();

        GridPane mainPanel = new GridPane();

        // Impostazione barra degli strumenti e barra menu
        Toolbar1 toolbar1 = new Toolbar1(stage);
       // MenuBar1 menuBar1 = new MenuBar1(stage);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(toolbar1/*,menuBar1*/);
        vBox.setFillWidth(true);

        stage.setTitle("Statistics");

        Scene scene = new Scene(root, 800, 550, Color.KHAKI);

        BorderPane borderPane = new BorderPane();

        tab1.setText("Monete");
        tab2.setText("Ingredienti");
        tab3.setText("Utilizzo");
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);

        //TODO MJ: aggiungere elementi grafici ai tab
        HistogramChart coinsChart = new HistogramChart(new CategoryAxis(), new NumberAxis());
        DrinkPieChart pie = new DrinkPieChart();

        tab1.setContent(coinsChart.setbar());
        tab2.setContent(pie.setChart());
        tab3.setContent(new BorderPane());

        tabPane.getTabs().addAll(tab1, tab2, tab3);


        // MJ: da tenere per il momento
        /*String[] tabNames = {"Monete", "Ingredienti", "Utilizzo"};

        for (int i = 0; i < tabNames.length; i++) {
            Tab tab = new Tab();
            tab.setText(tabNames[i]);
            tab.setClosable(false);
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label("Tab" + i));
            hbox.setAlignment(Pos.CENTER);
            tab.setContent(hbox);
            tabPane.getTabs().add(tab);
        }*/

        // bind to take available space

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.setCenter(tabPane);

        mainPanel.addRow(0, vBox);
        mainPanel.addRow(1, borderPane);
        mainPanel.prefHeightProperty().bind(scene.heightProperty());
        mainPanel.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().addAll(mainPanel);
        stage.setScene(scene);
        stage.show();
    }

    public void OpenTab(int i){
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(i); //select by index starting with 0
        selectionModel.clearSelection();
    }
}