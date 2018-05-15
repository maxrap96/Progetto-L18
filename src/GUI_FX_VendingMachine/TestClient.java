package GUI_FX_VendingMachine;

public class TestClient {
    public static void main(String[] args) {

        new ClientVendMach("localhost", 80).run();
    }
}
