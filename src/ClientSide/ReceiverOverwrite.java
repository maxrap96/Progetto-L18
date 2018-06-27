package ClientSide;

import HotDrinkVendingMachine.FileManager;

import java.util.ArrayList;

/**
 * Classe receiver per il comando di sovrascrittura.
 */

public class ReceiverOverwrite {
    private FileManager menuData;

    /**
     * Funzione che sovrascrive il file ricevuto.
     * @param arrayFromCommand array passato dal comando.
     * @param path percorso del file da sorascrivere.
     */
    protected void overwriteFileReceiver(ArrayList<String> arrayFromCommand, String path) {
        this.menuData = new FileManager(path);
        saveFileReceived(arrayFromCommand);
    }

    /**
     * Funzione che salva l'array passato.
     * @param arrayFromCommand array da salvare.
     */
    private void saveFileReceived(ArrayList<String> arrayFromCommand) {
        menuData.saveFileFromCommand(arrayFromCommand);
    }
}
