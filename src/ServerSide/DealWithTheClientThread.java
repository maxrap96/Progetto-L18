package ServerSide;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    public DealWithTheClientThread(Socket clientSocket, ObservableList<String> obsvStats,
                                   ObservableList<String> obsvMenu, ObservableList<String> obsvCoins,
                                   ObservableList<String> obsvData) {
        this.clientSocket = clientSocket;
        this.obsvStats = obsvStats;
        this.obsvMenu = obsvMenu;
        this.obsvCoins = obsvCoins;
        this.obsvData = obsvData;
    }

    @Override
    public void run() {
        try {
            // Inizializzazione dell'oggetto per leggere da client
            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Inizializzazione della mappa dei comandi, dopo aver inizializzato tutto ciò che serve
            this.initServerHashMap();

            while (inFromClient.readLine() != null) {
                commandFromKeyboard();
                sendString(READY, clientSocket);
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error caught: " + e);
        }
    }

    /**
     * Funzione che invia una stringa.
     * @param sendThisString stringa da inviare.
     * @param client socket a cui inviare.
     * @throws IOException
     */
    private void sendString(String sendThisString, Socket client) throws IOException {
        // Creazione dell'oggetto per scrivere al client
        PrintWriter outToClient = new PrintWriter(client.getOutputStream(), true);
        outToClient.println(sendThisString);
    }

    /**
     * Funzione che dovrebbe capire se il client è pronto a ricevere.
     * @throws IOException
     */
    private void commandFromKeyboard() throws IOException {
        System.out.println("Inserire valore da tastiera.\n0 SEND_DATA\n1 SEND_MENU\n2 SEND_COINS\n3 SEND_STATS\n" +
                "4 OVERWRITE_MENU");
        chooseCommand(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));
    }

    /**
     * Funzione che permette di scegliere il comando, sotto forma di stringa, da inviare al client.
     * @param index indice per scegliere il comando idoneo.
     */
    private void chooseCommand(int index) {
        switch (index) {
            case 0:
                commandServerHashMap.get(SEND_DATA).execute();
                break;
            case 1:
                commandServerHashMap.get(SEND_MENU).execute();
                break;
            case 2:
                commandServerHashMap.get(SEND_COINS).execute();
                break;
            case 3:
                commandServerHashMap.get(SEND_STATS).execute();
                break;
            case 4:
                commandServerHashMap.get(OVERWRITE_MENU).execute();
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    /**
     * Funzione per attivare un comando.
     *
     * Nota: Viene utilizzata dal Server.
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
        this.commandServerHashMap.put(SEND_COINS, new AskClientCommandServer(receiverServer, clientSocket, obsvCoins,
                inFromClient, SEND_COINS));
        this.commandServerHashMap.put(SEND_DATA, new AskClientCommandServer(receiverServer, clientSocket, obsvData,
                inFromClient, SEND_DATA));
        this.commandServerHashMap.put(SEND_MENU, new AskClientCommandServer(receiverServer, clientSocket, obsvMenu,
                inFromClient, SEND_MENU));
        this.commandServerHashMap.put(SEND_STATS, new AskClientCommandServer(receiverServer, clientSocket, obsvStats,
                inFromClient, SEND_STATS));
        this.commandServerHashMap.put(OVERWRITE_MENU, new OverwriteCommandServer(receiverServer, clientSocket, obsvMenu,
                OVERWRITE_MENU));
    }
}
