package GUI_FX_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class DealWithTheClientThread implements Runnable, FileServer, StringCommandList {

    private ArrayList<String> stats;
    private ArrayList<String> menu;
    private ArrayList<String> coins;
    private ArrayList<String> data;
    private Socket clientSocket;
    private BufferedReader inFromClient; // Oggetto per leggere da Client
    private String IdVendingMachine;
    private boolean state;

    public DealWithTheClientThread(Socket clientSocket, ArrayList<String> stats) {
        this.clientSocket = clientSocket;
        this.stats = stats;
        this.menu = new ArrayList<>();
        this.coins = new ArrayList<>();
        this.data = new ArrayList<>();
        this.state = STATE_WAITING;
    }

    @Override
    public void run() {
        try {
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            while (inFromClient.readLine() != null) {
                System.out.println("While");
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
        PrintWriter outToClient =
                new PrintWriter(client.getOutputStream(), true); // Oggetto per scrivere al Client
        outToClient.println(sendThisString);
    }

    /**
     * Funzione che dovrebbe capire se il Client è pronto a ricevere.
     *
     * @throws IOException
     */
    private void needToFindABetterName() throws IOException{
        System.out.println("Better name"); // Check se il codice arriva fino a qui
        System.out.println("Inserire valore da tastiera.\n0 SEND_DATA\n1 SEND_MENU\n2 SEND_COINS\n3 SEND_STATS");
        chooseCommand(Integer.parseInt(
                new BufferedReader(
                        new InputStreamReader(System.in)).readLine()));

    }

    /**
     * Funzione che permette di scegliere il comando da inviare al Client.
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

            default:
                System.out.println("Wrong command");
                //sendString("END_SENDING" ,clientSocket);
                //clientSocket.close();
                break;
        }
    }

    /**
     * Funzione che salva i dati in ingresso.
     *
     * @param whereToSaveFileFromClient ArrayList in cui salvo i dati.
     * @throws IOException
     */
    private void readyToReceive(ArrayList whereToSaveFileFromClient) throws IOException{
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
}
