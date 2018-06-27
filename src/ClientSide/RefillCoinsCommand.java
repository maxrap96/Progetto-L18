package ClientSide;

/**
 * Classe che si occupa della ricarica delle monete.
 */

public class RefillCoinsCommand implements Command {
    private ReceiverRefill receiverRefill;
    private BooleanRefill booleanRefill;

    public RefillCoinsCommand(ReceiverRefill receiverRefill, BooleanRefill booleanRefill) {
        this.receiverRefill = receiverRefill;
        this.booleanRefill = booleanRefill;
    }

    /**
     * Funzione che esegue i comandi di ricarica del receiver appropriato.
     */
    @Override
    public void execute() {
        this.receiverRefill.refillCoins();
        this.booleanRefill.setCoinsRefilled(true);
    }
}
