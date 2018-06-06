package ServerSide;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.Socket;

import static ServerSide.StringCommandList.END_SENDING;

public class OverwriteCommandServer implements CommandServer {
    private ReceiverServer receiverServer;
    private Socket clientSocket;
    private ObservableList<String> observableListCommand;
    private String commandString;

    public OverwriteCommandServer(ReceiverServer receiverServer, Socket clientSocket,
                                  ObservableList<String> arrayToSaveInfo, String commandString) {
        this.receiverServer = receiverServer;
        this.clientSocket = clientSocket;
        this.observableListCommand = arrayToSaveInfo;
        this.commandString = commandString;
    }

    @Override
    public void execute() {
        try {
            receiverServer.sendString(commandString, clientSocket);
            // Utilizzo dello stesso nome per l'array, ma vengono inviate informazioni al client
            for (String tmp : observableListCommand) {
                receiverServer.sendString(tmp, clientSocket);
            }
            receiverServer.sendString(END_SENDING, clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
