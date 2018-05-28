package ServerSide;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection extends Thread {

    private ArrayList<String> stats;
    private ObservableList<String> obsvstats;
    private ArrayList<String> menu;
    private ArrayList<String> coins;
    private ArrayList<String> data;
    private int portNumber;
    private Socket clientSocket;

    public ServerConnection(int port, ObservableList<String> obsvstats, ArrayList<String> menu, ArrayList<String> coins,
                            ArrayList<String> data) {
        this.portNumber = port;
        this.obsvstats = obsvstats;
        this.menu = menu;
        this.coins = coins;
        this.data = data;
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
                new DealWithTheClientThread(clientSocket, obsvstats, menu, coins, data).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
