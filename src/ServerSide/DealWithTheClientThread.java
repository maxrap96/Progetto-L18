package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class DealWithTheClientThread implements Runnable, StringCommandList {

    private ArrayList<String> stats;
    private ArrayList<String> menu;
    private ArrayList<String> coins;
    private ArrayList<String> data;
    private Socket clientSocket;
    private BufferedReader inFromClient;
    private String IdVendingMachine;
    private boolean state;

    public DealWithTheClientThread(Socket clientSocket, ArrayList<String> stats, ArrayList<String> menu,
                                   ArrayList<String> coins, ArrayList<String> data) {
        this.clientSocket = clientSocket;
        this.stats = stats;
        this.menu = menu;
        this.coins = coins;
        this.data = data;
        this.state = STATE_WAITING;
    }

    @Override
    public void run() {
        try {
            // Inizializzazione dell'oggetto per leggere da Client
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            while (inFromClient.readLine() != null) {
                if (state) {
                    state = !STATE_WAITING;
                    needToFindABetterName();
                    state = STATE_WAITING;
                    sendString(READY, clientSocket);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error caught: " + e);
        }
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
    private void needToFindABetterName() throws IOException{
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
     * @throws IOException
     */
    private void chooseCommand(int index) throws IOException{
        switch (index){
            case 0:
                sendString(SEND_DATA, clientSocket);
                readyToReceive(data);
                break;

            case 1:
                sendString(SEND_MENU, clientSocket);
                readyToReceive(menu);
                break;

            case 2:
                sendString(SEND_COINS, clientSocket);
                readyToReceive(coins);
                break;

            case 3:
                sendString(SEND_STATS, clientSocket);
                readyToReceive(stats);
                break;

            case 4:
                sendString(OVERWRITE_MENU, clientSocket);
                for (String tmp : menu){
                    sendString(tmp, clientSocket);
                }
                sendString(END_SENDING, clientSocket);
                break;

            default:
                System.out.println("Wrong command");
                break;
        }
    }

    protected void chosenCommand(String command, ArrayList<String> arrayUsedToSaveInfo){
        if(isAValidCommand(command)){
            try {
                sendString(command, clientSocket);
                readyToReceive(arrayUsedToSaveInfo);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error chosenCommand " + e);
            }
        }

    }

    /**
     * Funzione che salva i dati in ingresso.
     *
     * @param whereToSaveFileFromClient ArrayList in cui salvo i dati.
     * @throws IOException
     */
    private void readyToReceive(ArrayList whereToSaveFileFromClient) throws IOException{
        whereToSaveFileFromClient.clear();
        String tmp;
        while ((tmp = inFromClient.readLine()) != null){
            if (!tmp.equals(END_SENDING)) {
                whereToSaveFileFromClient.add(tmp);
                System.out.println(tmp);
            } else {
                break;
            }
        }
    }



    /**
     * Funzione che confronta la stringa passata e decide se è un comando valido.
     *
     * @param command comando da analizzare.
     */
    private boolean isAValidCommand(String command){
        if (command.equals(SEND_COINS) || command.equals(SEND_DATA) || command.equals(SEND_MENU) ||
                command.equals(SEND_STATS) || command.equals(OVERWRITE_MENU)) {
            return true;
        } else {
            return false;
        }
    }
}
