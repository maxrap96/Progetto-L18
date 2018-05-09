package GUI_FX_Server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;

public class Toolbar1 extends ToolBar {
   // Button sx = new Button();
   // Button dx = new Button();
    Button home = new Button();
    Button save = new Button("SAVE");
    MenuItem ing = new MenuItem("Ing");
    MenuItem utilizzo = new MenuItem("Utilizzo");
    MenuItem monete = new MenuItem("Money");
    Button menu = new Button("Menu");


    public Toolbar1(Stage stage) {
        MenuButton stats = new MenuButton("Stas",null,monete,ing,utilizzo);

        ImageView immHome = new ImageView(loadImage("src/ServerImages/home.png"));
     //   ImageView immSx = new ImageView(loadImage("src/ServerImages/FrecciaSx.png"));
     //   ImageView immDx = new ImageView(loadImage("src/ServerImages/FrecciaDx.png"));
        ImageView immStats = new ImageView(loadImage("src/ServerImages/stats.jpg"));
        ImageView immMenu = new ImageView(loadImage("src/ServerImages/menu.png"));


        immStats.setFitHeight(20);
        immStats.setFitWidth(20);
        immMenu.setFitWidth(20);
        immMenu.setFitHeight(20);

        menu.setGraphic(immMenu);
        stats.setGraphic(immStats);

        immHome.setFitHeight(20);
        immHome.setFitWidth(20);
        immStats.setFitHeight(20);
        immStats.setFitWidth(20);
//        immDx.setFitWidth(20);
//        immDx.setFitHeight(20);
//        immSx.setFitWidth(20);
//        immSx.setFitHeight(20);

        home.setGraphic(immHome);

       // sx.setGraphic(immSx);
       // dx.setGraphic(immDx);
        getItems().addAll(home,stats,menu,save);

        Action(stage);
    }

    public void Action(Stage primaryStage){
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomePage hp = new HomePage();
                hp.start(primaryStage);
            }
        });
        monete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StatsPage(primaryStage).OpenTab(0);
            }
        });
        ing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StatsPage(primaryStage).OpenTab(1);
            }
        });
        utilizzo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StatsPage(primaryStage).OpenTab(2);
            }
        });
        menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new MenuTable(primaryStage);
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