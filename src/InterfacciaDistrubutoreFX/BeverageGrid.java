package InterfacciaDistrubutoreFX;

import Distributore.Distributore;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BeverageGrid extends Application {
    private GridPane gridPane = new GridPane();
    private Distributore distributore;
    private final int BUTTON_PADDING = 10;
    private final int BUTTONS_PER_LINE = 4;
    private final int NUM_BUTTON_LINES = 3;
    private final double[] MAXSIZE = {200, 300};
    private final double[] MINSIZE = {50, 60};

    /*
    public BeverageGrid(Distributore distributore) {
        this.distributore = distributore;
        createGrid();
    }
*/

    private void createGrid(){
        //per una migliroe lettura è più comodo usare al massimo 12 pulsanti
        gridPane.setHgap(20);
        gridPane.setVgap(30);
        gridPane.setVisible(true);
        gridPane.setPadding(new Insets(BUTTON_PADDING));
        gridPane.setHgap(BUTTON_PADDING);
        gridPane.setVgap(BUTTON_PADDING);

        int number = 0;
        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                if( number + 1 < distributore.getListSize()){
                    number = NUM_BUTTON_LINES * r + c;
                    int idNumber = number + 1;  //le bevande iniziano dall'id 1
                    String id = distributore.getID(idNumber);
                    Button button = new Button(distributore.getLabel(idNumber));
                    button.setMinSize(MINSIZE[0], MINSIZE[1]);
                    button.setMaxSize(MAXSIZE[0], MAXSIZE[1]);
                    gridPane.add(button, c, r);
                }
                else {
                    Button button = new Button("");
                    gridPane.add(button, c, r);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        distributore = new Distributore();
        createGrid();
        ScrollPane scrollPane = new ScrollPane(gridPane);
        Scene scene = new Scene(scrollPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
