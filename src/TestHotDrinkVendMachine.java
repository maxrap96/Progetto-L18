import HotDrinkVendingMachine.HotDrinkVendMachine;
import HotDrinkVendingMachine.TextualInterface;

public class TestHotDrinkVendMachine {
    public static void main(String[] args) {
        HotDrinkVendMachine macchinetta = new HotDrinkVendMachine();
        TextualInterface textualInterface = new TextualInterface(macchinetta);
        textualInterface.textualInput();
    }
}
