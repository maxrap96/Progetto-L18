package ServerSide;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection extends Thread {
    private ObservableList<String> obsvStats;
    private ObservableList<String> obsvMenu;
    private ObservableList<String> obsvCoins;
    private ObservableList<String> obsvData;
    private ArrayList<DealWithTheClientThread> arrayList;
    private int portNumber;
    private Socket clientSocket;

    public ServerConnection(int port, ObservableList<String> obsvStats, ObservableList<String> obsvMenu,
                            ObservableList<String> obsvCoins, ObservableList<String> obsvData) {
        this.portNumber = port;
        this.obsvStats = obsvStats;
        this.obsvCoins = obsvCoins;
        this.obsvMenu = obsvMenu;
        this.obsvData = obsvData;
        this.arrayList = new ArrayList<>();
    }

    @Override
    public void run() {
        DealWithTheClientThread threadTmp;
        try {
            // Creazione socket di benvenuto
            ServerSocket serverSocket = new ServerSocket(portNumber);

            // Ciclo che consente la connessione a più client
            while (true) {
                // Accetta la connessione di un client
                clientSocket = serverSocket.accept();

                // Creazione del thread per ogni client che si connette
                threadTmp = new DealWithTheClientThread(clientSocket, obsvStats, obsvMenu, obsvCoins, obsvData);
                arrayList.add(threadTmp);
                threadTmp.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per scegliere il comando da eseguire e su quale Client.
     * @param command comando da eseguire.
     * @param index client su cui eseguire il comando.
     */
    public void chooseCommandExecutedByThread(String command, int index) {
        arrayList.get(index).chosenCommand(command);
    }
}
