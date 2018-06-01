import HotDrinkVendingMachine.Distributore;
import HotDrinkVendingMachine.TextualInterface;

public class TestDistributore {
    public static void main(String[] args) {
        Distributore macchinetta = new Distributore();
        TextualInterface textualInterface = new TextualInterface(macchinetta);
        textualInterface.textualInput();
    }
}
