package GUI_FX_VendingMachine;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Display extends GridPane {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final String DEFAULT_MESSAGE = "Scegliere una bevanda";
    private Text beverage = new Text();
    private Text beverageCost = new Text();
    private Text creditRow = new Text();
    private Text sugar = new Text();

    public Display() {
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
     * @param text: uno dei text del display.
     * @param message: messaggio da stampare a video.
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
