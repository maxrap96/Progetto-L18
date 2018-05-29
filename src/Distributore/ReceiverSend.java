package Distributore;

import java.io.*;

public class ReceiverSend {

    /**
     * Funzione che invia un file.
     * @param file file da inviare.
     * @param whereToWrite mezzo attraverso cui invio il file.
     */
    protected void sendFile(PrintWriter whereToWrite, File file)throws IOException {
        String stringFromFile;

        // Buffer per la lettura da File
        BufferedReader inFromFile = new BufferedReader(new FileReader(file.getPath()));

        // Invio al Server
        while ((stringFromFile = inFromFile.readLine()) != null) {
            whereToWrite.println(stringFromFile);
        }

        inFromFile.close();
    }
}
