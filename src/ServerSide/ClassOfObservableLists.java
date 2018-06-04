package ServerSide;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassOfObservableLists {
    private ObservableList<String> observStats = FXCollections.observableArrayList();
    private ObservableList<String> observMenu = FXCollections.observableArrayList();
    private ObservableList<String> observCoins = FXCollections.observableArrayList();
    private ObservableList<String> observData = FXCollections.observableArrayList();

    public ObservableList<String> getObservStats() {
        return observStats;
    }

    public ObservableList<String> getObservMenu() {
        return observMenu;
    }

    public ObservableList<String> getObservCoins() {
        return observCoins;
    }

    public ObservableList<String> getObservData() {
        return observData;
    }
}
