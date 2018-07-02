package ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Classe che si occupa della creazione della connessione lato server.
 */

public class ServerConnection extends Thread {
    private ClassOfObservableLists classOfObservableLists;
    private HashMap<Integer, DealWithTheClientThread> mapOfClient;
    private int index;
    private int selectedClient;
    private int portNumber;
    private Socket clientSocket;

    public ServerConnection(int port, ClassOfObservableLists classOfObservableLists) {
        this.portNumber = port;
        this.classOfObservableLists = classOfObservableLists;
        this.mapOfClient = new HashMap<>();
        this.index = 0;
        this.selectedClient = 0;
    }

    /**
     * Funzione che avvia la connesione.
     */
    @Override
    public void run() {
        DealWithTheClientThread threadTmp;
        try {
            // Creazione socket di benvenuto
            ServerSocket serverSocket = new ServerSocket(portNumber);

            // Ciclo che consente la connessione a più client
            while (true) {
                // Accetta la connessione di un client
                this.clientSocket = serverSocket.accept();

                // Creazione del thread per ogni client che si connette
                threadTmp = new DealWithTheClientThread(clientSocket, classOfObservableLists);
                this.mapOfClient.put(index, threadTmp);
                threadTmp.start();
                this.index++;
                System.out.println(index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per scegliere il comando da eseguire e su quale client.
     * @param command comando da eseguire.
     */
    public void chooseCommandExecutedByThread(String command) {
        this.mapOfClient.get(selectedClient).chosenCommand(command);
    }

    /**
     * Funzione che permette di selezionare il client a cui inviare i comandi.
     * @param selectedClient valore intero del client.
     */
    public void setSelectedClient(int selectedClient) {
        if (mapOfClient.containsKey(selectedClient)) {
            this.selectedClient = selectedClient;
        } else {
            this.selectedClient = 0;
        }
    }

    /**
     * Funzione che restituisce il numero di client connessi.
     *
     * Nota: da usare nella UI per mostrare quanti distributori sono connessi.
     */
    public int getIndex() {
        return index;
    }
}
