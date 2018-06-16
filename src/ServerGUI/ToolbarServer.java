package ServerGUI;

import ServerSide.ServerConnection;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import static ServerSide.StringCommandList.*;

public class ToolbarServer extends ToolBar {
    private Button home = new Button();
    private Button refreshBtn = new Button();
    private Button save = new Button("Save");
    private MenuItem bvgPurchase = new MenuItem("Acquisto Bevande");
    private MenuItem usage = new MenuItem("Utilizzo");
    private MenuItem coins = new MenuItem("Monete");
    private MenuItem beverage = new MenuItem("Bevande");
    private MenuItem items = new MenuItem("Varie");
    private Button menuToolbar = new Button("Menu");
    private MenuButton stats = new MenuButton("Stats",null, coins, bvgPurchase, usage, beverage, items);
    private MenuButton vendMachine = new MenuButton("Distributore",null);
    private ServerConnection serverConnection;
    private MenuItem[] dispenser;

    ToolbarServer(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;

        //Associazione di immagini ai bottoni
        ImageView imgHome = setImage("home.png");
        ImageView imgStats = setImage("stats.jpg");
        ImageView imgMenu = setImage("menu.png");
        ImageView imgRefresh = setImage("RefreshBtn.jpg");

        menuToolbar.setGraphic(imgMenu);
        stats.setGraphic(imgStats);
        home.setGraphic(imgHome);
        refreshBtn.setGraphic(imgRefresh);

        vendMachine.setPrefHeight(28);
        save.setPrefHeight(28);

        getItems().addAll(home, vendMachine, stats, menuToolbar, refreshBtn, save);
    }

    /**
     * Funzione che inizializza le azioni che eseguiranno i bottoni.
    **/
    void Action(AnchorPane anchor, MenuPage menuPage, StatsPage statsPage) {
        home.setOnAction(event -> {
            //Apertura della schermata di home
            menuPage.getvBox().setVisible(false);
            anchor.setVisible(true);
            statsPage.getMainPanel().setVisible(false);
        });

        //Apertura delle tab delle statisctiche ognuna caratterizzata dall'indice i
        coins.setOnAction(event -> {
            menuPage.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(0);
            sendFile();
        });

        bvgPurchase.setOnAction(event -> {
            menuPage.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(1);
            sendFile();
        });

        usage.setOnAction(event -> {
            menuPage.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(2);
            sendFile();
        });

        beverage.setOnAction(event -> {
            menuPage.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(3);
            sendFile();
        });

        items.setOnAction(event -> {
            menuPage.getvBox().setVisible(false);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(true);
            statsPage.openTab(4);
            sendFile();
        });

        //Apertura della tabella del menu
        menuToolbar.setOnAction(event -> {
            menuPage.getvBox().setVisible(true);
            anchor.setVisible(false);
            statsPage.getMainPanel().setVisible(false);
            serverConnection.chooseCommandExecutedByThread(SEND_MENU);
        });

        save.setOnAction(event -> {
            menuPage.sendMenu();});

        refreshBtn.setOnAction(event -> {
            sendFile();
        });

        vendMachine.setOnMouseClicked(event -> createVendMachine());
    }

    /**
     * Crea distributore da inserire nella tendinda del menù.
     */
    private void createVendMachine() {
        dispenser = new MenuItem[serverConnection.getIndex()];
        vendMachine.getItems().clear();
        for (int i = 0; i < serverConnection.getIndex(); i++) {
            String name = "Distributore" + (i + 1);
            dispenser[i] = new MenuItem(name);
            vendMachine.getItems().addAll(dispenser[i]);

            int index = i;

            dispenser[i].setOnAction(event -> {
            vendMachine.setText(dispenser[index].getText());
            serverConnection.setSelectedClient(index);
            System.out.println("check");
            });
        }

        if (vendMachine.isShowing()) {
            vendMachine.hide();
            vendMachine.show();
        }
    }

    /**
     * Funzione per caricare un'immagine di dimensioni prefissate.
     * @param url percorso dell'immagine.
     */
    private ImageView setImage(String url) {
        ImageView imageViewTmp = new ImageView(loadImage(url));
        imageViewTmp.setFitHeight(20);
        imageViewTmp.setFitWidth(20);
        return imageViewTmp;
    }

    /**
     * Funzione per caricare un'immagine.
     * @param url percorso dell'immagine.
     */
    private Image loadImage(String url) {
        Image imgTmp = new Image(url);
        return imgTmp;
    }

    /**
     * Funzione per inviare tutti i file dal client.
     */
    private void sendFile() {
        serverConnection.chooseCommandExecutedByThread(SEND_STATS);
        serverConnection.chooseCommandExecutedByThread(SEND_COINS);
        serverConnection.chooseCommandExecutedByThread(SEND_DATA);
        serverConnection.chooseCommandExecutedByThread(SEND_MENU);
    }
}