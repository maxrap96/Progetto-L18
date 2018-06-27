package ClientSide;

import java.io.IOException;

/**
 * Classe che si occupa della ricarica degli ingredienti.
 */

public class RefillIngredientsCommand implements Command {
    private ReceiverRefill receiverRefill;
    private BooleanRefill booleanRefill;

    public RefillIngredientsCommand(ReceiverRefill receiverRefill, BooleanRefill refilled) {
        this.receiverRefill = receiverRefill;
        this.booleanRefill = refilled;
    }

    /**
     * Funzione che esegue i comandi di ricarica del receiver appropriato.
     */
    @Override
    public void execute() {
        try {
            this.receiverRefill.refillBeverage();
            this.receiverRefill.compareMenuAndData();
            this.booleanRefill.setIngredientsRefilled(true);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
