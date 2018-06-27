import HotDrinkVendingMachine.HotDrinkVendMachine;
import HotDrinkVendingMachine.TextualInterface;

/**
 * Classe tester dell'interfaccia testuale del distributore.
 */

public class TestTextualInterface {
    public static void main(String[] args) {
        HotDrinkVendMachine vendMachine = new HotDrinkVendMachine();
        TextualInterface textualInterface = new TextualInterface(vendMachine);
        textualInterface.textualInput();
    }
}
