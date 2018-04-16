package Client_Server;

import java.io.*;
import java.net.Socket;

public class ClientSide extends Thread{

    static String stringFromServer;
    static PrintWriter outToServer; // Dati diretti al Server
    static BufferedReader inFromServer; // Dati in entrata
    static File fileMenuClient =
            new File("src/File_Testo/menu.txt");
    static File fileStatsClient =
            new File("src/File_Testo/stats.txt");

    @Override
    public void run() {
        try {
            connectionPreRequisite("localhost",2222);
            emptyFile(fileMenuClient);
            sendFile(outToServer, fileStatsClient);

            outToServer.println("SEND");

            while((stringFromServer = inFromServer.readLine()) != null){ // Ricevo dal Server
                writeFileReceived(stringFromServer, fileMenuClient);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Creo le basi per la connessione Client
     * @throws IOException
     */
    protected static void connectionPreRequisite (String hostName, int connectionPort) throws IOException{
        try{
            Socket clientSocket =
                    new Socket(hostName, connectionPort); // Creo il socket attraverso cui inviare i dati
            outToServer =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere
            inFromServer =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())); // Oggetto per ricevere

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Svuoto il file
     * @param file file svuotato
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
     * Scrivo il file ricevuto
     * @param stringToWrite
     * @param file dove salvo ciò che arriva
     * @throws IOException
     */
    protected static void writeFileReceived(String stringToWrite, File file)throws IOException{
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file.getPath(), true); // Scrivo il file
            fileOutputStream.write((stringToWrite + "\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Invio di un file al Client
     * @param file
     * @param whereToWrite
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
