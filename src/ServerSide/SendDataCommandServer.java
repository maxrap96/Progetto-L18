package ServerSide;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static ServerSide.StringCommandList.SEND_DATA;

public class SendDataCommandServer extends SendCommandServer implements CommandServer {

    public SendDataCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                                 ObservableList<String> arrayToSaveInfo, BufferedReader clientReader) {
        super(receiverServer, clientSocket, arrayToSaveInfo, clientReader);
    }

    @Override
    public void execute() {
        try {
            receiverServer.sendString(SEND_DATA, clientSocket);
            receiverServer.saveStringsFromClient(arrayListCommand, clientReader);
            this.saveArrayInObservable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
