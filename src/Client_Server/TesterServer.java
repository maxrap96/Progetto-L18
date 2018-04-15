package Client_Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TesterServer {

    static PrintWriter outToClient; // Stringa diretta al Client
    static BufferedReader inFromClient; // Dati in entrata
    static String stringFromClient; // Stringa letta dai dati in entrata

    //TODO Una funzione che mi permetta di switchare (forse proprio uno switch) tra la ricezione e l'invio di un file

    public static void main(String[] args) throws IOException{
        File fileMenu =
                new File("src/File_Testo/menu.txt");
        File fileStats =
                new File("src/Client_Server/statsServer.txt");
        try{
            connectionPreRequisite();
            emptyFile(fileStats);
            while ((stringFromClient = inFromClient.readLine()) != null) {
                writeAndSendCheck(fileStats);
                }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Creo le basi per la connessione
     * @throws IOException
     */
    private static void connectionPreRequisite () throws IOException{
        try{
            ServerSocket serverSocket =
                    new ServerSocket(2222); // Creo socket di benvenuto
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
            PrintWriter empyFile =
                    new PrintWriter(file.getPath());
            empyFile.write(""); // Svuoto il file
            empyFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Scrivo il file arrivato da Client e reinvio una verifica (che in futuro portà essere tolta)
     * @param file
     * @throws IOException
     */
    private static void writeAndSendCheck(File file)throws IOException{
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file.getPath(), true); // Scrivo il file
            fileOutputStream.write((stringFromClient + "\n").getBytes());
            outToClient.println(stringFromClient + " Server");  // Reinvio di file, questo caso é un esempio per
                                                                // vedere se funziona
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
