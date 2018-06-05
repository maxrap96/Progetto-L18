package ServerSide;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class DealWithTheClientThread extends Thread implements StringCommandList {
    private ObservableList<String> obsvStats;
    private ObservableList<String> obsvMenu;
    private ObservableList<String> obsvCoins;
    private ObservableList<String> obsvData;
    private Socket clientSocket;
    private BufferedReader inFromClient;
    private HashMap<String, CommandServer> commandServerHashMap;

    public DealWithTheClientThread(Socket clientSocket, ClassOfObservableLists classOfObservableLists) {
        this.clientSocket = clientSocket;
        this.obsvStats = classOfObservableLists.getObservStats();
        this.obsvMenu = classOfObservableLists.getObservMenu();
        this.obsvCoins = classOfObservableLists.getObservCoins();
        this.obsvData = classOfObservableLists.getObservData();
    }

    @Override
    public void run() {
        try {
            // Inizializzazione dell'oggetto per leggere da client
            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Inizializzazione della mappa dei comandi, dopo aver inizializzato tutto ciò che serve
            this.initServerHashMap();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error caught: " + e);
        }
    }

    /**
     * Funzione per attivare un comando.
     *
     * Nota: Viene utilizzata dal server.
     *
     * @param command comando da eseguire.
     */
    protected void chosenCommand(String command) {
        if (commandServerHashMap.containsKey(command)) {
            commandServerHashMap.get(command).execute();
        } else {
            System.out.println("Comando non valido");
        }
    }

    /**
     * Funzione che inizializza la mappa dei comandi disponibili.
     */
    private void initServerHashMap() {
        this.commandServerHashMap = new HashMap<>();
        ReceiverServer receiverServer = new ReceiverServer();
        this.commandServerHashMap.put(SEND_COINS, new ServerCommandGetter(receiverServer, clientSocket, obsvCoins,
                inFromClient, SEND_COINS));
        this.commandServerHashMap.put(SEND_DATA, new ServerCommandGetter(receiverServer, clientSocket, obsvData,
                inFromClient, SEND_DATA));
        this.commandServerHashMap.put(SEND_MENU, new ServerCommandGetter(receiverServer, clientSocket, obsvMenu,
                inFromClient, SEND_MENU));
        this.commandServerHashMap.put(SEND_STATS, new ServerCommandGetter(receiverServer, clientSocket, obsvStats,
                inFromClient, SEND_STATS));
        this.commandServerHashMap.put(OVERWRITE_MENU, new OverwriteCommandServer(receiverServer, clientSocket, obsvMenu,
                OVERWRITE_MENU));
        this.commandServerHashMap.put(REFILL_COINS, new RefillCommandServer(receiverServer, clientSocket,
                REFILL_COINS));
        this.commandServerHashMap.put(REFILL_INGREDIENTS, new RefillCommandServer(receiverServer, clientSocket,
                REFILL_INGREDIENTS));
        this.commandServerHashMap.put(REFILL_ITEMS, new RefillCommandServer(receiverServer, clientSocket,
                REFILL_ITEMS));
    }
}
