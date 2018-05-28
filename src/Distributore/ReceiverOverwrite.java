package Distributore;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReceiverOverwrite implements TextFiles {

    private Data menuData = new Data("src/File_Testo/menuPtova.txt");
    private String pathfile = "src/File_Testo/menuPtova.txt";
    private File menu = new File(pathfile);

    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand){
        try {
            emptyFile();
            saveFileFromCommand(arrayFromCommand);
            System.out.println("File Menu Saved");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveFileFromCommand(ArrayList<String> arrayList) throws IOException{
        PrintWriter printWriter = new PrintWriter(menu);
        for (int i = 0; i < arrayList.size(); i++){
            printWriter.print(arrayList.get(i));
            if(i != arrayList.size() - 1){
                printWriter.append("\n");
            }
        }
        printWriter.close();
    }
    private void emptyFile() throws IOException{
        PrintWriter printWriter = new PrintWriter(menu);
        printWriter.print("");
        printWriter.close();
    }
}
