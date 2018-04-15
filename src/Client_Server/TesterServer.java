package Client_Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TesterServer {

    static PrintWriter outToClient; // Dati diretti al Client
    static BufferedReader inFromClient; // Dati in entrata
    static String stringFromClient; // Stringa letta dai dati in entrata
    static File fileMenu =
            new File("src/Client_Server/serverMenu.txt");
    static File fileStats =
            new File("src/Client_Server/serverStats.txt");

    //TODO Una funzione che mi permetta di switchare (forse proprio uno switch) tra la ricezione e l'invio di un file

    public static void main(String[] args) throws IOException{

        try{
            connectionPreRequisite(2222);
            emptyFile(fileStats);
            while ((stringFromClient = inFromClient.readLine()) != null) {
                writeFileReceived(fileStats);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Creo le basi per la connessione
     * @throws IOException
     */
    private static void connectionPreRequisite (int connectionPort) throws IOException{
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
     * Svuoto il file su cui poi scriverò
     * @param file
     * @throws IOException
     */
    private static void emptyFile(File file) throws IOException{
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
     * Scrivo il file arrivato da Client e reinvio una verifica (che in futuro portà essere tolta)
     * @param file
     * @throws IOException
     */
    private static void writeFileReceived(File file)throws IOException{ // And send another file
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file.getPath(), true); // Scrivo il file
            fileOutputStream.write((stringFromClient + "\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Invio di un file al Client
     * @param file
     * @throws IOException
     */
    private static void sendFile(File file)throws IOException{
        try {
            String stringFromFile;
            BufferedReader inFromFile =
                    new BufferedReader(
                            new FileReader(file.getPath())); // Oggetto da cui prendo i dati

            while ((stringFromFile = inFromFile.readLine()) != null) { // Invio al Client
                outToClient.println(stringFromFile);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
