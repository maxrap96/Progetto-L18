package Client_Server;

import java.io.*;

public class ServerSide extends Connection{

    static String stringFromClient; // Stringa letta dai dati in entrata
    static File fileMenuServer =
            new File("src/Client_Server/serverMenu.txt");
    static File fileStatsServer =
            new File("src/Client_Server/serverStats.txt");

    public static void main(String[] args) throws IOException{

        try{
            connectionPreRequisite(2222);
            emptyFile(fileStatsServer);
            while ((stringFromClient = inFromClient.readLine()) != null) {
                if (!stringFromClient.equals("SEND")) {
                    writeFileReceived(stringFromClient, fileStatsServer);
                }else {
                    sendFile(outToClient, fileMenuServer);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}