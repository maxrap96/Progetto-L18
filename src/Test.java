import Distributore.Distributore;
import Distributore.Client;

public class Test {

    public static void main(String[] args) {
        Distributore macchinetta = new Distributore();
        macchinetta.textualInput();
        System.out.println("\ncheck");
        Client client = new Client();
        client.start();
    }
}