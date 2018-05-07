package GUI_FX_Server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MenuBar1 extends MenuBar{
    Menu stats = new Menu("Stats");
    MenuItem dati = new MenuItem("Dati");
    MenuItem statistiche = new MenuItem("General Stats");
    MenuItem monete = new MenuItem("Money");
    Menu menu = new Menu("Menu");

    public MenuBar1(Stage stage) {
        ImageView immStats = new ImageView(loadImage("src/ServerImages/stats.jpg"));
        ImageView immMenu = new ImageView(loadImage("src/ServerImages/menu.png"));


        immStats.setFitHeight(20);
        immStats.setFitWidth(20);
        immMenu.setFitWidth(20);
        immMenu.setFitHeight(20);

        menu.setGraphic(immMenu);
        stats.setGraphic(immStats);
        getMenus().addAll(menu, stats);

        stats.getItems().add(dati);
        stats.getItems().add(statistiche);
        stats.getItems().add(monete);

        Action(stage);

    }

    public void Action(Stage primaryStage){
        monete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HistogramChart coinsChart = new HistogramChart(new CategoryAxis(), new NumberAxis());
                //coinsChart.start(primaryStage);
            }
        });
    }

    private Image loadImage(String url){
        Image imgTmp = null;
        try {
            FileInputStream input = new FileInputStream(url);
            imgTmp = new Image(input);
            input.close();
            return imgTmp;
        } catch (IOException e){
            e.getMessage();
        }
        return imgTmp;
    }
}
