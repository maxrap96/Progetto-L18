package ClientSide;

import HotDrinkVendingMachine.Command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SendStatsCommand extends SendCommand implements Command {
    private File statsFile = new File("src/File_Testo/stats.txt");

    public SendStatsCommand(ReceiverSend receiverSend, PrintWriter printWriter) {
        super(receiverSend, printWriter);
    }

    @Override
    public void execute() {
        try {
            receiverSend.sendFile(printWriter, statsFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in execute " + e);
        }
    }
}
