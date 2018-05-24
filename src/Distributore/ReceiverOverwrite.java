package Distributore;

public class ReceiverOverwrite implements TextFiles {

    private Data menuData = new Data("src/File_Testo/menuPtova.txt");

    protected void overwriteFile(String string){
        menuData.writeData(string);
        System.out.println(string);
    }
}
