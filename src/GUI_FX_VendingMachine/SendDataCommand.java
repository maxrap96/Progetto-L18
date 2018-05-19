package GUI_FX_VendingMachine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SendDataCommand extends SendCommand implements Command {

    private File fileDati = new File("src/File_Testo/dati.txt");

    public SendDataCommand(Receiver receiver, PrintWriter printWriter) {
        super(receiver, printWriter);
    }

    @Override
    public void execute() {
        try {
            receiver.sendFile(printWriter, fileDati);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in execute " + e);
        }

    }
}
