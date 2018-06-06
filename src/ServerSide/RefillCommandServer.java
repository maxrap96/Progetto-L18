package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class RefillCommandServer implements CommandServer {
    private Socket clientSocket;
    private ReceiverServer receiverServer;
    private String stringCommand;

    public RefillCommandServer(ReceiverServer receiverServer, Socket clientSocket, String stringCommand) {
        this.clientSocket = clientSocket;
        this.receiverServer = receiverServer;
        this.stringCommand = stringCommand;
    }

    @Override
    public void execute() {
        ArrayList<String> arrayListTmp = new ArrayList<>();
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            receiverServer.sendString(stringCommand, clientSocket);
            receiverServer.saveStringsFromClient(arrayListTmp, inFromClient);
            //receiverServer.sendString(END_SENDING, clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
