package GUI_FX_VendingMachine;

import GUI_FX_Server.StringCommandList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;

public class ClientVendMach extends Thread implements FileClient, StringCommandList {

    private String ip;
    private int serverPort;
    private PrintWriter channelOutToServer;
    private BufferedReader inFromServer;
    private boolean state;
    private boolean fileReceived = false;


    public ClientVendMach(String ipServer, int port) {
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

            String tmp;
            channelOutToServer.println(READY);
            while ((tmp = inFromServer.readLine()) != null) {
                // Controllo se il Server sia pronto
                if (tmp.equals(READY)){
                    channelOutToServer.println(tmp);
                }
                if (state && isAValidCommand(tmp)){
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
    private void sendFile(PrintWriter whereToWrite, File file)throws IOException{
        String stringFromFile;

        // Buffer per la lettura da File
        BufferedReader inFromFile =
                new BufferedReader(new FileReader(file.getPath()));

        // Invio al Server
        while ((stringFromFile = inFromFile.readLine()) != null) {
            whereToWrite.println(stringFromFile);
        }

        whereToWrite.println(END_SENDING);
        inFromFile.close();
        fileReceived = true;
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
                case SEND_DATA:
                    sendFile(channelOutToServer, fileDati);
                    break;

                case SEND_MENU:
                    sendFile(channelOutToServer, fileMenu);
                    break;

                case SEND_COINS:
                    sendFile(channelOutToServer, fileMonete);
                    break;

                case SEND_STATS:
                    sendFile(channelOutToServer, fileStats);
                    break;

                default:
                    System.out.println("Not a valid command");
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
                command.equals(SEND_STATS)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFileReceived(){
        return fileReceived;
    }

    public void fileOpened(){
        fileReceived = false;
    }

}
