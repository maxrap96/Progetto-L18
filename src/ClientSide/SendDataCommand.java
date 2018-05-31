package ClientSide;

import Distributore.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SendDataCommand extends SendCommand implements Command {
    private File dataFile = new File("src/File_Testo/dati.txt");

    public SendDataCommand(ReceiverSend receiverSend, PrintWriter printWriter) {
        super(receiverSend, printWriter);
    }

    @Override
    public void execute() {
        try {
            receiverSend.sendFile(printWriter, dataFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in execute " + e);
        }
    }
}
