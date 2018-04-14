package Client_Server;

import java.io.File;
import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException{
        try {

        File fileMenu = new File("src/File_Testo/menu.txt");
        TCPClient tcpClient = new TCPClient("localhost", 2222, fileMenu.getPath());

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
