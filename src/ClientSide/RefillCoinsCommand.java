package ClientSide;

public class RefillCoinsCommand implements Command {
    private ReceiverRefill receiverRefill;

    public RefillCoinsCommand(ReceiverRefill receiverRefill) {
        this.receiverRefill = receiverRefill;
    }

    @Override
    public void execute() {
        receiverRefill.refillCoins();
    }
}
