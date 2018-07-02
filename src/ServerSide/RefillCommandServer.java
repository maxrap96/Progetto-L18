package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Classe che si occupa di inviare i comandi di refill.
 */

public class RefillCommandServer implements CommandServer {
    private Socket clientSocket;
    private ReceiverServer receiverServer;
    private String stringCommand;

    public RefillCommandServer(ReceiverServer receiverServer, Socket clientSocket, String stringCommand) {
        this.clientSocket = clientSocket;
        this.receiverServer = receiverServer;
        this.stringCommand = stringCommand;
    }

    /**
     * Funzione che esegue i comandi di ricarica del receiver appropriato.
     */
    @Override
    public void execute() {
        ArrayList<String> arrayListTmp = new ArrayList<>();
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            receiverServer.sendString(stringCommand, clientSocket);
            receiverServer.saveStringsFromClient(arrayListTmp, inFromClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
