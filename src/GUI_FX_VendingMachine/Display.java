package GUI_FX_VendingMachine;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Display extends GridPane {
    private final String DEFAULTMESSAGE = "SCEGLIERE UNA BEVANDA";
    private Text beverage = new Text();
    private Text beverageCost = new Text();
    private Text creditRow = new Text();
    private Text sugar = new Text();

    public Display() {
        createDisplay();
    }

    /**
     * funzione per impostare le righe del gridpane
     */
    private void createDisplay() {

        this.setPadding(new Insets(30,30,30,30));
        this.setStyle(
                "-fx-background-color: blue;" +
                "-fx-background-radius: 30;"
        );
        this.add(beverage,0,0);
        this.add(beverageCost,0,1);
        this.add(creditRow,0,2);
        this.add(sugar,0,3);

        beverage.setText(DEFAULTMESSAGE);
        beverage.setFont(Font.font("Times", 20));
        beverage.setFill(Color.WHITE);
        beverageCost.setText("");
        beverageCost.setFont(Font.font("Times", 20));
        beverageCost.setFill(Color.WHITE);
        creditRow.setText("Credito: ");
        creditRow.setFont(Font.font("Times", 20));
        creditRow.setFill(Color.WHITE);
        sugar.setText("riga dello zucchero");
        sugar.setFont(Font.font("Times", 20));
        sugar.setFill(Color.WHITE);
    }

    public void setBeverage(String info) {
        beverage.setText(info);
    }

    public void setBeverageCost(String cost) {
        beverageCost.setText("Costo: " + cost);
    }

    public void setCreditRow(String credit) {
        creditRow.setText("Credito: " + credit);
    }

    public void setSugar(String quantity) {
        sugar.setText(quantity);
    }
}
