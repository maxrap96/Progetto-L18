package Client_Server;

import java.io.*;
import java.net.Socket;

public class TesterClient {
    public static void main(String[] args) throws IOException{
        File fileMenu =
                new File("src/File_Testo/menu.txt");
        File fileStats =
                new File("src/File_Testo/stats.txt");

        try {
            Socket clientSocket =
                    new Socket("localhost", 2222); // Creo il socket attraverso cui inviare i dati
            PrintWriter outToServer =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere
            BufferedReader inFromServer =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())); // Oggetto per ricevere

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
}
