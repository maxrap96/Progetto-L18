package ClientSide;

import HotDrinkVendingMachine.Data;
import HotDrinkVendingMachine.TextPathFiles;

import java.io.IOException;
import java.util.ArrayList;

public class ReceiverOverwrite implements TextPathFiles {
    private Data menuData = new Data("src/File_Testo/menu.txt");

    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand) {
        try {
            saveFileFromCommand(arrayFromCommand);
            System.out.println("File Menu Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFileFromCommand(ArrayList<String> arrayFromCommand) throws IOException {
        menuData.saveFileFromCommand(arrayFromCommand);
    }
}
