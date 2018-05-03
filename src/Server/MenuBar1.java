package Server;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class MenuBar1 extends MenuBar {

    public MenuBar1() {

        //primaryStage.setTitle("JavaFX App");
        ImageView ImmStats = new ImageView("file:src/Server/stats.jpg");
        ImageView ImmMenu = new ImageView("file:src/Server/menu.png");
        ImageView ImmHome = new ImageView("file:src/Server/home.png");
        ImageView ImmSx = new ImageView("file:src/Server/FrecciaSx.png");
        ImageView ImmDx = new ImageView("file:src/Server/FrecciaDx.png");

        ImmHome.setFitHeight(20);
        ImmHome.setFitWidth(20);
        ImmStats.setFitHeight(20);
        ImmStats.setFitWidth(20);
        ImmMenu.setFitWidth(20);
        ImmMenu.setFitHeight(20);
        ImmDx.setFitWidth(20);
        ImmDx.setFitHeight(20);
        ImmSx.setFitWidth(20);
        ImmSx.setFitHeight(20);

        Menu sx = new Menu();
        Menu dx = new Menu();
        Menu home = new Menu();
        Menu menu = new Menu("Menu");
        Menu stats = new Menu("Stats");
        stats.setGraphic(ImmStats);
        menu.setGraphic(ImmMenu);
        home.setGraphic(ImmHome);
        sx.setGraphic(ImmSx);
        dx.setGraphic(ImmDx);

        getMenus().add(sx);
        getMenus().add(dx);
        getMenus().add(home);
        getMenus().add(menu);
        getMenus().add(stats);



        MenuItem dati = new MenuItem("Dati");
        MenuItem statistiche = new MenuItem("General Stats");
        MenuItem monete = new MenuItem("Money");
        stats.getItems().add(dati);
        stats.getItems().add(statistiche);
        stats.getItems().add(monete);

        //VBox vBox = new VBox(menuBar);

        //Scene scene = new Scene(vBox, 960, 600);

        //primaryStage.setScene(scene);
        //primaryStage.show();
    }
}