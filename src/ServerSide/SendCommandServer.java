package ServerSide;

import java.io.BufferedReader;
import java.net.Socket;
import java.util.ArrayList;

public abstract class SendCommandServer implements CommandServer {

    protected ReceiverServer receiverServer;
    protected Socket clientSocket;
    protected ArrayList<String> arrayToSaveInfo;
    protected BufferedReader clientReader;

    public SendCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                             ArrayList<String> arrayToSaveInfo, BufferedReader clientReader) {
        this.receiverServer = receiverServer;
        this.clientSocket = clientSocket;
        this.arrayToSaveInfo = arrayToSaveInfo;
        this.clientReader = clientReader;
    }
}
