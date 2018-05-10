package GUI_FX_VendingMachine;

import Distributore.Distributore;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.awt.Dimension;
import java.awt.Toolkit;

public class BeverageGrid extends GridPane {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Distributore distributore;
    private Display display;
    private ResetDisplay resetDisplay;
    private final int BUTTON_PADDING = 50;
    private final int BUTTONS_PER_LINE = 3;
    private final int NUM_LINES = 4;

    public BeverageGrid(Distributore distributore, Display display, ResetDisplay resetDisplay) {
        this.distributore = distributore;
        this.display = display;
        this.resetDisplay = resetDisplay;
        createGrid();
    }

    private void createGrid(){
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
                if( number + 1 < distributore.getListSize()){
                    number = (BUTTONS_PER_LINE * row) + col;
                    // Le bevande iniziano dall'id 1
                    button.setText(distributore.getLabel(number + 1));
                    BeverageEventHandler beverageEventHandler = new BeverageEventHandler (distributore, display,
                            number + 1, resetDisplay);
                    button.setOnAction(beverageEventHandler);
//                    button.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//                            button.setStyle(
//                                    "-fx-background-radius: 1em;" +
//                                    "-fx-background-color: linear-gradient(dimGray 0%, gray 20%, lightGray 40%, " +
//                                            "lightGray 60%, gray 80%, dimGray 100%);" +
//                                    "-fx-border-color: blue;" +
//                                    "-fx-border-radius: 1em;" +
//                                    "-fx-border-width: 2;"
//                            );
//                        }
//                    });
                }
                this.add(button, col, row);
            }
        }
    }

    /**
     * Funzione che configura i pulsanti delle bevande
     * @param buttonToSet: pulsante da configurare
     * @param width: lunghezza del pulsante
     * @param height: altezza del pulsante
     */
    private void setButton(Button buttonToSet, int width, int height){
        buttonToSet.setStyle(
                "-fx-background-radius: 1em;" +
                "-fx-background-color: linear-gradient(dimGray 0%, gray 20%, lightGray 40%, lightGray 60%," +
                        "gray 80%, dimGray 100%);"
        );
        buttonToSet.setFont(Font.font("California FB", 20));
        buttonToSet.setPrefSize(width, height);
    }
}
