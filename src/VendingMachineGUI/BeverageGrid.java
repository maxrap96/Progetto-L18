package VendingMachineGUI;

import HotDrinkVendingMachine.HotDrinkVendMachine;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Classe che definisce il pannello dove vengono aggiunti i pulsanti delle bevande.
 */

public class BeverageGrid extends GridPane {
    private Dimension screenSize;
    private HotDrinkVendMachine vendMachine;
    private Display display;
    private ResetDisplay resetDisplay;
    private final int BUTTON_PADDING = 50;
    private final int BUTTONS_PER_LINE = 3;
    private final int NUM_LINES = 4;

    public BeverageGrid(HotDrinkVendMachine vendMachine, Display display, ResetDisplay resetDisplay) {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.vendMachine = vendMachine;
        this.display = display;
        this.resetDisplay = resetDisplay;
        createGrid();
    }

    /**
     * Funzione che crea la griglia e i pulsanti delle bevande.
     */
    private void createGrid() {
        // Per una migliore lettura, usare al massimo 12 pulsanti
        this.setPadding(new Insets(BUTTON_PADDING));
        this.setHgap(BUTTON_PADDING);
        this.setVgap(BUTTON_PADDING);

        // Creazione pulsanti bevande
        int number = 0;
        for (int row = 0; row < NUM_LINES; row++) {
            for (int col = 0; col < BUTTONS_PER_LINE; col++) {
                Button button = new Button("");
                setButton(button, 18 * screenSize.width / 100, screenSize.height / 7);
                if (number + 1 < vendMachine.getListSize()) {
                    number = (BUTTONS_PER_LINE * row) + col;
                    // Le bevande iniziano dall'id 1
                    button.setText(vendMachine.getLabel(number + 1));
                    BeverageEventHandler beverageEventHandler = new BeverageEventHandler (vendMachine, display,
                            number + 1, resetDisplay);
                    button.setOnAction(beverageEventHandler);
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        DropShadow shadow = new DropShadow();
                        @Override
                        public void handle(MouseEvent event) {
                            shadow.setColor(Color.WHITE);
                            shadow.setWidth(70);
                            shadow.setHeight(70);
                            button.setEffect(shadow);
                        }
                    });
                    button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            button.setEffect(null);
                        }
                    });
                }
                this.add(button, col, row);
            }
        }
    }

    /**
     * Funzione che configura i pulsanti delle bevande.
     * @param buttonToSet pulsante da configurare.
     * @param width lunghezza del pulsante.
     * @param height altezza del pulsante.
     */
    private void setButton(Button buttonToSet, int width, int height) {
        buttonToSet.setStyle(
                "-fx-background-radius: 1em;" +
                "-fx-background-color: radial-gradient(focus-angle 100deg, focus-distance 35%, radius 45%, reflect, " +
                        "cornsilk 30%, peru 90%);" +
                "-fx-focus-color: blue;"
        );
        buttonToSet.setFont(Font.font("California FB", FontWeight.BOLD, 20));
        buttonToSet.setPrefSize(width, height);
    }
}
