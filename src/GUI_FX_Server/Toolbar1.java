package GUI_FX_Server;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.IOException;

public class Toolbar1 extends ToolBar {
    Button home = new Button();
    MenuItem acqB = new MenuItem("Acquisto Bevande");
    MenuItem utilizzo = new MenuItem("Utilizzo");
    MenuItem monete = new MenuItem("Monete");
    MenuItem itams = new MenuItem("Items");
    Button menu = new Button("Menu");


    public Toolbar1(Stage stage) {
        MenuButton stats = new MenuButton("Stats",null,monete, acqB,utilizzo,itams);

        //Associazione di immagini ai bottoni
        ImageView immHome = new ImageView(loadImage("src/ServerImages/home.png"));
        ImageView immStats = new ImageView(loadImage("src/ServerImages/stats.jpg"));
        ImageView immMenu = new ImageView(loadImage("src/ServerImages/menu.png"));


        immStats.setFitHeight(20);
        immStats.setFitWidth(20);
        immMenu.setFitWidth(20);
        immMenu.setFitHeight(20);
        immHome.setFitHeight(20);
        immHome.setFitWidth(20);

        menu.setGraphic(immMenu);
        stats.setGraphic(immStats);
        home.setGraphic(immHome);

        getItems().addAll(home,stats,menu);

        Action(stage);
    }
    /**Funzione che inizializza le azione che i bottoni eseguiranno
    **/
    public void Action(Stage primaryStage){
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Apertura di una nuova schermata di home
                HomePage hp = new HomePage();
                hp.start(primaryStage);
            }
        });

        //Apertura delle tab delle statisctiche ognuna caratterizzata dall indice i
        monete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StatsPage(primaryStage).OpenTab(0);
            }
        });
        acqB.setOnAction(new EventHandler<ActionEvent>() {
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
        itams.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StatsPage(primaryStage).OpenTab(3);
            }
        });

        //Apertura della tabella del menu
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