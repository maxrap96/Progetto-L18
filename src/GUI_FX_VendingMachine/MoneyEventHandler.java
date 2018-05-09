package GUI_FX_VendingMachine;

import Distributore.Distributore;
import javafx.event.Event;
import javafx.event.EventHandler;

public class MoneyEventHandler implements EventHandler {
    private final double VALUE;
    private Display display;
    private Distributore distributore;

    public MoneyEventHandler(double value, Display display, Distributore distributore) {
        VALUE = value;
        this.display = display;
        this.distributore = distributore;
    }

    @Override
    public void handle(Event event) {
        distributore.addCredit(VALUE);
        display.setCreditRow(String.format("%.2f", distributore.getCredit()));
    }
}
