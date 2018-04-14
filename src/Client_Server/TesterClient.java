package Client_Server;

import java.io.*;
import java.net.Socket;

public class TesterClient {
    public static void main(String[] args) throws IOException{
        File fileMenu = new File("src/File_Testo/menu.txt");
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
                            new FileReader(fileMenu.getPath())); // Oggetto da cui prendo i dati

            while ((stringFromFile = inFromFile.readLine()) != null) {
                outToServer.println(stringFromFile);
                System.out.println("Client sent succesfully");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
