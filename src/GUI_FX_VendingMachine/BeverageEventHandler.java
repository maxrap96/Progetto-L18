package GUI_FX_VendingMachine;

import HotDrinkVendingMachine.Distributore;
import javafx.event.Event;
import javafx.event.EventHandler;

public class BeverageEventHandler implements EventHandler {
    private final String BEVERAGE_PRICE;
    private final String ID;
    private Distributore distributore;
    private Display display;
    private ResetDisplay resetDisplay;

    public BeverageEventHandler(Distributore distributore, Display display, int index, ResetDisplay resetDisplay) {
        this.distributore = distributore;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.ID = distributore.getID(index);
        this.BEVERAGE_PRICE = String.format("%.2f", distributore.getPrice(ID));
    }

    @Override
    public void handle(Event event) {
        if (distributore.getCredit() >= distributore.getPrice(ID)) {
            display.setBeverageCost("");
        }
        else {
            display.setBeverageCost("Costo: " + BEVERAGE_PRICE);
        }

        display.setBeverage(distributore.selectBeverage(ID));
        display.setCreditRow(String.format("%.2f", distributore.getCredit()));
        resetDisplay.runTimer();
    }
}
