package ClientSide;

import HotDrinkVendingMachine.CoinsNumbers;
import HotDrinkVendingMachine.Data;
import HotDrinkVendingMachine.MaxValue;
import HotDrinkVendingMachine.TextPathFiles;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class ReceiverRefill implements MaxValue, TextPathFiles, CoinsNumbers {
    private Data menuFile;
    private Data beverageFile;
    private Data coinsFile;
    private ArrayList<String[]> menu;
    private final String[] ITEMS = {"Milk", "Sugar", "Spoons", "Cups", "Vodka"};
    private final double[] MAX_VAL = {MILK_MAX, SUGAR_MAX, SPOON_MAX, CUP_MAX,VODKA_MAX};

    public ReceiverRefill() {
        this.menuFile = new Data(MENU_PATH);
        this.beverageFile = new Data(DATA_PATH);
        this.coinsFile = new Data(COINS_PATH);
        this.menu = menuFile.readFile();
    }

    /**
     * Funzione che "ricarica" le quantità massima di dati.txt
     */
    protected void refillItems() throws IOException {
        ArrayList<String> oldData = beverageFile.readFileNotSplitted();
        for (int i = 0; i < ITEMS.length; i++) {
            beverageFile.overwriteFile((ITEMS[i] + "\t" + MAX_VAL[i]), oldData.get(i));
        }
    }

    /**
     *
     */
    protected void refillBeverage()throws IOException{
        ArrayList<String[]> oldData = beverageFile.readFile();
        for (int i = 0; i < oldData.size(); i++ ){
            if (oldData.get(i)[0].startsWith("0")){ //significa che è una entrata nel menu
                String currentId = oldData.get(i)[0];
                for (int j = 0; i < menu.size(); i++){
                    if (menu.get(j)[0].equals(currentId)){
                        double currentMaxQuantity = parseDouble(menu.get(j)[4]);
                        beverageFile.overwriteFile(String.valueOf(oldData.get(i)), (currentId + currentMaxQuantity));
                    }
                }
            }
        }
    }

    /**
     * Funzione per ricaricare le monete
     */
    protected void refillCoins(){
        coinsFile.writeFile(stringCoinsValue() + "Server refill");
    }

    /**
     * Funzione che restituisce la stringa dei valori di ricarica dei Coins.
     */
    private String stringCoinsValue(){
        String tmp = "";
        for (Integer integer : MONEY_COUNT){
            tmp += integer + "\t";
        }
        return tmp;
    }
}