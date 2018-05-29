package ServerSide;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerConnection extends Thread {

    private ObservableList<String> obsvStats;
    private ObservableList<String> obsvMenu;
    private ObservableList<String> obsvCoins;
    private ObservableList<String> obsvData;
    private DealWithTheClientThread threadTmp;
    private HashMap<Integer, DealWithTheClientThread> threadHashMap;
    private int portNumber;
    private Socket clientSocket;

    public ServerConnection(int port, ObservableList<String> obsvStats, ObservableList<String> obsvMenu,
                            ObservableList<String> obsvCoins, ObservableList<String> obsvData) {
        this.portNumber = port;
        this.obsvStats = obsvStats;
        this.obsvCoins = obsvCoins;
        this.obsvMenu = obsvMenu;
        this.obsvData = obsvData;
        this.threadHashMap = new HashMap<>();
    }

    @Override
    public void run() {
        try {
            // Creazione socket di benvenuto
            ServerSocket serverSocket = new ServerSocket(portNumber);

            int i = 0;

            // Ciclo che consente la connessione a più Client
            while (true) {

                // Accetto la connessione di un client
                clientSocket = serverSocket.accept();

                // Creo il thread per ogni Client che si connette
                this.threadTmp = new DealWithTheClientThread(clientSocket, obsvStats, obsvMenu, obsvCoins, obsvData);
                this.threadTmp.start();
                threadHashMap.put(i, threadTmp);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
