package GUI_FX_VendingMachine;

import HotDrinkVendingMachine.Distributore;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MoneyGrid extends GridPane {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Distributore distributore;
    private Display display;
    private ResetDisplay resetDisplay;
    private final int BUTTON_PADDING = 32;
    private final int BUTTONS_PER_LINE = 3;
    private final int NUM_BUTTON_LINES = 3;

    public MoneyGrid(Distributore distributore, Display display, ResetDisplay resetDisplay) {
        this.distributore = distributore;
        this.display = display;
        this.resetDisplay = resetDisplay;
        createGrid();
        this.setAlignment(Pos.CENTER_RIGHT);
    }

    private void createGrid() {
        this.setPadding(new Insets(BUTTON_PADDING));
        this.setHgap(BUTTON_PADDING);
        this.setVgap(BUTTON_PADDING);

        // Creazione pulsante resto "C"
        Button change = new Button();
        change.setText("C");
        setPurchaseButton(change);
        change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                distributore.giveChange();
                display.setCreditRow(String.format("%.2f", distributore.getCredit()));
            }
        });
        this.add(change, 0, 0);

        // Creazione pulsante "-"
        Button minus = new Button();
        minus.setText("-");
        setPurchaseButton(minus);
        minus.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  distributore.lessSugar();
                  resetDisplay.setDots();
                  resetDisplay.runTimer();
              }
        });
        this.add(minus, 1, 0);

        // Creazione pulsante "+"
        Button plus = new Button();
        plus.setText("+");
        setPurchaseButton(plus);
        plus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                distributore.moreSugar();
                resetDisplay.setDots();
                resetDisplay.runTimer();
            }
        });
        this.add(plus, 2, 0);

        // Creazione pulsanti monete
        int number;
        for (int r = 1; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                number = BUTTONS_PER_LINE * (r - 1) + c;
                String cValue = String.format("%.2f", distributore.getCoinsValue()[number]);
                Button button = new Button(cValue);
                setPurchaseButton(button);
                button.setOnAction(new MoneyEventHandler(distributore.getCoinsValue()[number], display, distributore));
                this.add(button, c, r);
            }
        }
    }

    /**
     * Funzione che configura i pulsanti delle monete, zucchero e resto.
     * @param button: il pulsante da configurare.
     */
    private void setPurchaseButton(Button button) {
        button.setShape(new Circle (screenSize.height / 8));
        button.setFont(Font.font("California FB", 20));
        button.setPrefSize(screenSize.height / 8, screenSize.height / 8);
        button.setStyle(
                "-fx-base: lightGray;" +
                "-fx-focus-color: blue;"
        );
    }
}
