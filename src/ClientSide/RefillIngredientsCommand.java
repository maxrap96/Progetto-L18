package ClientSide;

import java.io.IOException;

public class RefillIngredientsCommand implements Command {
    private ReceiverRefill receiverRefill;

    public RefillIngredientsCommand(ReceiverRefill receiverRefill) {
        this.receiverRefill = receiverRefill;
    }

    @Override
    public void execute() {
        try {
            receiverRefill.refillBeverage();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
