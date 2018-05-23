package Distributore;

import ServerSide.StringCommandList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ClientVendMach extends Thread implements StringCommandList {

    private String ip;
    private int serverPort;
    private PrintWriter channelOutToServer;
    private BufferedReader inFromServer;
    private boolean fileReceived = false;
    private HashMap<String, Command> commandHashMap;
    private Receiver receiver;


    public ClientVendMach(String ipServer, int port) {
        this.ip = ipServer;
        this.serverPort = port;
        this.commandHashMap = new HashMap<>();
        this.receiver = new Receiver();
    }

    @Override
    public void run() {
        try {
            // Creazione del socket attraverso cui inviare i dati
            Socket clientSocket = new Socket(ip, serverPort);

            // Creazione dell'ggetto per scrivere
            channelOutToServer =
                    new PrintWriter(clientSocket.getOutputStream(), true);

            // Creazione dell'oggetto per ricevere
            inFromServer =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            // Aggiungo i comandi da eseguire
            this.addCommands();

            String tmp;
            channelOutToServer.println(READY);
            while ((tmp = inFromServer.readLine()) != null) {
                // Controllo se il Server sia pronto
                if (tmp.equals(READY)){
                    channelOutToServer.println(tmp);
                }
                if (isAValidCommand(tmp)){
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
     * Funzione che legge il comando ricevuto dal Server ed esegue l'azione corrispondente.
     *
     * @param commandFromServer
     */
    private void commandReceived(String commandFromServer){
        this.commandHashMap.get(commandFromServer).execute();
        this.channelOutToServer.println(END_SENDING);
    }

    /**
     * Funzione che confronta la stringa passata e decide se è un comando valido.
     *
     * @param command comando da analizzare.
     */
    private boolean isAValidCommand(String command){
        if (command.equals(SEND_COINS) || command.equals(SEND_DATA) || command.equals(SEND_MENU) ||
                command.equals(SEND_STATS)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Funzione che aggiunge i comandi da eseguire.
     */
    private void addCommands(){
        this.commandHashMap.put(SEND_MENU, new SendMenuCommand(receiver, channelOutToServer));
        this.commandHashMap.put(SEND_DATA, new SendCoinsCommand(receiver, channelOutToServer));
        this.commandHashMap.put(SEND_COINS, new SendCoinsCommand(receiver, channelOutToServer));
        this.commandHashMap.put(SEND_STATS, new SendStatsCommand(receiver, channelOutToServer));
    }

    public boolean isFileReceived(){
        return fileReceived;
    }

    public void fileOpened(){
        fileReceived = false;
    }

}
