package ServerSide;

import java.io.IOException;
import java.net.Socket;

import static ServerSide.StringCommandList.END_SENDING;

public class RefillCommandServer implements CommandServer {

    private Socket clientSocket;
    private ReceiverServer receiverServer;
    private String stringCommand;

    public RefillCommandServer(Socket clientSocket, ReceiverServer receiverServer, String stringCommand) {
        this.clientSocket = clientSocket;
        this.receiverServer = receiverServer;
        this.stringCommand = stringCommand;
    }

    @Override
    public void execute() {
        try {
            receiverServer.sendString(stringCommand, clientSocket);
            receiverServer.sendString(END_SENDING, clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
