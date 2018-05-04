package GUI_Swing;

import Distributore.Distributore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.awt.*;

public class MoneyGrid extends GridPane {
    private Distributore distributore;
    private final int BUTTON_PADDING = 50;
    private final int BUTTONS_PER_LINE = 3;
    private final int NUM_BUTTON_LINES = 2;

    public MoneyGrid(Distributore distributore) {
        this.distributore = distributore;
        this.setAlignment(Pos.CENTER_RIGHT);
        createGrid();
    }

    private void createGrid(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPadding(new Insets(BUTTON_PADDING));
        this.setHgap(BUTTON_PADDING);
        this.setVgap(BUTTON_PADDING);

//        int number = 0;
//        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
//            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
//                if( number + 1 < 6){
//                    number = BUTTONS_PER_LINE * r + c;
//                    int idNumber = number + 1;
//                    String cValue = String.format("%.2f", distributore.getCoinsValue()[number]);
//                    Button button = new Button("");
//
//                    button.setFont(Font.font("Times", FontPosture.ITALIC, 20));
//                    this.add(button, c, r);
//                }
//            }
//        }
        Button b = new Button("");
        this.add(b, 0, 0);
    }
}
