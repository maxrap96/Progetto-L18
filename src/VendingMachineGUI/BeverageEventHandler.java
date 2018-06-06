package VendingMachineGUI;

import HotDrinkVendingMachine.HotDrinkVendMachine;
import javafx.event.Event;
import javafx.event.EventHandler;

public class BeverageEventHandler implements EventHandler {
    private final String BEVERAGE_PRICE;
    private final String ID;
    private HotDrinkVendMachine vendMachine;
    private Display display;
    private ResetDisplay resetDisplay;

    public BeverageEventHandler(HotDrinkVendMachine vendMachine, Display display, int index,
                                ResetDisplay resetDisplay) {
        this.vendMachine = vendMachine;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.ID = vendMachine.getID(index);
        this.BEVERAGE_PRICE = String.format("%.2f", vendMachine.getPrice(ID));
    }

    @Override
    public void handle(Event event) {
        if (vendMachine.getCredit() >= vendMachine.getPrice(ID)) {
            display.setBeverageCost("");
        }
        else {
            display.setBeverageCost("Costo: " + BEVERAGE_PRICE);
        }

        display.setBeverage(vendMachine.selectBeverage(ID));
        display.setCreditRow(String.format("%.2f", vendMachine.getCredit()));
        resetDisplay.runTimer();
    }
}
