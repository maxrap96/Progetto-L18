package GUI_FX_Server;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;

public class Toolbar1 extends ToolBar {
    private Button home = new Button();
    private Button save = new Button("Save");
    private MenuItem acqB = new MenuItem("Acquisto Bevande");
    private MenuItem utilizzo = new MenuItem("Utilizzo");
    private MenuItem monete = new MenuItem("Monete");
    private MenuItem bevande = new MenuItem("Bevande");
    private MenuItem items = new MenuItem("Varie");
    private Button menu = new Button("Menu");
    private MenuItem vend1 = new MenuItem("Distributore 1");
    private MenuItem vend2 = new MenuItem("Distributore 2");
    private MenuItem vend3 = new MenuItem("Distributore 3");
    private MenuButton stats = new MenuButton("Stats",null, monete, acqB, utilizzo, bevande, items);
    private MenuButton vendMachines = new MenuButton("Distributore",null, vend1, vend2, vend3);

    public Toolbar1() {

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

        vendMachines.setPrefHeight(28);
        save.setPrefHeight(28);

        getItems().addAll(home, vendMachines, stats, menu, save);
    }

    /**
     * Funzione che inizializza le azione che i bottoni eseguiranno
    **/
    public void Action(AnchorPane anchor,MenuTable menuTable,StatsPage statsPage){
        home.setOnAction(event -> {
            //Apertura di una nuova schermata di home
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(true);
            statsPage.getMainPanel().setVisible(false);
        });

        //Apertura delle tab delle statisctiche ognuna caratterizzata dall indice i
        monete.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.OpenTab(0);
        });
        acqB.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.OpenTab(1);
        });
        utilizzo.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.OpenTab(2);
        });
        bevande.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.OpenTab(3);
        });
        items.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.OpenTab(4);
        });

        //Apertura della tabella del menu
        menu.setOnAction(event -> {
            menuTable.getvBox().setVisible(true);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(false);
        });

        save.setOnAction(event -> {menuTable.sendMenu();});

        vend1.setOnAction(event -> {
            vendMachines.setText(vend1.getText());
        });
        vend2.setOnAction(event -> {
            vendMachines.setText(vend2.getText());
        });
        vend3.setOnAction(event -> {
            vendMachines.setText(vend3.getText());
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
            e.printStackTrace();
        }
        return imgTmp;
    }
}