package ServerSide;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.net.Socket;
import java.util.ArrayList;

public abstract class SendCommandServer implements CommandServer {
    protected ReceiverServer receiverServer;
    protected Socket clientSocket;
    protected ObservableList<String> observableListCommand;
    protected ArrayList<String> arrayListCommand;
    protected BufferedReader clientReader;

    public SendCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                             ObservableList<String> arrayToSaveInfo, BufferedReader clientReader) {
        this.receiverServer = receiverServer;
        this.clientSocket = clientSocket;
        this.observableListCommand = arrayToSaveInfo;
        this.clientReader = clientReader;
        this.arrayListCommand = new ArrayList<>();
    }

    /**
     * Funzione per sovrascrivere l'observableList.
     */
    protected void saveArrayInObservable() {
        observableListCommand.removeAll();
        observableListCommand.addAll(arrayListCommand);
    }
}
