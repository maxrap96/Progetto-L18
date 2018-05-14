package GUI_FX_Server;

import GUI_FX_VendingMachine.FileClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;

public class ClientProva1 implements Runnable, FileClient, StringCommandList {

    private String stringSentToServer;
    private String ip;
    private int serverPort;
    private PrintWriter channelOutToServer;
    private BufferedReader inFromServer;
    private boolean state;


    public ClientProva1(String ipServer, int port) {
        ip = ipServer;
        serverPort = port;
        state = STATE_WAITING;
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

            System.out.println("Ready to Send");
            String tmp;
            channelOutToServer.println(READY);
            while ((tmp = inFromServer.readLine()) != null) {
                System.out.println("While 1");
                if (state){
                    System.out.println(tmp);
                    state = !STATE_WAITING;
                    commandReceived(tmp);
                    state = STATE_WAITING;
                }
            }

            inFromServer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error caught: " + e);
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

        // Invio al Server
        while ((stringFromFile = inFromFile.readLine()) != null) {
            whereToWrite.println(stringFromFile);
        }

        whereToWrite.println("END_SENDING");
        inFromFile.close();
    }

    /**
     * Funzione che legge il comando ricevuto dal Server ed esegue l'azione corrispondente.
     *
     * @param commandFromServer
     * @throws IOException
     */
    private void commandReceived(String commandFromServer) throws IOException{
        if (commandFromServer != null) {
            switch (commandFromServer) {
                case "SEND_MENU":
                    sendFile(channelOutToServer, fileMenu);
                    break;

                case "SEND_DATA":
                    sendFile(channelOutToServer, fileDati); // Manca la parte Server
                    break;

                default:
                    // Prova per capire gli errori, ma non ancora implementato
                    System.out.println("Not a valid command");
                    channelOutToServer.println("ERROR");
                    break;
            }
        }
    }
}
