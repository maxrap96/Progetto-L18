package ClientSide;

import Distributore.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SendCoinsCommand extends SendCommand implements Command {
    private File coinsFile = new File("src/File_Testo/monete.txt");

    public SendCoinsCommand(ReceiverSend receiverSend, PrintWriter printWriter) {
        super(receiverSend, printWriter);
    }

    @Override
    public void execute() {
        try {
            receiverSend.sendFile(printWriter, coinsFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in execute " + e);
        }
    }
}
