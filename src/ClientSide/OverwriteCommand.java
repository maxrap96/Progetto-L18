package ClientSide;

import HotDrinkVendingMachine.Command;
import ServerSide.StringCommandList;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class OverwriteCommand implements Command, StringCommandList {
    private ReceiverOverwrite receiverOverwrite;
    private BufferedReader bufferedReader;
    private ArrayList<String> menuFromServer;

    public OverwriteCommand(ReceiverOverwrite receiverOverwrite, BufferedReader bufferedReader) {
        this.receiverOverwrite = receiverOverwrite;
        this.bufferedReader = bufferedReader;
        this.menuFromServer = new ArrayList<>();
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
            receiverOverwrite.overwriteFileReceiver(menuFromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
