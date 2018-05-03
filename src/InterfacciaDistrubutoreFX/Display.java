package InterfacciaDistrubutoreFX;

import Distributore.Distributore;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display extends Application {
    private Distributore distributore;
    private final String DEFAULTMESSAGE = "SCEGLIERE UNA BEVANDA";
    private Pane pane = new Pane();
    private Text beverage = new Text();
    private Text beverageCost = new Text();
    private Text credit = new Text();
    private Text sugar = new Text();
    private GridPane gridPane;
    private Scene scene;


    public Display() {
        this.distributore = new Distributore();
        createDisplay();
    }

    /**
     * funzione per impostare le righe del gridpane
     */
    private void createDisplay() {
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVisible(true);

        beverage.setText(DEFAULTMESSAGE);
        beverageCost.setText("");
        credit.setText("Credito: " + distributore.getCredit());
        sugar.setText("riga dello zucchero");

        gridPane.add(beverage,0,0);
        gridPane.add(beverageCost,0,1);
        gridPane.add(credit,0,2);
        gridPane.add(sugar,0,3);

    }

    public void setBeverage(Text beverage) {
        this.beverage = beverage;
    }

    public void setBeverageCost(Text beverageCost) {
        this.beverageCost = beverageCost;
    }

    public void setCredit(Text credit) {
        this.credit = credit;
    }

    public void setSugar(Text sugar) {
        this.sugar = sugar;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
