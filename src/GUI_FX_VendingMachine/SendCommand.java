package GUI_FX_VendingMachine;

import java.io.PrintWriter;

public abstract class SendCommand implements Command{

    protected Receiver receiver;
    protected PrintWriter printWriter;

    public SendCommand(Receiver receiver, PrintWriter printWriter) {
        this.receiver = receiver;
        this.printWriter = printWriter;
    }
}
