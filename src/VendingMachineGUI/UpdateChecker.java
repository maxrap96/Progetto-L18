package VendingMachineGUI;

import HotDrinkVendingMachine.HotDrinkVendMachine;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;

import java.io.File;

import static HotDrinkVendingMachine.TextPathFiles.MENU_PATH;

public class UpdateChecker extends Thread{
    private HotDrinkVendMachine vendMachine;
    private BeverageGrid beverageGrid;
    private BorderPane root;
    private Display display;
    private ResetDisplay resetDisplay;
    private File menuFile = new File(MENU_PATH);
    private long oldTimestamp = menuFile.lastModified();

    public UpdateChecker(HotDrinkVendMachine vendMachine, BeverageGrid beverageGrid, Display display,
                         ResetDisplay resetDisplay, BorderPane root) {
        this.vendMachine = vendMachine;
        this.beverageGrid = beverageGrid;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.root = root;
    }

    private boolean isFileChanced() {
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
            if (isFileChanced()) {
                System.out.println("Updating");
                vendMachine = new HotDrinkVendMachine();
                beverageGrid = new BeverageGrid(vendMachine, display, resetDisplay);
                Platform.runLater(() -> {root.setLeft(beverageGrid);});
            }
        }
    }
}
