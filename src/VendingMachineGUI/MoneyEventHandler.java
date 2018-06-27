package VendingMachineGUI;

import HotDrinkVendingMachine.HotDrinkVendMachine;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Classe che gestisce la pressione dei pulsanti delle monete.
 */

public class MoneyEventHandler implements EventHandler {
    private final double VALUE;
    private Display display;
    private HotDrinkVendMachine vendMachine;

    public MoneyEventHandler(double value, Display display, HotDrinkVendMachine vendMachine) {
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
