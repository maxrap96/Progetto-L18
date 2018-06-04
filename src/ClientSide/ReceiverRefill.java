package ClientSide;

import HotDrinkVendingMachine.CoinsNumbers;
import HotDrinkVendingMachine.Data;
import HotDrinkVendingMachine.MaxValue;
import HotDrinkVendingMachine.TextPathFiles;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class ReceiverRefill implements MaxValue, TextPathFiles, CoinsNumbers {
    private ArrayList<String[]> menu = new Data(MENU_PATH).readFile();
    private Data beverageFile = new Data(DATA_PATH);
    private Data coinsFile = new Data(COINS_PATH);
    private final String[] ITEMS = {"Milk", "Sugar", "Spoons", "Cups", "Vodka"};

    /**
     * funzione per refillare gli items e scrivere su file le quantità riempite
     */
    protected void refillItems() throws IOException {
        ArrayList<String[]> oldData = beverageFile.readFile();
        beverageFile.overwriteFile(String.valueOf(oldData.get(0)), (ITEMS[0] + MILK_MAX));
        beverageFile.overwriteFile(String.valueOf(oldData.get(1)), (ITEMS[1] + SUGAR_MAX));
        beverageFile.overwriteFile(String.valueOf(oldData.get(2)), (ITEMS[2] + SPOON_MAX));
        beverageFile.overwriteFile(String.valueOf(oldData.get(3)), (ITEMS[3] + CUP_MAX));
        beverageFile.overwriteFile(String.valueOf(oldData.get(4)), (ITEMS[4] + VODKA_MAX));
    }

    /**
     * funzione per riempire le bevande basandosi sulla quantià massima presente nel menu
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
     * funzione per refillare i coins
     */
    protected void refillCoins(){
        String moneyCount = String.valueOf(MONEY_COUNT[0] + "\t" + MONEY_COUNT[1] + "\t"  + MONEY_COUNT[2] + "\t"
                + MONEY_COUNT[3] + "\t" + MONEY_COUNT[4] + "\tServer refill");
        coinsFile.writeData(moneyCount);
    }


}
