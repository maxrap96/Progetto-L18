package GUI_FX_VendingMachine;

import Distributore.Distributore;
import ClientSide.ClientVendMach;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

import java.io.File;

import static Distributore.TextFiles.MENUPATH;

public class UpdateChecker extends Thread{
    private Distributore distributore;
    private BeverageGrid beverageGrid;
    private BorderPane root;
    private Display display;
    private ResetDisplay resetDisplay;
    private File menuFile = new File(MENUPATH);
    private long oldTimestamp = menuFile.lastModified();

    public UpdateChecker(Distributore distributore, BeverageGrid beverageGrid, Display display,
                         ResetDisplay resetDisplay, BorderPane root) {
        this.distributore = distributore;
        this.beverageGrid = beverageGrid;
        this.display = display;
        this.resetDisplay = resetDisplay;
        this.root = root;
    }

    private boolean isFileChanced() {
        long currentTimestamp = menuFile.lastModified();
        if (oldTimestamp != currentTimestamp){
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
                distributore = new Distributore();
                beverageGrid = new BeverageGrid(distributore, display, resetDisplay);
                Platform.runLater(() -> {root.setLeft(beverageGrid);});
            }
        }
    }

}
