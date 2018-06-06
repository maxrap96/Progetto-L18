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
    private File menuFile = new File(MENU_PATH);
    private long oldTimestamp = menuFile.lastModified();
    private BooleanRefill booleanRefill;

    public UpdateChecker(HotDrinkVendMachine vendMachine, BeverageGrid beverageGrid, Display display,
                         ResetDisplay resetDisplay, BorderPane root, BooleanRefill booleanRefill) {
        this.vendMachine = vendMachine;
        this.beverageGrid = beverageGrid;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.root = root;
        this.booleanRefill = booleanRefill;
    }

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
            if (booleanRefill.isBeverageRefilled()) {
                booleanRefill.setBeverageRefilled(false);
                vendMachine = new HotDrinkVendMachine();
            }
            if (booleanRefill.isItemRefilled()) {
                booleanRefill.setItemRefilled(false);
                vendMachine = new HotDrinkVendMachine();
            }
        }
    }
}
