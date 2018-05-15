package GUI_FX_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection extends Thread {

    private ArrayList<String> stats;
    private int portNumber;
    private Socket clientSocket;

    public ServerConnection(int port) {
        this.portNumber = port;
        stats = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            // Creazione socket di benvenuto
            ServerSocket serverSocket = new ServerSocket(portNumber);

            // Ciclo che consente la connessione a più Client
            while (true) {

                // Accetto la connessione di un client
                clientSocket = serverSocket.accept();

                // Creo il thread per ogni Client che si connette
                new DealWithTheClientThread(clientSocket, stats).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error caught: " + e);
        }
    }

}
