package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static ServerSide.StringCommandList.SEND_STATS;

public class SendStatsCommandServer extends SendCommandServer implements CommandServer {

    public SendStatsCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                                  ArrayList<String> arrayToSaveInfo, BufferedReader clientReader) {
        super(receiverServer, clientSocket, arrayToSaveInfo, clientReader);
    }

    @Override
    public void execute() {
        try {
            receiverServer.sendString(SEND_STATS, clientSocket);
            receiverServer.saveStringsFromClient(arrayToSaveInfo, clientReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
