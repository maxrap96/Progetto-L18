import Distributore.Distributore;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        OpenFile openFile = new OpenFile("menu.txt");
        ArrayList<String[]> fileaperto = openFile.apriFile();
        Distributore macchinetta = new Distributore(fileaperto);
        macchinetta.textualInput();
        System.out.println("check");    //out utile solo per metterci un debug e
                                        // controllare che tutto sia corretto in memoria.
    }
}