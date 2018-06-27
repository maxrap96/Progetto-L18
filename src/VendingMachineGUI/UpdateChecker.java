package VendingMachineGUI;

import ClientSide.BooleanRefill;
import HotDrinkVendingMachine.HotDrinkVendMachine;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

import java.io.File;

import static HotDrinkVendingMachine.TextPathFiles.MENU_PATH;

/**
 * Classe che aggiorna gli elementi che compongono la GUI quando il menù viene modificato o quando gli
 * ingredienti, le monete o gli items vengono ricaricati da server.
 */

public class UpdateChecker extends Thread {
    private HotDrinkVendMachine vendMachine;
    private BeverageGrid beverageGrid;
    private BorderPane root;
    private Display display;
    private ResetDisplay resetDisplay;
    private File menuFile;
    private long oldTimestamp;
    private BooleanRefill booleanRefill;
    private BackgroundImage backgroundImage;
    private PurchasePane purchasePane;

    public UpdateChecker(HotDrinkVendMachine vendMachine, BeverageGrid beverageGrid, Display display,
                         ResetDisplay resetDisplay, BorderPane root, BooleanRefill booleanRefill,
                         BackgroundImage backgroundImage, PurchasePane purchasePane) {
        this.vendMachine = vendMachine;
        this.beverageGrid = beverageGrid;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.root = root;
        this.booleanRefill = booleanRefill;
        this.menuFile = new File(MENU_PATH);
        this.oldTimestamp = menuFile.lastModified();
        this.backgroundImage = backgroundImage;
        this.purchasePane = purchasePane;
    }

    /**
     * Funzione che aggiorna la GUI dopo eventuali modifiche da server.
     */
    public void  run() {
        while(true) {
            if (isFileChanged()) {
                vendMachine = new HotDrinkVendMachine();
                Platform.runLater(() -> {
                    beverageGrid = new BeverageGrid(vendMachine, display, resetDisplay);
                    beverageGrid.setBackground(new Background(backgroundImage));
                    root.setLeft(beverageGrid);});
                Platform.runLater(() -> {
                    purchasePane = new PurchasePane(display, resetDisplay, vendMachine);
                    root.setRight(purchasePane);});
            }
            if (booleanRefill.isCoinsRefilled()) {
                booleanRefill.setCoinsRefilled(false);
                Platform.runLater(() -> {vendMachine = new HotDrinkVendMachine();});
            }
            if (booleanRefill.isIngredientsRefilled()) {
                booleanRefill.setIngredientsRefilled(false);
                Platform.runLater(() -> {vendMachine = new HotDrinkVendMachine();});
            }
            if (booleanRefill.isItemRefilled()) {
                booleanRefill.setItemRefilled(false);
                Platform.runLater(() -> {vendMachine = new HotDrinkVendMachine();});
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
