package Client_Server;

public class Tester {
    public static void main(String[] args) {
        ServerSide serverSide = new ServerSide();
        ClientSide clientSide = new ClientSide();

        serverSide.start();
        clientSide.start();
    }
}
