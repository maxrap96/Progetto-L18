package ClientSide;

import HotDrinkVendingMachine.FileManager;

import java.util.ArrayList;

public class ReceiverOverwrite {
    private FileManager menuData;

    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand, String path) {
        this.menuData = new FileManager(path);
        saveFileReceived(arrayFromCommand);
    }

    private void saveFileReceived(ArrayList<String> arrayFromCommand) {
        menuData.saveFileFromCommand(arrayFromCommand);
    }
}
