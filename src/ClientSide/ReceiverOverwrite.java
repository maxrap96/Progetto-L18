package ClientSide;

import Distributore.Data;
import Distributore.TextFiles;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReceiverOverwrite implements TextFiles {
    private Data menuData = new Data("src/File_Testo/menuProva.txt");
    private String pathfile = "src/File_Testo/menuProva.txt";
    private File menu = new File(pathfile);

    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand) {
        try {
            emptyFile();
            saveFileFromCommand(arrayFromCommand);
            System.out.println("File Menu Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFileFromCommand(ArrayList<String> arrayFromCommand) {
        try {
            menuData.saveFileFromCommand(arrayFromCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void emptyFile() throws IOException {
        PrintWriter printWriter = new PrintWriter(menu);
        printWriter.print("");
        printWriter.close();
    }
}
