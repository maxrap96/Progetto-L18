import HotDrinkVendingMachine.HotDrinkVendMachine;
import HotDrinkVendingMachine.TextualInterface;

public class TestTextualInterface {
    public static void main(String[] args) {
        HotDrinkVendMachine vendMachine = new HotDrinkVendMachine();
        TextualInterface textualInterface = new TextualInterface(vendMachine);
        textualInterface.textualInput();
    }
}
