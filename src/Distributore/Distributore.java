package Distributore;

import Bevande.*;
import Errori.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static Distributore.MaxValue.SUGARDOSE;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;

public class Distributore {

    private HashMap<String,Bevanda> list;
    private int cup, spoon;
    private double sugar, milk;
    private Coins coins;
    private Data stats = new Data("src/File_Testo/stats.txt");
    private Data menu = new Data("src/File_Testo/menu.txt");

    public Distributore() {
        this.list = new HashMap<>();
        this.coins = new Coins();
        setVendingMachine();
        try {
            createList(menu.readFile());
        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }
    }

    /**
     * Imposto i valori massimi di alcuni parametri del distributore, come la quantità di zucchero, latte, bicchierini
     * e cucchiani.
     */

    private void setVendingMachine() {
        this.sugar = MaxValue.SUGARMAX;
        this.milk = MaxValue.MILKMAX;
        this.cup = MaxValue.CUPMAX;
        this.spoon = MaxValue.SPOONMAX;
    }

    /**
     * Creo il menu nella macchinetta
     * @param listFromFile arraylist di stringhe fornito all'apertura del file
     */

    private void createList(ArrayList<String[]> listFromFile) {

        for (int i = 0; i < listFromFile.size(); i++){
            Tipo tipo = Tipo.valueOf(listFromFile.get(i)[1]);
            Bevanda bevanda = null;
            switch (tipo.ordinal()) {
                case 0:
                    bevanda = new Macinato(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],bevanda);
                    break;
                case 1:
                    bevanda = new Capsula(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],bevanda);
                    break;
                case 2:
                    bevanda = new Solubile(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],bevanda);
                    break;
                default:
                    new InvalidType();
                    continue;
            }
        }

    }

    /**
     * Funzione per recepire i comandi testuali ed analizzarli.
     */

    public void textualInput (){
        showList();
        System.out.println("Inserire il codice della bevanda e la quantità di zucchero richiesta (da 0 a 5)\n" +
                "separate da uno spazio.");
        String input = null;

        try {
            input = keyboard();
        } catch (NoDigit noDigit) {
            noDigit.printStackTrace();
        }

        String[] splitted = input.split("\\s+");
        //mi chiedo se la bevanda è disponibile
        try {
            if (list.get(splitted[0]).isAvailable()) {
                double[] value = coins.getCOINS_VALUE();
                for (int i = 0; i < value.length; i++) {
                    try {
                        System.out.println("Inserire le monete da " + String.format("%.2f", value[i]) + " cent");
                        input = keyboard();
                        coins.addCredit(input, i);
                    } catch (NoDigit noDigit) {
                        noDigit.printStackTrace();
                    }
                }
                //vera e propria funzione da usare nell'interfaccia per l'erogazione della bevanda
                selectBeverage(splitted[0], parseInt(splitted[1]));
            }
        }
        catch (Exception e){
            new BeverageNotAvailable();
        }
    }

    /**
     * Funzione per recepire input da tastiera e restituirli sotto forma di stringa.
     */

    public String keyboard() throws NoDigit{
        InputStreamReader keyboard = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(keyboard);
        try {
            //String letta = bufferedReader.readLine(); // MJ: La stringa letta e' ridondante, possiamo toglierla.
            //TODO C'è DA CAPIRE PERCHè SE LE CHIUDO ESPLODE IL MONDO
            // keyboard.close();
            //bufferedReader.close();
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new NoDigit();
        }
    }

    /**
     * Funzione per selezionare una bevanda. Essa controlla che il credito sia sufficiente.
     * il credito è già contenuto dentro a credit.
     * @param ID: è l'id della bevanda selezionata
     * @param sugar: è la qunatità di zucchero da 0 a 5
     */

    private void selectBeverage(String ID, int sugar){

        if (coins.getCredit() >= list.get(ID).getPrice()){  //se il credito è uguale o più singifica che posso
                                                            // potenzialmente acquistare la bevanda
            subtractIngredients(ID,sugar);
            coins.updateBalance(list.get(ID).getPrice());

            // Scrittura su file di statistiche:
            try {
                stats.writeFile(statisticsFile(ID));
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }

            if (coins.getCredit()!= 0) {
                coins.giveChange();
            }
        }
        else {
            new UnsufficientCredit();
        }
    }

    /**
     * Funzione per sottrarre quantità necessarie per preparare la bevanda
     * @param ID bevanda da cui prendere le dosi
     * @param sugar
     */
    private void subtractIngredients(String ID, int sugar) {
        milk -= list.get(ID).getMilk();
        subtractSugar(sugar);
        cup--;
    }

    /**
     * Funzione che sottrae lo zucchero usato
     * @param qty, valore tra 0 e 5
     */

    private void subtractSugar(int qty){
        if (qty != 0){
            sugar -= (double) qty * MaxValue.SUGARDOSE;
            spoon--;
        }
    }

    /**
     * Funziona che mostra la lista delle bevande contenute nel distributore.
     */
    private void showList() {
        for (int i = 1; i < list.size() + 1; i++){
            System.out.println(list.get("0" + i));
        }
    }

    // MJ: Funzione da rinominare con nome piu' significativo:
    protected String statisticsFile(String ID) {
        return  list.get(ID).getName() + "\t" + String.format("%.3f", sugar) + "\t" + cup + "\t" + spoon +
                "\t\tTransazione avvenuta il:";
    }
}