package ServerSide;

import javafx.collections.ObservableList;

import javax.sound.midi.Receiver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class DealWithTheClientThread implements Runnable, StringCommandList {

    private ArrayList<String> stats;
    private ArrayList<String> menu;
    private ArrayList<String> coins;
    private ArrayList<String> data;
    private ObservableList<String> obsvStats;
    private ObservableList<String> obsvMenu;
    private ObservableList<String> obsvCoins;
    private ObservableList<String> obsvData;
    private Socket clientSocket;
    private BufferedReader inFromClient;
    private HashMap<String, CommandServer> commandServerHashMap;
    private String IdVendingMachine;

    public DealWithTheClientThread(Socket clientSocket, ObservableList<String> obsvStats,
                                   ObservableList<String> obsvMenu, ObservableList<String> obsvCoins,
                                   ObservableList<String> obsvData) {
        this.initArrayList();
        this.clientSocket = clientSocket;
        this.obsvStats = obsvStats;
        this.obsvMenu = obsvMenu;
        this.obsvCoins = obsvCoins;
        this.obsvData= obsvData;
    }

    @Override
    public void run() {
        try {
            // Inizializzazione dell'oggetto per leggere da Client
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            // Inizializzazione della mappa dei comandi, dopo aver inizializzato tutto ciò che mi serve
            this.initServerHashMap();

            while (inFromClient.readLine() != null) {
                commandFromKeyboard();
                sendString(READY, clientSocket);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error caught: " + e);
        }
    }

    private void initArrayList(){
        this.stats = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    /**
     * Funzione che invia una stringa.
     *
     * @param sendThisString stringa da inviare.
     * @param client socket a cui inviare.
     * @throws IOException
     */
    private void sendString(String sendThisString, Socket client) throws IOException {
        // Creazione dell'oggetto per scrivere al Client
        PrintWriter outToClient = new PrintWriter(client.getOutputStream(), true);
        outToClient.println(sendThisString);
    }

    /**
     * Funzione che dovrebbe capire se il Client è pronto a ricevere.
     *
     * @throws IOException
     */
    private void commandFromKeyboard() throws IOException{
        // Questo "sout" serve solo per fare i test da riga di comando, quando si userà l'interfaccia togliere
        // questa funzione e usare solo la chooseCommand.
        System.out.println("Inserire valore da tastiera.\n0 SEND_DATA\n1 SEND_MENU\n2 SEND_COINS\n3 SEND_STATS\n" +
                "4 OVERWRITE_MENU");
        chooseCommand(Integer.parseInt(
                new BufferedReader(
                        new InputStreamReader(System.in)).readLine()));

    }

    /**
     * Funzione che permette di scegliere il comando, sotto forma di stringa, da inviare al Client.
     *
     * @param index indice per scegliere il comando idoneo.
     */
    private void chooseCommand(int index){
        switch (index){
            case 0:
                commandServerHashMap.get(SEND_DATA).execute();
                obsvData.addAll(data);
                break;

            case 1:
                commandServerHashMap.get(SEND_MENU).execute();
                obsvMenu.addAll(menu);
                break;

            case 2:
                commandServerHashMap.get(SEND_COINS).execute();
                obsvCoins.addAll(coins);
                break;

            case 3:
                commandServerHashMap.get(SEND_STATS).execute();
                obsvStats.addAll(stats);
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
     * Nota: Possibile uso negli strati della UI.
     *
     * @param command
     */
    protected void chosenCommand(String command){
        if (commandServerHashMap.containsKey(command)) {
            commandServerHashMap.get(command).execute();
        } else {
            System.out.println("Comando non valido");
        }
    }

    /**
     * Funzione che inizializza la mappa dei comandi disponibili.
     */
    private void initServerHashMap(){
        this.commandServerHashMap = new HashMap<>();
        ReceiverServer receiverServer = new ReceiverServer();
        this.commandServerHashMap.put(SEND_COINS, new SendCoinsCommandServer(receiverServer, clientSocket, coins,
                inFromClient));
        this.commandServerHashMap.put(SEND_DATA, new SendDataCommandServer(receiverServer, clientSocket, data,
                inFromClient));
        this.commandServerHashMap.put(SEND_MENU, new SendMenuCommandServer(receiverServer, clientSocket, menu,
                inFromClient));
        this.commandServerHashMap.put(SEND_STATS, new SendStatsCommandServer(receiverServer, clientSocket, stats,
                inFromClient));
        this.commandServerHashMap.put(OVERWRITE_MENU, new OverwriteCommandServer(receiverServer, clientSocket, menu,
                inFromClient));
    }
}
