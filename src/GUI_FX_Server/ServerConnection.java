package GUI_FX_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection implements Runnable {

    static private String stringToUpdateSomething;
    private int portNumber;

    public ServerConnection(int port) {
        this.portNumber = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber); // Creo socket di benvenuto
            while(true) {
                Socket clientSocket = serverSocket.accept(); // Accetto la connessione di un client
                BufferedReader inFromClient =
                        new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream())); // Oggetto per leggere da Client
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error" + e);
        }
    }

    private void sendString(String sendThisString, Socket client) throws IOException{
        PrintWriter outToClient =
                new PrintWriter(client.getOutputStream(), true); // Oggetto per scrivere al Client
        outToClient.println(sendThisString);
    }
}
