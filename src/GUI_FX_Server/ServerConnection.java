package GUI_FX_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection implements Runnable {

    static private PrintWriter outToClient;
    static private BufferedReader inFromClient;
    static private ServerSocket serverSocket;
    static private String stringToUpdateSomething;
    static private Socket clientSocket;
    private int portNumber;

    public ServerConnection(int port) {
        this.portNumber = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber); // Creo socket di benvenuto
            while(true) {
                clientSocket = serverSocket.accept(); // Accetto la connessione di un client
                inFromClient =
                        new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream())); // Oggetto per leggere da Client
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error" + e);
        }
    }

    private void sendString(String sendThisString) throws IOException{
        outToClient =
                new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere al Client
        outToClient.println(sendThisString);
    }
}
