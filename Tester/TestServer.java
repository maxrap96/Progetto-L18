import ServerSide.ClassOfObservableLists;
import ServerSide.ServerConnection;

public class TestServer {

    public static void main(String[] args) {
        ClassOfObservableLists classOfObservableLists = new ClassOfObservableLists();
        // Avviamento server e client di prova
        new ServerConnection(80, classOfObservableLists).run();
    }
}
