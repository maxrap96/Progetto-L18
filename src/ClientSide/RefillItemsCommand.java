package ClientSide;

import java.io.IOException;

public class RefillItemsCommand implements Command {
    private ReceiverRefill receiverRefill;
    private BooleanRefill booleanRefill;

    public RefillItemsCommand(ReceiverRefill receiverRefill, BooleanRefill refilled) {
        this.receiverRefill = receiverRefill;
        this.booleanRefill = refilled;
    }

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
