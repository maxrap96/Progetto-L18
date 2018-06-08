package ClientSide;

import HotDrinkVendingMachine.Data;

import java.util.ArrayList;

public class ReceiverOverwrite {
    private Data menuData;

    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand, String path) {
        this.menuData = new Data(path);
        saveFileReceived(arrayFromCommand);
    }

    private void saveFileReceived(ArrayList<String> arrayFromCommand) {
        menuData.saveFileFromCommand(arrayFromCommand);
    }
}
