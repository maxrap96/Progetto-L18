import ServerSide.ServerConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestServer {
    private static ObservableList<String> obsvStats = FXCollections.observableArrayList();
    private static ObservableList<String> obsvMenu = FXCollections.observableArrayList();
    private static ObservableList<String> obsvCoins = FXCollections.observableArrayList();
    private static ObservableList<String> obsvData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        // Avvio server e client di prova
        new ServerConnection(80, obsvStats, obsvMenu, obsvCoins, obsvData).run();
    }
}
