package VendingMachineGUI;

import HotDrinkVendingMachine.HotDrinkVendMachine;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.awt.*;

public class PurchasePane extends BorderPane {
    private Dimension screenSize;

    public PurchasePane(Display display, ResetDisplay resetDisplay, HotDrinkVendMachine vendMachine) {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPadding(new Insets(11, 11, 11, 11));
        this.setStyle(
                "-fx-background-color: dimGray;"
        );
        this.setVisible(true);

        // Display del distributore
        this.setTop(display);
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
        key.setOnAction(event -> {
            vendMachine.setConnectionKey();
            display.setCreditRow(String.valueOf(vendMachine.getCredit()));
            key.setStyle(
                    "-fx-background-radius: 1em;" +
                            "-fx-focus-color: blue;"
            );
        });
        this.setCenter(key);

        // Pannello delle monete
        GridPane moneyPane = new MoneyGrid(vendMachine, display, resetDisplay);
        this.setBottom(moneyPane);
    }
}
