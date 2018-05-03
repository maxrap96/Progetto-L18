package InterfacciaDistrubutoreFX;

import Distributore.Distributore;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BeverageGrid extends Application {
    private GridPane gridPane;
    private Distributore distributore;

    /*
    public BeverageGrid(Distributore distributore) {
        this.distributore = distributore;
    }
*/

    private void createGrid(){
        //per una migliroe lettura è più comodo usare al massimo 12 pulsanti
        gridPane.setHgap(20);
        gridPane.setVgap(30);
        gridPane.setVisible(true);

        


    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
