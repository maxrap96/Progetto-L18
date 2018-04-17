import Client_Server.ClientSide;
import Client_Server.ServerSide;
import Distributore.Distributore;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        Distributore macchinetta = new Distributore();
        macchinetta.textualInput();;

        // startConnection(); // Funziona, ma non so come farlo fermare da solo
    }

    /**
     * Invio stats.txt a Server e ricevo serverMenu.txt
     */

    // Per essere più chiari: stats.txt sovrascrive serverStats.txt, mentre serverMenu.txt sovrascrive menu.txt

    private static void startConnection(){
        ServerSide serverSide = new ServerSide();
        ClientSide clientSide = new ClientSide();
        serverSide.start();
        clientSide.start();
    }
}