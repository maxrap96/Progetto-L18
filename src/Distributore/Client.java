package Distributore;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Client extends Thread{

    static String stringFromServer; // Stringa in ingresso dal Server
    static PrintWriter outToServer; // Dati diretti al Server
    static BufferedReader inFromServer; // Dati in entrata
    static File fileMenuClient =
            new File("src/File_Testo/menu.txt");
    static File fileStatsClient =
            new File("src/File_Testo/stats.txt");

    public void run(){
        try {
            startClient();
        } catch (ConnectException ce){
            ce.printStackTrace();
        }
    }

    /**
     * Avvia la comunicazione al server
     */
    private static void startClient() throws ConnectException {
        try {
            if(connectionPreRequisite("localhost",2222)) {   // Da modificare se voglio
                // cambiare le impostazioni di connessione
                emptyFile(fileMenuClient);
                sendFile(outToServer, fileStatsClient);

                outToServer.println("SEND");

                while ((stringFromServer = inFromServer.readLine()) != null) { // Ricevo dal Server
                    writeFileReceived(stringFromServer, fileMenuClient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creo le basi per la connessione Distributore.Client
     * @throws IOException
     */
    protected static boolean connectionPreRequisite (String hostName, int connectionPort) throws IOException{
        try{
            Socket clientSocket =
                    new Socket(hostName, connectionPort); // Creo il socket attraverso cui inviare i dati

            outToServer =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere

            inFromServer =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Oggetto per ricevere

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Svuoto il file
     * @param file file svuotato
     * @throws IOException
     */
    protected static void emptyFile(File file) throws IOException{
        try {
            PrintWriter emptyFile = new PrintWriter(file.getPath());

            emptyFile.write(""); // Svuoto il file
            emptyFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Scrivo il file ricevuto
     * @param stringToWrite
     * @param file dove salvo ciò che arriva
     * @throws IOException
     */
    protected static void writeFileReceived(String stringToWrite, File file)throws IOException{
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath(), true); // Scrivo il file

            fileOutputStream.write((stringToWrite + "\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Invio di un file al Distributore.Client
     * @param file file da inviare
     * @param whereToWrite mezzo attraverso cui invio il file
     * @throws IOException
     */
    protected static void sendFile(PrintWriter whereToWrite, File file)throws IOException{
        try {
            String stringFromFile;
            BufferedReader inFromFile =
                    new BufferedReader(new FileReader(file.getPath())); // Oggetto da cui prendo i dati

            while ((stringFromFile = inFromFile.readLine()) != null) { // Invio al Distributore.Client
                whereToWrite.println(stringFromFile);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
