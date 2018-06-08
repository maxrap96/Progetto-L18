package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static ServerSide.StringCommandList.END_SENDING;

public class OverwriteCommand implements Command {
    private ReceiverOverwrite receiverOverwrite;
    private BufferedReader bufferedReader;
    private ArrayList<String> menuFromServer;
    private String path;

    public OverwriteCommand(ReceiverOverwrite receiverOverwrite, BufferedReader bufferedReader, String path) {
        this.receiverOverwrite = receiverOverwrite;
        this.bufferedReader = bufferedReader;
        this.menuFromServer = new ArrayList<>();
        this.path = path;
    }

    @Override
    public void execute() {
        menuFromServer.clear();
        try {
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                if (!tmp.equals(END_SENDING)) {
                    menuFromServer.add(tmp);
                } else {
                    break;
                }
            }
            receiverOverwrite.overwriteFileReceiver(menuFromServer, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
