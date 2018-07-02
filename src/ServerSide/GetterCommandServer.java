package ServerSide;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Classe che invia e salva stringhe client.
 */

public class GetterCommandServer implements CommandServer {
    private ReceiverServer receiverServer;
    private Socket clientSocket;
    private ObservableList<String> observableListCommand;
    private ArrayList<String> arrayListCommand;
    private BufferedReader clientReader;
    private String stringCommand;

    public GetterCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                               ObservableList<String> arrayToSaveInfo, BufferedReader clientReader,
                               String stringCommand) {
        this.receiverServer = receiverServer;
        this.clientSocket = clientSocket;
        this.observableListCommand = arrayToSaveInfo;
        this.clientReader = clientReader;
        this.arrayListCommand = new ArrayList<>();
        this.stringCommand = stringCommand;
    }

    /**
     * Funzione che esegue i comandi di invio e salvataggio del receiver appropriato.
     */
    @Override
    public void execute() {
        try {
            receiverServer.sendString(stringCommand, clientSocket);
            receiverServer.saveStringsFromClient(arrayListCommand, clientReader);
            this.saveArrayInObservable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per sovrascrivere l'observableList.
     */
    private void saveArrayInObservable() {
        if (!(arrayListCommand.isEmpty() || arrayListCommand.equals(observableListCommand))) {
            observableListCommand.clear();
            observableListCommand.addAll(arrayListCommand);
        }
    }
}
