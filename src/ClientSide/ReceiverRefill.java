package ClientSide;

import HotDrinkVendingMachine.CoinsNumbers;
import HotDrinkVendingMachine.Data;
import HotDrinkVendingMachine.MaxValue;
import HotDrinkVendingMachine.TextPathFiles;

import java.io.IOException;
import java.util.ArrayList;

public class ReceiverRefill implements MaxValue, TextPathFiles, CoinsNumbers {
    private Data menuFile;
    private Data beverageFile;
    private Data coinsFile;
    private ArrayList<String[]> menu;
    private final String[] ITEMS = {"Milk", "Sugar", "Spoons", "Cups", "Vodka"};
    private final double[] MAX_VAL = {MILK_MAX, SUGAR_MAX, SPOON_MAX, CUP_MAX, VODKA_MAX};

    public ReceiverRefill() {
        this.menuFile = new Data(MENU_PATH);
        this.beverageFile = new Data(DATA_PATH);
        this.coinsFile = new Data(COINS_PATH);
        this.menu = menuFile.readFile();
    }

    /**
     * Funzione che "ricarica" le quantità massima di Data.txt
     */
    protected void refillItems() throws IOException {
        ArrayList<String> oldData = beverageFile.readFileNotSplitted();
        for (int i = 0; i < ITEMS.length; i++) {
            beverageFile.overwriteFileRefill((ITEMS[i] + "\t" + MAX_VAL[i]), oldData.get(i));
        }
    }

    /**
     * Funzione che ricarica le bevande.
     */
    protected void refillBeverage() throws IOException {
        ArrayList<String[]> oldData = beverageFile.readFile();
        for (String[] vettTmp : oldData) {
            if (vettTmp[0].startsWith("0")) {
                String currentId = vettTmp[0];
                int currentMaxQuantity = getMaxQuantityFromMenu(currentId);
                String oldString = recreateString(vettTmp);
                String newString = currentId + "\t" + String.valueOf(currentMaxQuantity);
                beverageFile.overwriteFileRefill(newString, oldString);
            }
        }
    }

    /**
     * Funzione che ottiene la quantità massima.
     * @param id l'id associato alla quantità desiderata.
     */
    private int getMaxQuantityFromMenu(String id) {
        int tmpReturned = 0;
        for (String[] rowTmp : menu) {
            if (id.equals(rowTmp[0])) {
                tmpReturned = Integer.parseInt(rowTmp[4]);
            }
        }
        return tmpReturned;
    }

    /**
     * Funzione che crea una stringa unica da un vettore.
     * @param vett vettore da unificare.
     */
    private String recreateString(String[] vett){
        String tmp = "";
        for (int i = 0; i < vett.length; i++) {
            tmp += vett[i];
            if (i != vett.length - 1) {
                tmp += "\t";
            }
        }
        return tmp;
    }

    /**
     * Funzione per ricaricare le monete.
     */
    protected void refillCoins() {
        coinsFile.writeFile(stringCoinsValue() + "Server refill");
    }

    /**
     * Funzione che restituisce la stringa dei valori di ricarica dei coins.
     */
    private String stringCoinsValue() {
        String tmp = "";
        for (Integer integer : DEFAULT_MONEY) {
            tmp += integer + "\t";
        }
        return tmp;
    }
}