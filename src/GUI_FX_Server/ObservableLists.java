package GUI_FX_Server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObservableLists {
    private static ObservableList<String> observStats = FXCollections.observableArrayList();
    private static ObservableList<String> observMenu = FXCollections.observableArrayList();
    private static ObservableList<String> observCoins = FXCollections.observableArrayList();
    private static ObservableList<String> observData = FXCollections.observableArrayList();

    protected static ObservableList<String> getObservStats() {
        return observStats;
    }

    protected static ObservableList<String> getObservMenu() {
        return observMenu;
    }

    protected static ObservableList<String> getObservCoins() {
        return observCoins;
    }

    protected static ObservableList<String> getObservData() {
        return observData;
    }

    protected static void setObservStats(ObservableList<String> observStats) {
        ObservableLists.observStats = observStats;
    }

    protected static void setObservMenu(ObservableList<String> observMenu) {
        ObservableLists.observMenu = observMenu;
    }

    protected static void setObservCoins(ObservableList<String> observCoins) {
        ObservableLists.observCoins = observCoins;
    }

    protected static void setObservData(ObservableList<String> observData) {
        ObservableLists.observData = observData;
    }
}
