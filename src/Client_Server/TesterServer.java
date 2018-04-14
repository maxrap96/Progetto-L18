package Client_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TesterServer {
    public static void main(String[] args) throws IOException{

        try{
            ServerSocket serverSocket =
                    new ServerSocket(2222); // Creo socket di benvenuto
            Socket clientSocket = serverSocket.accept(); // Accetto la connessione di un client
            PrintWriter outToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere al Client
            BufferedReader inFromClient =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())); // Oggetto per leggere da Client

            String stringFromClient;
            while ((stringFromClient = inFromClient.readLine()) != null) {
                System.out.println(stringFromClient);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
