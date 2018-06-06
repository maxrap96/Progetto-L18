import HotDrinkVendingMachine.HotDrinkVendMachine;
import HotDrinkVendingMachine.TextualInterface;

public class TestHotDrinkVendMachine {
    public static void main(String[] args) {
        HotDrinkVendMachine vendMachine = new HotDrinkVendMachine();
        TextualInterface textualInterface = new TextualInterface(vendMachine);
        textualInterface.textualInput();
    }
}
