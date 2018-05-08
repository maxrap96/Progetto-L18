package GUI_FX_VendingMachine;

import Distributore.Distributore;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.FileInputStream;

public class VendingMachine extends Application {
     private Distributore distributore = new Distributore();
     private ResetDisplay resetDisplay;
     private double screenWidth = Screen.getPrimary().getBounds().getWidth();
     private double screenHeight = Screen.getPrimary().getBounds().getHeight();

    @Override
    public void start(Stage primaryStage) throws Exception {
        double width = screenWidth / 2;
        double height = screenHeight / 2;

        primaryStage.setTitle("Hot Drinks Vending Machine");

        // Creazione del pannello radice a cui attaccare tutti gli altri
        BorderPane root = new BorderPane();

        // Immagine per lo sfondo
        FileInputStream input = new FileInputStream("src/GUI_FX_VendingMachine/I.JPG");
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

        // Creazione pannello del display, delle monete e degli altri pulsanti
        BorderPane purchasePane = new BorderPane();
        purchasePane.setStyle(
                "-fx-background-color: gray;"
        );
        root.setRight(purchasePane);

        // Display del distributore
        Display display = new Display(screenWidth, screenHeight);
        purchasePane.setTop(display);
        resetDisplay = new ResetDisplay(display, distributore);
        resetDisplay.setDots();

        // Creazione chiavetta
        Button key = new Button("Chiavetta");
        key.setFont(Font.font("Century", 20));
        key.setPrefSize(18 * screenHeight / 100, screenHeight / 9);
        key.setStyle(
                "-fx-background-radius: 1em;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;"
        );
        key.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                key.setStyle(
                        "-fx-background-radius: 1em;" +
                        "-fx-focus-color: blue;"
                );
            }
        });
        purchasePane.setCenter(key);

        // Pannello delle bevande
        GridPane beveragePane = new BeverageGrid(distributore, display, resetDisplay, screenWidth, screenHeight);
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
