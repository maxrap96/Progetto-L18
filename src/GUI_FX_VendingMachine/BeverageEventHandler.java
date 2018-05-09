package GUI_FX_VendingMachine;

import Distributore.Distributore;
import javafx.event.Event;
import javafx.event.EventHandler;

public class BeverageEventHandler implements EventHandler{
    private final String BEVERAGEPRICE;
    private final String ID;
    private Distributore distributore;
    private Display display;
    private ResetDisplay resetDisplay;

    public BeverageEventHandler(Distributore distributore, Display display, int index, ResetDisplay resetDisplay) {
        this.distributore = distributore;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.ID = distributore.getID(index);
        this.BEVERAGEPRICE = String.format("%.2f", distributore.getPrice(ID));
    }

    @Override
    public void handle(Event event) {
        if (distributore.getCredit() >= distributore.getPrice(ID)) {
            display.setBeverageCost("");
        }
        else {
            display.setBeverageCost("Costo: " + BEVERAGEPRICE);
        }

        display.setBeverage(distributore.selectBeverage(ID));
        display.setCreditRow(String.format("%.2f", distributore.getCredit()));
        resetDisplay.runTimer();
    }
}
