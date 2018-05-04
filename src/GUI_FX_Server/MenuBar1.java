package GUI_FX_Server;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

public class MenuBar1 extends MenuBar {

    public MenuBar1() {
        ImageView immStats = new ImageView(loadImage("src/ServerImages/stats.jpg"));
        ImageView immMenu = new ImageView(loadImage("src/ServerImages/menu.png"));
        ImageView immHome = new ImageView(loadImage("src/ServerImages/home.png"));
        ImageView immSx = new ImageView(loadImage("src/ServerImages/FrecciaSx.png"));
        ImageView immDx = new ImageView(loadImage("src/ServerImages/FrecciaDx.png"));

        immHome.setFitHeight(20);
        immHome.setFitWidth(20);
        immStats.setFitHeight(20);
        immStats.setFitWidth(20);
        immMenu.setFitWidth(20);
        immMenu.setFitHeight(20);
        immDx.setFitWidth(20);
        immDx.setFitHeight(20);
        immSx.setFitWidth(20);
        immSx.setFitHeight(20);

        Menu sx = new Menu();
        Menu dx = new Menu();
        Menu home = new Menu();
        Menu menu = new Menu("Menu");
        Menu stats = new Menu("Stats");
        stats.setGraphic(immStats);
        menu.setGraphic(immMenu);
        home.setGraphic(immHome);
        sx.setGraphic(immSx);
        dx.setGraphic(immDx);

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