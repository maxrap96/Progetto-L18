package InterfacciaDistributore;

import java.util.*;
import InterfacciaDistributore.VendingMachine;

public class MyTask extends TimerTask {
    VendingMachine v = new VendingMachine();
    public void run() {
        v.getDisplay().setText("ciao");
    }
}
