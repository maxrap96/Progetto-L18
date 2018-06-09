package VendingMachineGUI;

import ClientSide.BooleanRefill;
import HotDrinkVendingMachine.HotDrinkVendMachine;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;

import java.io.File;

import static HotDrinkVendingMachine.TextPathFiles.MENU_PATH;

public class UpdateChecker extends Thread {
    private HotDrinkVendMachine vendMachine;
    private BeverageGrid beverageGrid;
    private BorderPane root;
    private Display display;
    private ResetDisplay resetDisplay;
    private File menuFile;
    private long oldTimestamp;
    private BooleanRefill booleanRefill;

    public UpdateChecker(HotDrinkVendMachine vendMachine, BeverageGrid beverageGrid, Display display,
                         ResetDisplay resetDisplay, BorderPane root, BooleanRefill booleanRefill) {
        this.vendMachine = vendMachine;
        this.beverageGrid = beverageGrid;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.root = root;
        this.booleanRefill = booleanRefill;
        this.menuFile = new File(MENU_PATH);
        this.oldTimestamp = menuFile.lastModified();
    }

    public void  run() {
        while(true) {
            if (isFileChanged()) {
                vendMachine = new HotDrinkVendMachine();
                beverageGrid = new BeverageGrid(vendMachine, display, resetDisplay);
                Platform.runLater(() -> {root.setLeft(beverageGrid);});
            }
            if (booleanRefill.isCoinsRefilled()) {
                booleanRefill.setCoinsRefilled(false);
                vendMachine = new HotDrinkVendMachine();
            }
            if (booleanRefill.isIngredientsRefilled()) {
                booleanRefill.setIngredientsRefilled(false);
                vendMachine = new HotDrinkVendMachine();
            }
            if (booleanRefill.isItemRefilled()) {
                booleanRefill.setItemRefilled(false);
                vendMachine = new HotDrinkVendMachine();
            }
        }
    }

    /**
     * Funzione che controlla se il file ha subito delle modifiche.
     */
    private boolean isFileChanged() {
        long currentTimestamp = menuFile.lastModified();
        if (oldTimestamp != currentTimestamp) {
            oldTimestamp = currentTimestamp;
            return true;
        }
        else {
            return false;
        }
    }
}
