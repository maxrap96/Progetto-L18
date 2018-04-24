package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

    static String stringFromClient; // Stringa letta dai dati in entrata
    static PrintWriter outToClient; // Dati diretti al Client
    static BufferedReader inFromClient; // Dati in entrata
    static File fileMenuServer =
            new File("src/FIle_Testo_Server/serverMenu.txt");
    static File fileStatsServer =
            new File("src/FIle_Testo_Server/serverStats.txt");

    public static void main(String[] args){
        try{
            connectionPreRequisite(2222);
            emptyFile(fileStatsServer);
            while ((stringFromClient = inFromClient.readLine()) != null) {
                if (!stringFromClient.equals("SEND")) {
                    writeFileReceived(stringFromClient, fileStatsServer);
                }else {
                    sendFile(outToClient, fileMenuServer);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Creo le basi per la connessione Server.
     * @throws IOException
     */
    protected static void connectionPreRequisite (int connectionPort) throws IOException{
        try{
            ServerSocket serverSocket =
                    new ServerSocket(connectionPort); // Creo socket di benvenuto
            Socket clientSocket = serverSocket.accept(); // Accetto la connessione di un client
            outToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere al Client
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())); // Oggetto per leggere da Client
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Svuoto il file.
     *
     * @param file il file da svuotare.
     * @throws IOException
     */
    protected static void emptyFile(File file) throws IOException{
        try {
            PrintWriter emptyFile =
                    new PrintWriter(file.getPath());
            emptyFile.write(""); // Svuoto il file
            emptyFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Scrivo il file ricevuto.
     *
     * @param stringToWrite stringa da scrivere su file.
     * @param file dove salvo ciò che arriva.
     * @throws IOException
     */
    protected static void writeFileReceived(String stringToWrite, File file)throws IOException{
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file.getPath(), true); // Scrivo il file
            fileOutputStream.write((stringToWrite + "\n").getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Invio di un file al Client.
     *
     * @param file da inviare al Client.
     * @param whereToWrite PrintWriter impiegato per inviare il file.
     * @throws IOException
     */
    protected static void sendFile(PrintWriter whereToWrite, File file)throws IOException{
        try {
            String stringFromFile;
            BufferedReader inFromFile =
                    new BufferedReader(
                            new FileReader(file.getPath())); // Oggetto da cui prendo i dati

            while ((stringFromFile = inFromFile.readLine()) != null) { // Invio al Client
                whereToWrite.println(stringFromFile);
            }
            inFromFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}