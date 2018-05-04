package GUI_FX;

import Distributore.Distributore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BeverageGrid extends GridPane {

    private Distributore distributore;
    private final int BUTTON_PADDING = 10;
    private final int BUTTONS_PER_LINE = 4;
    private final int NUM_BUTTON_LINES = 3;
    private final double[] MAXSIZE = {2000, 3000};
    private final double[] MINSIZE = {50, 60};


    public BeverageGrid(Distributore distributore) {
        this.distributore = distributore;
        this.setAlignment(Pos.CENTER);
        createGrid();
    }

    private void createGrid(){
        // per una migliore lettura, usare al massimo 12 pulsanti
        this.setHgap(20);
        this.setVgap(30);
        this.setVisible(true);
        this.setPadding(new Insets(BUTTON_PADDING));
        this.setHgap(BUTTON_PADDING);
        this.setVgap(BUTTON_PADDING);

        int number = 0;
        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                if( number + 1 < distributore.getListSize()){
                    number = NUM_BUTTON_LINES * r + c;
                    int idNumber = number + 1;  //le bevande iniziano dall'id 1
                    Button button = new Button(distributore.getLabel(idNumber));
                    button.setMinSize(MINSIZE[0], MINSIZE[1]);
                    button.setMaxSize(MAXSIZE[0], MAXSIZE[1]);
                    this.add(button, c, r);
                }
                else {
                    Button button = new Button("");
                    this.add(button, c, r);
                }
            }
        }
    }
}
