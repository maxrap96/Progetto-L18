package ClientSide;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SendCommand implements Command {
    private ReceiverSend receiverSend;
    private PrintWriter printWriter;
    private File sendThisFile;

    public SendCommand(ReceiverSend receiverSend, PrintWriter printWriter, String path) {
        this.receiverSend = receiverSend;
        this.printWriter = printWriter;
        this.sendThisFile = new File(path);
    }

    @Override
    public void execute() {
        try {
            receiverSend.sendFile(printWriter, sendThisFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in execute " + e);
        }
    }
}
