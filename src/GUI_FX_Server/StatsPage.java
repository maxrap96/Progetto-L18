package GUI_FX_Server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StatsPage extends GridPane {

    public StatsPage(Stage stage) {
        Group root = new Group();

        GridPane mainPanel = new GridPane();

        Toolbar1 toolbar1 = new Toolbar1(stage);
        MenuBar1 menuBar1 = new MenuBar1(stage);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(toolbar1,menuBar1);
        vBox.setFillWidth(true);

        stage.setTitle("Statistics");

        Scene scene = new Scene(root, 800, 550, Color.KHAKI);

        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();

        String[] tabNames = {"Monete", "Ingredienti", "Utilizzo"};

        for (int i = 0; i < tabNames.length; i++) {
            Tab tab = new Tab();
            tab.setText(tabNames[i]);
            tab.setClosable(false);
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label("Tab" + i));
            hbox.setAlignment(Pos.CENTER);
            tab.setContent(hbox);
            tabPane.getTabs().add(tab);
        }

        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.setCenter(tabPane);

        mainPanel.addRow(0, vBox);
        mainPanel.addRow(1, borderPane);

        root.getChildren().addAll(mainPanel);

        stage.setScene(scene);
        stage.show();
    }
}