import ClientSide.BooleanRefill;
import ClientSide.ClientVendMach;

/**
 * Classe che testa il funzionamento del client.
 */

public class TestClient {
    public static void main(String[] args) {
        BooleanRefill booleanRefill =  new BooleanRefill();
        new ClientVendMach("localhost", 80, booleanRefill).run();
    }
}
