package Client_Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TesterServer {

    public static void main(String[] args) throws IOException{
        File fileStats =
                new File("src/Client_Server/statsServer.txt");

        try{
            ServerSocket serverSocket =
                    new ServerSocket(2222); // Creo socket di benvenuto
            Socket clientSocket = serverSocket.accept(); // Accetto la connessione di un client
            PrintWriter outToClient =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere al Client
            BufferedReader inFromClient =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())); // Oggetto per leggere da Client
            PrintWriter empyFile =
                    new PrintWriter(fileStats.getPath());
            empyFile.write(""); // Svuoto il precedente file
            empyFile.close();

            String stringFromClient;
            while ((stringFromClient = inFromClient.readLine()) != null) {
                FileOutputStream fileOutputStream =
                        new FileOutputStream(fileStats.getPath(), true); // Scrivo il file
                fileOutputStream.write((stringFromClient + "\n").getBytes());
                outToClient.println(stringFromClient + " Server");  // Reinvio di file, questo caso é un esempio per
                                                                    // vedere se funziona
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
