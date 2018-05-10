package GUI_FX_VendingMachine;

import Distributore.Distributore;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;

public class VendingMachine extends Application {
     private Distributore distributore = new Distributore();
     private ResetDisplay resetDisplay;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() / 2;
        double height = screenSize.getHeight() / 2;

        primaryStage.setTitle("Hot Drinks Vending Machine");

        // Creazione del pannello radice a cui attaccare tutti gli altri
        BorderPane root = new BorderPane();

        // Immagine per lo sfondo
        FileInputStream input = new FileInputStream("src/GUI_FX_VendingMachine/B.jpg");
        Image image = new Image(input);
        input.close();

        // Creazione delle dimensioni per lo sfondo
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                false, false, false, true);
        // Creazione dell'immagine di sfondo
        BackgroundImage changeNameWhenFinalImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        // Impostazione dello sfondo
        root.setStyle(
                "-fx-background-color: black;"
        );

        // Creazione pannello per il display, le monete e altri pulsanti
        BorderPane purchasePane = new BorderPane();
        purchasePane.setPadding(new Insets(11, 11, 11, 11));
        purchasePane.setStyle(
                "-fx-background-color: SaddleBrown;"
        );
        root.setRight(purchasePane);

        // Display del distributore
        Display display = new Display();
        purchasePane.setTop(display);
        resetDisplay = new ResetDisplay(display, distributore);
        resetDisplay.setDots();

        // Creazione chiavetta
        Button key = new Button("Chiavetta");
        key.setFont(Font.font("California FB", 20));
        key.setPrefSize(16 * screenSize.width / 100, screenSize.height / 9);
        key.setStyle(
                "-fx-background-radius: 1em;" +
                "-fx-base: lightGray;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;"
        );
        key.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                key.setStyle(
                        "-fx-background-radius: 1em;" +
                        "-fx-focus-color: darkRed;"
                );
            }
        });
        purchasePane.setCenter(key);

        // Pannello delle bevande
        GridPane beveragePane = new BeverageGrid(distributore, display, resetDisplay);
        beveragePane.setBackground(new Background(changeNameWhenFinalImage));
        root.setLeft(beveragePane);

        // Pannello delle monete
        GridPane moneyPane = new MoneyGrid(distributore, display, resetDisplay);
        purchasePane.setBottom(moneyPane);

        Scene scene = new Scene(root, width, height, Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Termina l'applicazione cliccando la x rossa
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
