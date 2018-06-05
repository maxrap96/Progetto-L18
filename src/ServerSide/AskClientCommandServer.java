package ServerSide;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class AskClientCommandServer implements CommandServer {
    private ReceiverServer receiverServer;
    private Socket clientSocket;
    private ObservableList<String> observableListCommand;
    private ArrayList<String> arrayListCommand;
    private BufferedReader clientReader;
    private String stringCommand;

    public AskClientCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                                  ObservableList<String> arrayToSaveInfo, BufferedReader clientReader,
                                  String stringCommand) {
        this.receiverServer = receiverServer;
        this.clientSocket = clientSocket;
        this.observableListCommand = arrayToSaveInfo;
        this.clientReader = clientReader;
        this.arrayListCommand = new ArrayList<>();
        this.stringCommand = stringCommand;
    }

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
    protected void saveArrayInObservable() {
        if (!arrayListCommand.isEmpty()) {
            observableListCommand.clear();
            observableListCommand.addAll(arrayListCommand);
        }
    }
}
