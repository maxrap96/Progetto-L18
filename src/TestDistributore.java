import Distributore.Distributore;
import Distributore.TextualInterface;

public class TestDistributore {

    public static void main(String[] args) {
        Distributore macchinetta = new Distributore();
        TextualInterface textualInterface = new TextualInterface(macchinetta);
        textualInterface.textualInput();
    }
}