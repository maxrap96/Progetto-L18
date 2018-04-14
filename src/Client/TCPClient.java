package Client;

import java.io.*;
import java.net.*;

/**
 * In questo esempio il client manda sentence e il server rimanda indietro la scritta in CAPS
 */

public class TCPClient {
    public static void main(String argv[]) throws Exception {

        File file = new File("src/File_Testo/menu.txt");
        String sentece;
        String modifiedSentence;

        Socket clientSocket = new Socket("localhost", 2222);
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn =
                new BufferedReader(
                        new FileReader(file));

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while ((sentece = stdIn.readLine()) != null) {
            out.println(sentece);
            System.out.println("echo: " + in.readLine());
        }

        /*outToServer.writeBytes(sentece + "\n");
        modifiedSentence = inFromServer.readLine();
        System.out.println(modifiedSentence);*/


        clientSocket.close();
        stdIn.close();
        inFromServer.close();
        outToServer.close();
    }
}