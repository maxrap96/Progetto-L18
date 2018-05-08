package GUI_FX_VendingMachine;

import Distributore.Distributore;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import javax.swing.*;


public class BeverageEventHandler implements EventHandler{

    private final String BEVERAGEPRICE;
    private final String ID;
    private Distributore distributore;
    private Display display;
    private ResetDisplay resetDisplay;

    public BeverageEventHandler (Distributore distributore, Display display, int index, ResetDisplay resetDisplay) {
        this.distributore = distributore;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.ID = distributore.getID(index);
        this.BEVERAGEPRICE = String.format("%.2f", distributore.getPrice(ID));

    }

    @Override
    public void handle(Event event) {

        display.setBeverage(distributore.selectBeverage(ID));

        if (distributore.getCredit() >= distributore.getPrice(ID)) {
            display.setBeverageCost("");
        }
        else {
            display.setBeverageCost("Costo: " + BEVERAGEPRICE);
        }

        display.setCreditRow(String.format("%.2f", distributore.getCredit()));
        resetDisplay.runTimer();
    }

}
