package ServerSide;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import static ServerSide.StringCommandList.SEND_MENU;

public class SendMenuCommandServer extends SendCommandServer implements CommandServer {
    
    public SendMenuCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                                 ObservableList<String> arrayToSaveInfo, BufferedReader clientReader) {
        super(receiverServer, clientSocket, arrayToSaveInfo, clientReader);
    }

    @Override
    public void execute() {
        try {
            receiverServer.sendString(SEND_MENU, clientSocket);
            receiverServer.saveStringsFromClient(arrayListCommand, clientReader);
            this.saveArrayInObservable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
