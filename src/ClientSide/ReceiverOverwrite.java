package ClientSide;

import HotDrinkVendingMachine.Data;
import HotDrinkVendingMachine.TextPathFiles;

import java.util.ArrayList;

public class ReceiverOverwrite implements TextPathFiles {
    private Data menuData = new Data(MENU_PATH);

    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand) {
        saveFileReceived(arrayFromCommand);
    }

    private void saveFileReceived(ArrayList<String> arrayFromCommand) {
        menuData.saveFileFromCommand(arrayFromCommand);
    }
}
