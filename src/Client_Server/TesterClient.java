package Client_Server;

import java.io.*;
import java.net.Socket;

public class TesterClient {

    static PrintWriter outToServer; // Dati diretti al Server
    static BufferedReader inFromServer; // Dati in entrata
    static File fileMenu =
            new File("src/Client_Server/serverMenu.txt");
    static File fileStats =
            new File("src/Client_Server/serverStats.txt");

    public static void main(String[] args) throws IOException{

        try {
            connectionPreRequisite("localhost",2222);

            String stringFromFile;
            BufferedReader inFromFile =
                    new BufferedReader(
                            new FileReader(fileStats.getPath())); // Oggetto da cui prendo i dati

            while ((stringFromFile = inFromFile.readLine()) != null) { // Invio al Server
                outToServer.println(stringFromFile);
            }

            String stringFromServer;
            while((stringFromServer = inFromServer.readLine()) != null){ // Ricevo dal Server
                System.out.println(stringFromServer);
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
}
