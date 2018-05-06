package GUI_FX;

import Distributore.Distributore;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.awt.*;

public class BeverageGrid extends GridPane {
    private Distributore distributoreBG;
    private final int BUTTON_PADDING = 50;
    private final int BUTTONS_PER_LINE = 3;
    private final int NUM_LINES = 4;

    public BeverageGrid(Distributore distributore) {
        this.distributoreBG = distributore;
        createGrid();
    }

    private void createGrid(){
        // Per una migliore lettura, usare al massimo 12 pulsanti
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPadding(new Insets(BUTTON_PADDING));
        this.setHgap(BUTTON_PADDING);
        this.setVgap(BUTTON_PADDING);

        int number = 0;
        for (int row = 0; row < NUM_LINES; row++) {
            for (int col = 0; col < BUTTONS_PER_LINE; col++) {
                Button button = new Button("");
                setButton(button, 18 * screenSize.width / 100, screenSize.height / 7);
                if( number + 1 < distributoreBG.getListSize()){
                    number = (BUTTONS_PER_LINE * row) + col;
                    // Le bevande iniziano dall'id 1
                    button.setText(distributoreBG.getLabel(number + 1));
                }
                this.add(button, col, row);
            }
        }
    }

    private void setButton(Button buttonToSet, int width, int height){
        buttonToSet.setStyle(
                        "-fx-background-radius: 1em;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;"
        );
        buttonToSet.setFont(Font.font("Times", FontPosture.ITALIC, 20));
        buttonToSet.setPrefSize(width, height);
    }
}
