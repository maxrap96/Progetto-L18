package ClientSide;

import HotDrinkVendingMachine.TextPathFiles;
import ServerSide.StringCommandList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ClientVendMach extends Thread implements StringCommandList, TextPathFiles {
    private String ip;
    private int serverPort;
    private PrintWriter channelOutToServer;
    private BufferedReader inFromServer;
    private HashMap<String, Command> commandHashMap;
    private BooleanRefill booleanRefill;
    private ReceiverSend receiverSend;
    private ReceiverOverwrite receiverOverwrite;
    private ReceiverRefill receiverRefill;

    public ClientVendMach(String ipServer, int port, BooleanRefill booleanRefill) {
        this.ip = ipServer;
        this.serverPort = port;
        this.commandHashMap = new HashMap<>();
        this.booleanRefill = booleanRefill;
        this.receiverSend = new ReceiverSend();
        this.receiverOverwrite = new ReceiverOverwrite();
        this.receiverRefill = new ReceiverRefill();
    }

    @Override
    public void run() {
        try {
            // Creazione del socket attraverso cui inviare i dati
            Socket clientSocket = new Socket(ip, serverPort);

            // Creazione dell'oggetto per scrivere
            channelOutToServer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Creazione dell'oggetto per ricevere
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Aggiunta dei comandi da eseguire
            this.addCommands(receiverSend, receiverOverwrite, receiverRefill);

            String tmp;
            while ((tmp = inFromServer.readLine()) != null) {
                if (commandHashMap.containsKey(tmp)) {
                    commandReceived(tmp);
                }
            }
            inFromServer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error caught: " + e);
        }
    }

    /**
     * Funzione che legge il comando ricevuto dal server ed esegue l'azione corrispondente.
     * @param commandFromServer stringa di comando letta da server.
     */
    private void commandReceived(String commandFromServer) {
        this.commandHashMap.get(commandFromServer).execute();
        this.channelOutToServer.println(END_SENDING);
    }

    /**
     * Funzione che aggiunge i comandi da eseguire.
     */
    private void addCommands(ReceiverSend receiverSend, ReceiverOverwrite receiverOverwrite,
                             ReceiverRefill receiverRefill) {
        this.commandHashMap.put(SEND_MENU, new SendCommand(receiverSend, channelOutToServer, MENU_PATH));
        this.commandHashMap.put(SEND_DATA, new SendCommand(receiverSend, channelOutToServer, DATA_PATH));
        this.commandHashMap.put(SEND_COINS, new SendCommand(receiverSend, channelOutToServer, COINS_PATH));
        this.commandHashMap.put(SEND_STATS, new SendCommand(receiverSend, channelOutToServer, STATS_PATH));
        this.commandHashMap.put(OVERWRITE_MENU, new OverwriteCommand(receiverOverwrite, inFromServer));
        this.commandHashMap.put(REFILL_COINS, new RefillCoinsCommand(receiverRefill, booleanRefill));
        this.commandHashMap.put(REFILL_INGREDIENTS, new RefillIngredientsCommand(receiverRefill, booleanRefill));
        this.commandHashMap.put(REFILL_ITEMS, new RefillItemsCommand(receiverRefill, booleanRefill));
    }
}
