package Distributore;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class SendCoinsCommand extends SendCommand implements Command {

    private File fileMonete = new File("src/File_Testo/monete.txt");

    public SendCoinsCommand(Receiver receiver, PrintWriter printWriter) {
        super(receiver, printWriter);
    }

    @Override
    public void execute() {
        try {
            receiver.sendFile(printWriter, fileMonete);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in execute " + e);
        }
    }
}
