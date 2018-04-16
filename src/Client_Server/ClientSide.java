package Client_Server;

import java.io.*;

public class ClientSide extends Connection{

    static String stringFromServer;
    static File fileMenuClient =
            new File("src/File_Testo/menu.txt");
    static File fileStatsClient =
            new File("src/File_Testo/stats.txt");

    public static void main(String[] args) throws IOException{

        try {
            connectionPreRequisite("localhost",2222);
            emptyFile(fileMenuClient);
            sendFile(outToServer, fileStatsClient);

            outToServer.println("SEND");

            while((stringFromServer = inFromServer.readLine()) != null){ // Ricevo dal Server
                writeFileReceived(stringFromServer, fileMenuClient);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
