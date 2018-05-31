package GUI_FX_VendingMachine;

import Distributore.Distributore;
import ClientSide.ClientVendMach;

public class UpdateChecker extends Thread {
    private Distributore distributore;
    private BeverageGrid beverageGrid;
    private Display display;
    private ResetDisplay resetDisplay;
    private ClientVendMach clientVendMach;

    public UpdateChecker(Distributore distributore, BeverageGrid beverageGrid, Display display,
                         ResetDisplay resetDisplay, ClientVendMach clientVendMach) {
        this.distributore = distributore;
        this.beverageGrid = beverageGrid;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.clientVendMach = clientVendMach;
        // Impostazione del thread per avviarsi solo quando un file è stato ricevuto
        this.setDaemon(clientVendMach.isFileReceived());
    }

    public void  run() {
        System.out.println("Updating");
        distributore = new Distributore();
        beverageGrid = new BeverageGrid(distributore, display, resetDisplay);
        clientVendMach.fileOpened();
    }
}
