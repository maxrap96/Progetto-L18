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
        int sizeDifference = compareMenuAndData();
        for (String[] vettTmp : oldData) {
            if (vettTmp[0].startsWith("0")) {
                String currentId = vettTmp[0];
                double currentMaxQuantity = getMaxQuantityFromMenu(currentId);
                String oldString = recreateString(vettTmp);
                String newString = currentId + "\t" + String.valueOf(currentMaxQuantity);
                beverageFile.overwriteFileRefill(newString, oldString);
            }
        }
        String lastId = oldData.get(oldData.size() - 1)[0];
        ArrayList<String> originalFile = beverageFile.readFileRefill();
        for (int i = 0; i < sizeDifference; i++) {
            String currentId = menuFile.findNextId(lastId, menuFile.readFile());
            double currentMaxQuantity = getMaxQuantityFromMenu(currentId);
            String newString = currentId + "\t" + String.valueOf(currentMaxQuantity);
            originalFile.remove(originalFile.size() - 1);
            originalFile.add(newString);
            originalFile.add("*");
            lastId = currentId;
        }
        beverageFile.saveFileFromCommand(originalFile);
    }

    /**
     * Funzione che ottiene la quantità massima.
     * @param id l'id associato alla quantità desiderata.
     */
    private double getMaxQuantityFromMenu(String id) {
        double tmpReturned = 0;
        for (String[] rowTmp : menu) {
            if (id.equals(rowTmp[0])) {
                tmpReturned = Double.parseDouble(rowTmp[4]);
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

    public int compareMenuAndData () {
        int beverageMenuCount = 0;
        beverageMenuCount = menuFile.countBeverage(menuFile.readFileNotSplitted());
        int beverageDataCount = 0;
        beverageDataCount = beverageFile.countBeverage(beverageFile.readFileNotSplitted());
        if (beverageDataCount < beverageMenuCount) {
            return beverageMenuCount - beverageDataCount;
        }
        else {
            return 0;
        }
    }
}