package ClientSide;

import java.io.*;

import static ServerSide.StringCommandList.END_SENDING;

/**
 * Classe receiver che si occupa dell'invio dei file verso il server.
 */

public class ReceiverSend {
    /**
     * Funzione che invia un file.
     * @param file file da inviare.
     * @param whereToWrite mezzo attraverso cui si invia il file.
     */
    protected void sendFile(PrintWriter whereToWrite, File file) throws IOException {
        String stringFromFile;

        // Buffer per la lettura da File
        BufferedReader inFromFile = new BufferedReader(new FileReader(file.getPath()));

        // Invio al server
        while ((stringFromFile = inFromFile.readLine()) != null) {
            whereToWrite.println(stringFromFile);
        }
        inFromFile.close();
    }
}
