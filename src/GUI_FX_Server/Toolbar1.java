package GUI_FX_Server;

import ServerSide.ServerConnection;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.IOException;

import static ServerSide.StringCommandList.*;

public class Toolbar1 extends ToolBar {
    private Button home = new Button();
    Button refreshBtn = new Button();
    private Button save = new Button("Save");
    private MenuItem acqB = new MenuItem("Acquisto Bevande");
    private MenuItem utilizzo = new MenuItem("Utilizzo");
    private MenuItem monete = new MenuItem("Monete");
    private MenuItem bevande = new MenuItem("Bevande");
    private MenuItem items = new MenuItem("Varie");
    private Button menuTool = new Button("Menu");
    private MenuButton stats = new MenuButton("Stats",null, monete, acqB, utilizzo, bevande, items);
    private MenuButton vendMachines = new MenuButton("Distributore",null);
    private ServerConnection serverConnection;
    MenuItem[] vend;

    public Toolbar1(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        //createdVend();

        //Associazione di immagini ai bottoni
        ImageView immHome = new ImageView(loadImage("src/ServerImages/home.png"));
        ImageView immStats = new ImageView(loadImage("src/ServerImages/stats.jpg"));
        ImageView immMenu = new ImageView(loadImage("src/ServerImages/menu.png"));
        ImageView immRefresh = new ImageView(loadImage("src/ServerImages/RefreshBtn.jpg"));

        immStats.setFitHeight(20);
        immStats.setFitWidth(20);
        immMenu.setFitWidth(20);
        immMenu.setFitHeight(20);
        immHome.setFitHeight(20);
        immHome.setFitWidth(20);
        immRefresh.setFitHeight(20);
        immRefresh.setFitWidth(20);

        menuTool.setGraphic(immMenu);
        stats.setGraphic(immStats);
        home.setGraphic(immHome);
        refreshBtn.setGraphic(immRefresh);

        vendMachines.setPrefHeight(28);
        save.setPrefHeight(28);

        getItems().addAll(home, vendMachines, stats, menuTool, refreshBtn, save);
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
            statsPage.openTab(0);
            sendFile();
        });
        acqB.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(1);
            sendFile();
        });
        utilizzo.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(2);
            sendFile();
        });
        bevande.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(3);
            sendFile();
        });
        items.setOnAction(event -> {
            menuTable.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(4);
            sendFile();
        });

        //Apertura della tabella del menu
        menuTool.setOnAction(event -> {
            menuTable.getvBox().setVisible(true);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(false);
            serverConnection.chooseCommandExecutedByThread(SEND_MENU);
        });

        save.setOnAction(event -> {menuTable.sendMenu();});

        vendMachines.setOnMouseClicked(event -> {
            createdVend();
        });

    }

    private void createdVend(){

            vend = new MenuItem[serverConnection.getIndex()];
            vendMachines.getItems().clear();
            for(int i = 0; i < serverConnection.getIndex(); i++){
                vend[i] = null;
                String name = "Distributore"+(i+1);
                vend[i] = new MenuItem(name);
                vendMachines.getItems().addAll(vend[i]);

                final int index = i;
                vend[i].setOnAction(event -> {
                    vendMachines.setText(vend[index].getText());
                    serverConnection.setSelectedClient(index);
                    System.out.println("check");
                });
            }
        if (vendMachines.isShowing()){
            vendMachines.hide();
            vendMachines.show();
        }
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

    private void sendFile(){
        serverConnection.chooseCommandExecutedByThread(SEND_STATS);
        serverConnection.chooseCommandExecutedByThread(SEND_COINS);
        serverConnection.chooseCommandExecutedByThread(SEND_DATA);
        serverConnection.chooseCommandExecutedByThread(SEND_MENU);
    }
}