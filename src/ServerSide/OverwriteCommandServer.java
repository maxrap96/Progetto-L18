package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static ServerSide.StringCommandList.END_SENDING;
import static ServerSide.StringCommandList.OVERWRITE_MENU;

public class OverwriteCommandServer extends SendCommandServer implements CommandServer{

    public OverwriteCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                                  ArrayList<String> arrayToSaveInfo, BufferedReader clientReader) {
        super(receiverServer, clientSocket, arrayToSaveInfo, clientReader);
    }

    @Override
    public void execute() {
        try {
            receiverServer.sendString(OVERWRITE_MENU, clientSocket);
            // Uso lo stesso nome per l'array, ma stavolta invio informazioni al Client
            for (String tmp : arrayToSaveInfo){
                receiverServer.sendString(tmp, clientSocket);
            }
            receiverServer.sendString(END_SENDING, clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
