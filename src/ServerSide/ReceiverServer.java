package ServerSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import static ServerSide.StringCommandList.END_SENDING;

/**
 * Classe che offre funzioni per inviare e salvare stringhe.
 */

public class ReceiverServer {
    /**
     * Funzione che invia una stringa.
     * @param sendThisString stringa da inviare.
     * @param client socket a cui inviare.
     * @throws IOException
     */
    protected void sendString(String sendThisString, Socket client) throws IOException {
        // Creazione dell'oggetto per scrivere al client
        PrintWriter outToClient = new PrintWriter(client.getOutputStream(), true);
        outToClient.println(sendThisString);
    }

    /**
     * Funzione che salva i dati in ingresso.
     * @param whereToSaveFileFromClient ArrayList in cui salvo i dati.
     * @throws IOException
     */
    protected void saveStringsFromClient(ArrayList<String> whereToSaveFileFromClient, BufferedReader inFromClient)
            throws IOException {
        whereToSaveFileFromClient.clear();
        String tmp;
        while ((tmp = inFromClient.readLine()) != null) {
            if (!tmp.equals(END_SENDING)) {
                whereToSaveFileFromClient.add(tmp);
                System.out.println(tmp);
            } else {
                break;
            }
        }
    }
}
