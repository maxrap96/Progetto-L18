package ClientSide;

import java.io.IOException;

public class RefillIngredientsCommand implements Command {
    private ReceiverRefill receiverRefill;
    private BooleanRefill booleanRefill;

    public RefillIngredientsCommand(ReceiverRefill receiverRefill, BooleanRefill refilled) {
        this.receiverRefill = receiverRefill;
        this.booleanRefill = refilled;
    }

    @Override
    public void execute() {
        try {
            this.receiverRefill.refillBeverage();
            this.booleanRefill.setBeverageRefilled(true);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
