package VendingMachineGUI;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Classe che definisce il display del distributore.
 */

public class Display extends GridPane {
    private Dimension screenSize;
    private final String DEFAULT_MESSAGE = "Scegliere una bevanda";
    private Text beverage;
    private Text beverageCost;
    private Text creditRow;
    private Text sugar;

    public Display() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.beverage = new Text();
        this.beverageCost = new Text();
        this.creditRow = new Text();
        this.sugar = new Text();
        createDisplay();
    }

    /**
     * Funzione per impostare le righe del gridpane.
     */
    private void createDisplay() {
        this.setMinSize(30 * screenSize.width / 100, 25 * screenSize.height / 100);
        this.setPrefSize(30 * screenSize.width / 100, 25 * screenSize.height / 100);
        this.setMaxSize(30 * screenSize.width / 100, 25 * screenSize.height / 100);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setHgap(20);
        this.setVgap(20);
        this.setStyle(
                "-fx-background-color: black;" +
                "-fx-background-radius: 30;" +
                "-fx-border-radius: 30;" +
                "-fx-border-color: white;" +
                "-fx-border-width: 2;"
        );
        this.add(beverage, 0, 0);
        this.add(beverageCost, 0, 1);
        this.add(creditRow, 0, 2);
        this.add(sugar, 0, 3);

        setDisplay(beverage, DEFAULT_MESSAGE);
        setDisplay(beverageCost, "");
        setDisplay(creditRow, "Credito: ");
        setDisplay(sugar, "riga dello zucchero");
    }

    /**
     * Funzione che configura i vari Text del display.
     * @param text uno dei Text del display.
     * @param message messaggio da stampare a video.
     */
    private void setDisplay(Text text, String message) {
        text.setText(message);
        text.setFont(Font.font("Calibri", 25));
        text.setFill(Color.WHITE);
    }

    protected void setBeverage(String info) {
        beverage.setText(info);
    }

    protected void setBeverageCost(String cost) {
        beverageCost.setText(cost);
    }

    protected void setCreditRow(String credit) {
        creditRow.setText("Credito: " + credit);
    }

    protected void setSugar(String quantity) {
        sugar.setText(quantity);
    }
}
