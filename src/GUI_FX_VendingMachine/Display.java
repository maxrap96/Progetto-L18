package GUI_FX_VendingMachine;


import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
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
        this.setHgap(10);
        //gridPane.setVisible(true);
        //this.setAlignment(Pos.TOP_CENTER);

        this.setStyle("-fx-background-color: blue");
        this.add(beverage,0,0);
        this.add(beverageCost,0,1);
        this.add(creditRow,0,2);
        this.add(sugar,0,3);


        beverage.setText(DEFAULTMESSAGE);
       // beverage.setTextAlignment(TextAlignment.CENTER);
        beverageCost.setText("");
       // beverageCost.setTextAlignment(TextAlignment.CENTER);
        creditRow.setText("");
        //creditRow.setTextAlignment(TextAlignment.CENTER);
        sugar.setText("riga dello zucchero");
        //sugar.setTextAlignment(TextAlignment.CENTER);

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
