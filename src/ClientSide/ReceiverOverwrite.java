package ClientSide;

import HotDrinkVendingMachine.Data;
import HotDrinkVendingMachine.TextPathFiles;

import java.io.IOException;
import java.util.ArrayList;

public class ReceiverOverwrite implements TextPathFiles {
    private Data menuData = new Data(MENU_PATH);

    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand) {
        try {
            saveFileReceived(arrayFromCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFileReceived(ArrayList<String> arrayFromCommand) throws IOException {
        menuData.saveFileFromCommand(arrayFromCommand);
    }
}
