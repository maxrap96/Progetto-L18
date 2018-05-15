package GUI_FX_VendingMachine;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Client extends Thread implements FileClient{

    static String stringFromServer; // Stringa in ingresso dal Server
    static PrintWriter outToServer; // Dati diretti al Server
    static BufferedReader inFromServer; // Dati in entrata
    private boolean fileReceived = false;


    public void run(){
        try {
            startClient();
        } catch (ConnectException ce){
            ce.printStackTrace();
        }
    }

    /**
     * Avvia la comunicazione al server,
     */
    private static void startClient() throws ConnectException {
        try {
            if(connectionPreRequisite("localhost",2222)) {   // Da modificare se voglio
                // cambiare le impostazioni di connessione
                emptyFile(fileMenu);
                sendFile(outToServer, fileStats);

                outToServer.println("SEND");

                while ((stringFromServer = inFromServer.readLine()) != null) { // Ricevo dal Server
                    writeFileReceived(stringFromServer, fileMenu);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creo le basi per la connessione Distributore.
     */
    protected static boolean connectionPreRequisite (String hostName, int connectionPort) throws IOException{
        try{
            Socket clientSocket =
                    new Socket(hostName, connectionPort); // Creo il socket attraverso cui inviare i dati

            outToServer =
                    new PrintWriter(clientSocket.getOutputStream(), true); // Oggetto per scrivere

            inFromServer =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Oggetto per ricevere

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Funzione per cancellare il contenuto di un file.
     *
     * @param file file da svuotare.
     * @throws IOException
     */
    protected static void emptyFile(File file) throws IOException{
        try {
            PrintWriter emptyFile = new PrintWriter(file.getPath());

            emptyFile.write(""); // Svuoto il file
            emptyFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Scrivo il file ricevuto.
     *
     * @param stringToWrite stringa da scrivere.
     * @param file dove salvo ciò che arriva.
     * @throws IOException
     */
    protected static void writeFileReceived(String stringToWrite, File file)throws IOException{
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath(), true); // Scrivo il file

            fileOutputStream.write((stringToWrite + "\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Invio di un file al Distributore.
     *
     * @param file file da inviare.
     * @param whereToWrite mezzo attraverso cui invio il file.
     * @throws IOException
     */
    protected static void sendFile(PrintWriter whereToWrite, File file)throws IOException{
        try {
            String stringFromFile;
            BufferedReader inFromFile =
                    new BufferedReader(new FileReader(file.getPath())); // Oggetto da cui prendo i dati

            while ((stringFromFile = inFromFile.readLine()) != null) { // Invio al GUI_FX_VendingMachine.Client
                whereToWrite.println(stringFromFile);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isFileReceived() {
        return fileReceived;
    }

    public void FileReloaded(){
        fileReceived = false; //significa che io in questo momento ho letto il file.
    }
}
