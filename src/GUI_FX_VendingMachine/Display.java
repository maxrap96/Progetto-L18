package GUI_FX_VendingMachine;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Display extends GridPane {
    private final String DEFAULTMESSAGE = "Scegliere una bevanda";
    private Text beverage = new Text();
    private Text beverageCost = new Text();
    private Text creditRow = new Text();
    private Text sugar = new Text();

    public Display( double screenWidth, double screenHeight) {
        createDisplay(screenWidth, screenHeight);
    }

    /**
     * Funzione per impostare le righe del gridpane
     * @param screenWidth
     * @param screenHeight
     */
    private void createDisplay(double screenWidth, double screenHeight) {
        this.setPrefSize(screenWidth / 100, screenHeight /4);
        this.setPadding(new Insets(20,20,20,20));
        this.setHgap(20);
        this.setVgap(20);
        this.setStyle(
                "-fx-background-color: blue;" +
                "-fx-background-radius: 30;"
        );
        this.add(beverage,0,0);
        this.add(beverageCost,0,1);
        this.add(creditRow,0,2);
        this.add(sugar,0,3);

        setDisplay(beverage, DEFAULTMESSAGE);
        setDisplay(beverageCost, "");
        setDisplay(creditRow, "Credito: ");
        setDisplay(sugar, "riga dello zucchero");
    }

    /**
     * Funzione che configura i vari Text del display
     * @param text: uno dei text del display
     * @param message: messaggio da stampare a video
     */
    private void setDisplay(Text text, String message) {
        text.setText(message);
        text.setFont(Font.font("Calibri", 25));
        text.setFill(Color.WHITE);
    }

    public void setBeverage(String info) {
        beverage.setText(info);
    }

    public void setBeverageCost(String cost) {
        beverageCost.setText(cost);
    }

    public void setCreditRow(String credit) {
        creditRow.setText("Credito: " + credit);
    }

    public void setSugar(String quantity) {
        sugar.setText(quantity);
    }
}