import Distributore.Distributore;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        OpenFile openFile = new OpenFile("menu.txt");
        ArrayList<String[]> fileAperto = openFile.apriFile();
        Distributore macchinetta = new Distributore(fileAperto);
        macchinetta.textualInput();
        System.out.println("check");
    }
}