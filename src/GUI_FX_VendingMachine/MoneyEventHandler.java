package GUI_FX_VendingMachine;

import HotDrinkVendingMachine.Distributore;
import javafx.event.Event;
import javafx.event.EventHandler;

public class MoneyEventHandler implements EventHandler {
    private final double VALUE;
    private Display display;
    private Distributore vendMachine;

    public MoneyEventHandler(double value, Display display, Distributore vendMachine) {
        VALUE = value;
        this.display = display;
        this.vendMachine = vendMachine;
    }

    @Override
    public void handle(Event event) {
        vendMachine.addCredit(VALUE);
        display.setCreditRow(String.format("%.2f", vendMachine.getCredit()));
    }
}
