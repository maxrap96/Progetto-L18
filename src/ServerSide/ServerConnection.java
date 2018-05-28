package ServerSide;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection extends Thread {

    //private ArrayList<String> stats;
    private ObservableList<String> obsvstats;
    //private ArrayList<String> menu;
    private ObservableList<String> obsvmenu;
    //private ArrayList<String> coins;
    private ObservableList<String> obsvcoins;
    //private ArrayList<String> data;
    private ObservableList<String> obsvdata;
    private int portNumber;
    private Socket clientSocket;

    public ServerConnection(int port, ObservableList<String> obsvstats, ObservableList<String> obsvmenu,
                            ObservableList<String> obsvcoins, ObservableList<String> obsvdata) {
        this.portNumber = port;
        this.obsvstats = obsvstats;
        this.obsvcoins = obsvcoins;
        this.obsvmenu= obsvmenu;
        this.obsvdata = obsvdata;
    }

    @Override
    public void run() {
        try {
            // Creazione socket di benvenuto
            ServerSocket serverSocket = new ServerSocket(portNumber);

            // Ciclo che consente la connessione a più Client
            while (true) {

                // Accetto la connessione di un client
                clientSocket = serverSocket.accept();

                // Creo il thread per ogni Client che si connette
                new DealWithTheClientThread(clientSocket, obsvstats, obsvmenu, obsvcoins, obsvdata).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
