import HotDrinkVendingMachine.HotDrinkVendMachine;
import HotDrinkVendingMachine.TextualInterface;

public class TestDistributore {
    public static void main(String[] args) {
        HotDrinkVendMachine macchinetta = new HotDrinkVendMachine();
        TextualInterface textualInterface = new TextualInterface(macchinetta);
        textualInterface.textualInput();
    }
}
