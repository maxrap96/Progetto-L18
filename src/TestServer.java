import GUI_FX_Server.ServerConnection;

public class TestServer {

    public static void main(String[] args) {
        // Avvio Server e client di Prova
        new ServerConnection(80).run();
    }
}
