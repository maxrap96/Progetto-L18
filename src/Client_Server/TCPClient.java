package Client_Server;

import java.io.*;
import java.net.*;

/**
 *
 */

public class TCPClient {

    private String hostName;
    private int portNumber;
    private String pathFile;
    private Socket clientSocket;
    private PrintWriter outToServer;
    private BufferedReader inFromServer;
    private BufferedReader inFromFile;

    public TCPClient(String hostName, int port, String path) throws IOException{
        this.hostName = hostName;
        this.portNumber = port;
        this.pathFile = path;
        this.createSocket();
    }

    private void createSocket() throws IOException{
        try {
            this.clientSocket =
                    new Socket(hostName, portNumber); // Creo il socket attraverso cui inviare i dati
            this.outToServer =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere
            this.inFromServer =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream())); // Oggetto per ricevere
            this.inFromFile =
                    new BufferedReader(
                            new FileReader(this.pathFile)); // Oggetto da cui prendo i dati
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void sendFile() throws IOException{
        String stringFromFile;
        try {
            while ((stringFromFile = inFromFile.readLine()) != null) {
                outToServer.println(stringFromFile);
                System.out.println("OK");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void closeAll() throws  IOException{
        try {
            this.clientSocket.close();
            this.outToServer.close();
            this.inFromServer.close();
            this.inFromFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}