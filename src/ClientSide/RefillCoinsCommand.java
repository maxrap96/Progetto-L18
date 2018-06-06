package ClientSide;

public class RefillCoinsCommand implements Command {
    private ReceiverRefill receiverRefill;
    private BooleanRefill booleanRefill;

    public RefillCoinsCommand(ReceiverRefill receiverRefill, BooleanRefill booleanRefill) {
        this.receiverRefill = receiverRefill;
        this.booleanRefill = booleanRefill;
    }

    @Override
    public void execute() {
        this.receiverRefill.refillCoins();
        this.booleanRefill.setCoinsRefilled(true);
    }
}
