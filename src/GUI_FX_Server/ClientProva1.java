package GUI_FX_Server;

import GUI_FX_VendingMachine.FileClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;

public class ClientProva1 implements Runnable, FileClient {

    static private String stringSentToServer;
    static private String ip;
    static private int serverPort;

    public ClientProva1(String ipServer, int port) {
        ip = ipServer;
        serverPort = port;
    }

    @Override
    public void run() {
        try {
            // Creazione del socket attraverso cui inviare i dati
            Socket clientSocket = new Socket(ip, serverPort);

            // Creazione dell'ggetto per scrivere
            PrintWriter channelOutToServer =
                    new PrintWriter(clientSocket.getOutputStream(), true);

            // Creazione dell'oggetto per ricevere
            BufferedReader inFromServer =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            sendFile(channelOutToServer, new File("src/File_Testo/menu.txt"));
            // Remember to close inFromServer
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Funzione che invia un file.
     *
     * @param file file da inviare.
     * @param whereToWrite mezzo attraverso cui invio il file.
     */
    private static void sendFile(PrintWriter whereToWrite, File file)throws IOException{
        String stringFromFile;

        // Buffer per la lettura da File
        BufferedReader inFromFile =
                new BufferedReader(new FileReader(file.getPath()));

        // Invio al Client
        while ((stringFromFile = inFromFile.readLine()) != null) {
            whereToWrite.println(stringFromFile);
        }
        inFromFile.close();
    }
}
