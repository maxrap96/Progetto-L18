package VendingMachineGUI;

import ClientSide.BooleanRefill;
import HotDrinkVendingMachine.HotDrinkVendMachine;
import ClientSide.ClientVendMach;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Classe principale dell'interfaccia del distributore dove viene creata la finestra della GUI.
 */

public class VendingMachine extends Application {
    private HotDrinkVendMachine vendMachine = new HotDrinkVendMachine();
    private ResetDisplay resetDisplay;
    private BeverageGrid beverageGrid;
    private Display display;
    private UpdateChecker updateChecker;
    private ClientVendMach clientVendMach;
    private BooleanRefill booleanRefill = new BooleanRefill();

    /**
     * Funzione che crea e avvia il frame della GUI.
     * @param primaryStage stage della GUI.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Avvio della connessione client
            this.clientVendMach = new ClientVendMach("localhost", 80, booleanRefill);
            this.clientVendMach.start();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth() / 2;
            double height = screenSize.getHeight() / 2;

            primaryStage.setTitle("Hot Drinks Vending Machine");

            // Creazione del pannello radice a cui attaccare tutti gli altri
            BorderPane root = new BorderPane();

            // Immagine per lo sfondo
            FileInputStream input = new FileInputStream("Images/background.jpg");
            Image image = new Image(input);
            input.close();

            // Creazione delle dimensioni per lo sfondo
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                    false, false, false, true);

            // Creazione dell'immagine di sfondo
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

            // Impostazione dello sfondo
            root.setStyle(
                    "-fx-background-color: black;"
            );

            // Creazione pannello per il display, le monete e altri pulsanti
            this.display = new Display();
            this.resetDisplay = new ResetDisplay(display, vendMachine);
            PurchasePane purchasePane = new PurchasePane(display, resetDisplay, vendMachine);
            root.setRight(purchasePane);

            // Pannello delle bevande
            beverageGrid = new BeverageGrid(vendMachine, display, resetDisplay);
            beverageGrid.setBackground(new Background(backgroundImage));
            root.setLeft(beverageGrid);

            Scene scene = new Scene(root, width, height, Color.LIGHTGRAY);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setResizable(false);
            primaryStage.show();

            // Avvio del thread che si occupa dell'update
            updateChecker = new UpdateChecker(vendMachine, beverageGrid, display, resetDisplay, root, booleanRefill,
                    backgroundImage, purchasePane);
            updateChecker.start();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error caused by " + e);
        }

        // Termina l'applicazione chiudendo la GUI
        primaryStage.setOnCloseRequest(windowEvent -> {
                Platform.exit();
                System.exit(0);
        });
    }
}
