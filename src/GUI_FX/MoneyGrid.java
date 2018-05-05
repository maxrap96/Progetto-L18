package GUI_FX;

import Distributore.Distributore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.awt.*;

public class MoneyGrid extends GridPane {
    private Distributore distributore;
    private final int BUTTON_PADDING = 35;
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

        int number;
        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                number = BUTTONS_PER_LINE * r + c;
                String cValue = String.format("%.2f", distributore.getCoinsValue()[number]);
                Button button = new Button(cValue);
                button.setShape(new Circle(screenSize.height/8));
                button.setStyle(
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;"
                );
                button.setFont(Font.font("Times", FontPosture.ITALIC, 20));
                button.setMinSize(screenSize.height / 8, screenSize.height / 8);
                button.setPrefSize(screenSize.height / 8, screenSize.height / 8);
                button.setMaxSize(screenSize.height / 8, screenSize.height / 8);
                this.add(button, c, r);
            }
        }
    }
}
