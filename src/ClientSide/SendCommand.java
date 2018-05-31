package ClientSide;

import Distributore.Command;

import java.io.PrintWriter;

public abstract class SendCommand implements Command {
    protected ReceiverSend receiverSend;
    protected PrintWriter printWriter;

    public SendCommand(ReceiverSend receiverSend, PrintWriter printWriter) {
        this.receiverSend = receiverSend;
        this.printWriter = printWriter;
    }
}
