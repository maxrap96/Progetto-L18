package ClientSide;

import Distributore.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SendMenuCommand extends SendCommand implements Command {
    private File menuFile = new File("src/File_Testo/menu.txt");

    public SendMenuCommand(ReceiverSend receiverSend, PrintWriter printWriter) {
        super(receiverSend, printWriter);
    }

    @Override
    public void execute() {
        try {
            receiverSend.sendFile(printWriter, menuFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in execute " + e);
        }
    }
}
