package GUI_FX_Server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.IOException;

public class Toolbar1 extends ToolBar {
    Button sx = new Button();
    Button dx = new Button();
    Button home = new Button();

    Menu stats = new Menu("Stats");
    MenuItem dati = new MenuItem("Dati");
    MenuItem statistiche = new MenuItem("General Stats");
    MenuItem monete = new MenuItem("Money");

    public Toolbar1(Stage stage) {
        ImageView immStats = new ImageView(loadImage("src/ServerImages/stats.jpg"));
        ImageView immHome = new ImageView(loadImage("src/ServerImages/home.png"));
        ImageView immSx = new ImageView(loadImage("src/ServerImages/FrecciaSx.png"));
        ImageView immDx = new ImageView(loadImage("src/ServerImages/FrecciaDx.png"));

        immHome.setFitHeight(20);
        immHome.setFitWidth(20);
        immStats.setFitHeight(20);
        immStats.setFitWidth(20);
        immDx.setFitWidth(20);
        immDx.setFitHeight(20);
        immSx.setFitWidth(20);
        immSx.setFitHeight(20);

        stats.setGraphic(immStats);
        home.setGraphic(immHome);
        sx.setGraphic(immSx);
        dx.setGraphic(immDx);

        getItems().addAll(sx,dx,home);




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
                coinsChart.start(primaryStage);
            }
        });

        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomePage hp = new HomePage();
                hp.start(primaryStage);
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