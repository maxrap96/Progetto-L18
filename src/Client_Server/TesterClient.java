package Client_Server;

import java.io.*;
import java.net.Socket;

public class TesterClient {

    static PrintWriter outToServer; // Dati diretti al Server
    static BufferedReader inFromServer; // Dati in entrata
    static String stringFromServer;
    static File fileMenu =
            new File("src/File_Testo/menu.txt");
    static File fileStats =
            new File("src/File_Testo/stats.txt");

    public static void main(String[] args) throws IOException{

        try {
            connectionPreRequisite("localhost",2222);
            // emptyFile(fileMenu);

            String stringFromFile;
            BufferedReader inFromFile =
                    new BufferedReader(
                            new FileReader(fileStats.getPath())); // Oggetto da cui prendo i dati

            while ((stringFromFile = inFromFile.readLine()) != null) { // Invio al Server
                outToServer.println(stringFromFile);
            }

            while((stringFromServer = inFromServer.readLine()) != null){ // Ricevo dal Server
                writeFileReceived(fileMenu);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Creo le basi per la connessione
     * @throws IOException
     */
    private static void connectionPreRequisite (String hostName, int connectionPort) throws IOException{
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
     * Svuoto il file su cui poi scriverò
     * @param file
     * @throws IOException
     */
    private static void emptyFile(File file) throws IOException{
        try {
            PrintWriter emptyFile = new PrintWriter(file.getPath());
            emptyFile.write(""); // Svuoto il file
            emptyFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Scrivo il file arrivato da Client e reinvio una verifica (che in futuro portà essere tolta)
     * @param file
     * @throws IOException
     */
    private static void writeFileReceived(File file)throws IOException{
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file.getPath(), true); // Scrivo il file
            fileOutputStream.write((stringFromServer + "\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
