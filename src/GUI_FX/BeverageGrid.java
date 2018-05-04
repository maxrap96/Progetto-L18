package GUI_FX;

import Distributore.Distributore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.awt.*;

import static java.awt.Color.black;

public class BeverageGrid extends GridPane {

    private Distributore distributore;
    private final int BUTTON_PADDING = 50;
    private final int BUTTONS_PER_LINE = 3;
    private final int NUM_BUTTON_LINES = 4;

    public BeverageGrid(Distributore distributore) {
        this.distributore = distributore;
        this.setAlignment(Pos.BASELINE_LEFT);
        createGrid();
    }

    private void createGrid(){
        // Per una migliore lettura, usare al massimo 12 pulsanti
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPadding(new Insets(BUTTON_PADDING));
        this.setHgap(BUTTON_PADDING);
        this.setVgap(BUTTON_PADDING);

        int number = 0;
        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                if( number + 1 < distributore.getListSize()){
                    number = BUTTONS_PER_LINE * r + c;
                    int idNumber = number + 1;  // Le bevande iniziano dall'id 1
                    Button button = new Button(distributore.getLabel(idNumber));
                    button.setStyle(
                            "-fx-background-radius: 1em;" +
                            //"-fx-border-radius: 1em;" +
                            //"-fx-border-color: black;" +
                            //"-fx-border-width: 2 2 2 2;"
                            "-fx-focus-color: transparent;" +
                            "-fx-faint-focus-color: transparent;"
                    );
                    button.setFont(Font.font("Times", FontPosture.ITALIC, 20));
                    button.setMinSize(screenSize.width / 6, screenSize.height / 7);
                    button.setPrefSize(screenSize.width / 6, screenSize.height / 7);
                    button.setMaxSize(screenSize.width / 6, screenSize.height / 7);
                    this.add(button, c, r);
                }
                else {
                    Button button = new Button("");
                    button.setStyle("-fx-background-radius: 1em;");
                    button.setFont(Font.font("Times", FontPosture.ITALIC, 20));
                    button.setMinSize(screenSize.width / 6, screenSize.height / 7);
                    button.setPrefSize(screenSize.width / 6, screenSize.height / 7);
                    button.setMaxSize(screenSize.width / 6, screenSize.height / 7);
                    this.add(button, c, r);
                }
            }
        }
    }
}
