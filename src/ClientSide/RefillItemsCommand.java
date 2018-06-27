package ClientSide;

import java.io.IOException;

/**
 * Classe che si occupa della ricarica degli oggetti.
 */

public class RefillItemsCommand implements Command {
    private ReceiverRefill receiverRefill;
    private BooleanRefill booleanRefill;

    public RefillItemsCommand(ReceiverRefill receiverRefill, BooleanRefill refilled) {
        this.receiverRefill = receiverRefill;
        this.booleanRefill = refilled;
    }

    /**
     * Funzione che esegue i comandi di ricarica del receiver appropriato.
     */
    @Override
    public void execute() {
        try {
            this.receiverRefill.refillItems();
            this.booleanRefill.setItemRefilled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
