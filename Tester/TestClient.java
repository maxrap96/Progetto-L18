import ClientSide.BooleanRefill;
import ClientSide.ClientVendMach;

public class TestClient {
    public static void main(String[] args) {
        BooleanRefill booleanRefill =  new BooleanRefill();
        new ClientVendMach("localhost", 80, booleanRefill).run();
    }
}
