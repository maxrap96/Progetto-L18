package Client_Server;

import java.io.*;
import java.net.*;

/**
 *
 */

public class TCPServer {

    private int portNumber;

    public TCPServer(int port) {
        this.portNumber = port;
    }

    /*try (
                ServerSocket serverSocket =
                        new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter outToClient =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                outToClient.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }*/
}
