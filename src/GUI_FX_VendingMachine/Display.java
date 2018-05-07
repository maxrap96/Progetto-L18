package GUI_FX_VendingMachine;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Display extends Application {
    private final String DEFAULTMESSAGE = "SCEGLIERE UNA BEVANDA";
    private Pane pane = new Pane();
    private Text beverage = new Text();
    private Text beverageCost = new Text();
    private Text creditRow = new Text();
    private Text sugar = new Text();
    private GridPane gridPane;
    private Scene scene;


    public Display() {
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
        beverage.setTextAlignment(TextAlignment.CENTER);
        beverageCost.setText("");
        beverageCost.setTextAlignment(TextAlignment.CENTER);
        creditRow.setText("Credito: ");
        creditRow.setTextAlignment(TextAlignment.CENTER);
        sugar.setText("riga dello zucchero");
        sugar.setTextAlignment(TextAlignment.CENTER);

        gridPane.add(beverage,0,0);
        gridPane.add(beverageCost,0,1);
        gridPane.add(creditRow,0,2);
        gridPane.add(sugar,0,3);

    }

    public void setBeverage(String info) {
        beverage.setText(info);
    }

    public void setBeverageCost(String cost) {
        beverageCost.setText(cost);
    }

    public void setCreditRow(String credit) {
        creditRow.setText(credit);
    }

    public void setSugar(String quantity) {
        sugar.setText(quantity);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public GridPane getDisplay(){
        return gridPane;
    }

    @Override
    public void start(Stage primaryStage) {
        createDisplay();
        gridPane.setAlignment(Pos.CENTER);
        scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
