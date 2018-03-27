package Distributore;

import Bevande.*;
import Errori.BeverageNotAvaible;
import Errori.InvalidType;
import Errori.NoDigit;
import Errori.UnsufficientCredit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Distributore {

    /**
     * Commento di Dario: "Per i campi final ne parliamo".
     */

    private HashMap<String,Bevanda> list;
    private int cup, cupMax, spoon, spoonmax;
    private double water, watermax;
    private double sugar, sugarMax;
    private double milk, milkMax;
    private Coins coins;
    private ArrayList<String[]> listFromFile;
    private String[] statistics;
    //private OpenFile file = new OpenFile();    // è una vecchia parte di un programma di Hexrebuilt. apre files e
                                                // li splitta in base alla tabulazione. inoltre ha  già un metodo per
                                                // recepire comandi da tastiera.

    public Distributore(ArrayList listFromFile) {

        this.list = new HashMap<>();
        this.listFromFile = listFromFile;
        setVendingMachine();
        this.coins = new Coins();
    }

    /** Il file è impostato in modo tale che la prima riga siano le informazioni della macchinetta
     * valore:  bicchierini cucchianini acqua   zucchero    latte
     * indice:  0            1           2       3          4
     *
     */

    private void setVendingMachine() {

        this.cupMax = parseInt(listFromFile.get(0)[0]);
        this.cup = cupMax;
        this.spoonmax = parseInt(listFromFile.get(0)[1]);
        this.spoon = spoonmax;
        this.watermax = parseDouble(listFromFile.get(0)[2]);
        this.water = watermax;
        this.sugarMax = parseDouble(listFromFile.get(0)[3]);
        this.sugar = sugarMax;
        this.milkMax = parseDouble(listFromFile.get(0)[4]);
        this.milk=milkMax;
        //todo add server quando ci sarà

        //mi devo ricordare che dalla seconda riga in poi sono le bevande
        createList();
    }

    /**
     * Per ogni riga di file letto, analizzo il contenuto per creare la bevanda.
     * Ricordandomi che la riga 0 sono le informazioni della macchinetta. quindi inizio dall'elemento 1 dell'arraylist
     * per le bevande.
     *
     * MACINATO,
     * CAPSULA,
     * SOLUBILE
     * l'uso degli enum mi permette di creare rapidamente un sistema per decidere
     * il tipo della bevanda, permettendo di aggiungerne una nuova tipologia con facilità.
     */
    private void createList() {

        for (int i = 1; i < listFromFile.size(); i++){
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
     * Funzione per recepire i comandi testuali ed analizzarli. una volta capito se si tratti di un Codice di una
     * bevanda o di un quantittativo di monete, chiamerò le rispettive funzioni.
     *
     * da tastiera le monete saranno inserite come "quantità inserita"
     * tipo: 0.05 0.10. 0.20 0.50 1 2
     * input: 1     0    3    1   0 0 ad esempio.
     */

    public void textualInput (){
        showList();
        System.out.println("Inserire il codice della bevanda e la quantità di zucchero richiesta (da 0 a 5)\nseparate da uno spazio.");
        String input = null;
        try {
            input = keyboard();
        } catch (NoDigit noDigit) {
            noDigit.printStackTrace();
        }
        String[] splitted = input.split("\\s+");
        //mi chiedo se la bevanda è disponibile
        if (list.get(splitted[0]).isAvaible()) {
            System.out.println("Inserire il numero di monete inserite riferite al rispettivo taglio separandole con uno spazio.\ntipo: 0.05c 0.10c 0.20c 0.50c 1 2");
            try {
                input = keyboard();
            } catch (NoDigit noDigit) {
                noDigit.printStackTrace();
            }
            coins.addCredit(input);
            //vera e propria funzione da usare nella interfaccia
            try {
                selectBeverage(splitted[0], parseInt(splitted[1]));
            } catch (UnsufficientCredit unsufficientCredit) {
                unsufficientCredit.printStackTrace();
            }
        }
        else {
            new BeverageNotAvaible();
        }
    }

    /**
     * Funzione per recepire input da tastiera e restituirli sotto forma di stringa. Essa poi dovrà essere analizzata
     * adeguatamente dalla funzione che la va a richiamare.
     */

    public String keyboard() throws NoDigit {
        InputStreamReader keyboard = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(keyboard);
        try {
            String letta= bufferedReader.readLine();
            //bufferedReader.close();
            //keyboard.close();
            return letta;
        } catch (IOException e) {
            throw new NoDigit();
        }
    }

    /**
     * funzione per selezionare una bevanda. essa controlla che il credito sia sufficiente
     * @param beverage: è l'id della bevanda selezionata
     * @param sugar: è la qunatità di zucchero da 0 a 5
     */

    private void selectBeverage(String beverage,int sugar) throws UnsufficientCredit {

        if (coins.getCredit()>=list.get(beverage).getPrice()){ //se il credito è uguale o più singifica che posso potenzialmente acquistare la bevanda
                list.get(beverage).subtractDose();
                subtractIngridients(beverage);
                subtractSugar(sugar);
                coins.updateBalance(list.get(beverage).getPrice());
                if (coins.getCredit()!= 0) {
                    //TODO sta funzione è da fare
                    coins.giveChange();
                }
        }
        else {
            throw new UnsufficientCredit();
        }
    }

    /**
     * funzione per sottrarre quantità necessarie per preparare la bevanda
     * @param bevanda bevanda da cui sottrarre
     */
    private void subtractIngridients(String bevanda) {
        milk-=list.get(bevanda).getMilk();
        water-=list.get(bevanda).getWater();
        cup--;

    }

    private void subtractSugar(int qty){
        if (qty!=0){
            sugar-= (double) qty *0.022/5;
            spoon--;
        }
    }

    /**
     * Funziona che mostra la lista delle bevande contenute nel distributore
     */
    private void showList() {
        for (int i=0;i<list.size();i++){
            //todo replecare lo string
            //list.get(i).toString();
            System.out.println(i);
        }
    }
}

