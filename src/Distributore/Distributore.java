package Distributore;

import Bevande.*;
import Errori.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import static java.lang.Integer.parseInt;

public class Distributore implements MaxValue{

    private HashMap<String,Bevanda> list;
    private int cup, spoon;
    private double sugar, milk;
    private Coins coins;
    private Data stats = new Data("src/File_Testo/stats.txt");
    private Data menu = new Data("src/File_Testo/menu.txt");

    public Distributore() {
        this.list = new HashMap<>();
        this.coins = new Coins();
        setValues();

        try {
            createList(menu.readFile());
        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }
    }


    // Funzione che assegna i valori agli elementi della macchinetta leggendoli da file per
    // tenere conto anche delle transazioni precedenti.
    // Sul file stats.txt salvo i valori dell'ultima transazione che saranno i nuovi valori
    // iniziali della macchinetta alla prossima esecuzione.

    public void setValues() {
        String[] data = new String[5];

        try {
            ArrayList<String[]> statistiche = stats.readFile();
            int last = statistiche.size() - 1;


            for(int i = 0; i < data.length; i++) {
                data[i] = statistiche.get(last)[i];
            }

            this.cup = parseInt(data[1]);
            this.spoon = parseInt(data[2]);
            /*sugar data[3]
            milk data[4]*/

            this.sugar = SUGARMAX;
            this.milk = MILKMAX;

        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }
    }

    /**
     * Imposto i valori massimi di alcuni parametri del distributore, come la quantità di zucchero, latte, bicchierini
     * e cucchiani.
     */

    private void setVendingMachine() {
        this.sugar = SUGARMAX;
        this.milk = MILKMAX;
        this.cup = CUPMAX;
        this.spoon = SPOONMAX;
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
        System.out.println("Inserire l'ID della bevanda e la quantità di zucchero richiesta (da 0 a 5)\n" +
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

            // Scrittura statistiche su file:
            try {
                stats.writeFile(statsToText(ID));
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }

            if (coins.getCredit()!= 0) {
                coins.giveChange();
            }
        }
        else {
            //stats.writeFile();        // MJ: decommentare e aggiungere in futuro se necessario.
            new UnsufficientCredit();
        }
    }

    /**
     * Funzione per sottrarre quantità necessarie per preparare la bevanda
     * @param ID bevanda da cui prendere le dosi
     * @param sugar
     */
    private void subtractIngredients(String ID, int sugar) {
        list.get(ID).subtractDose();
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
            sugar -= (double) qty * SUGARDOSE;
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

    /**
     * Funzione per tener traccia di ciò che accade nella macchinetta per poi estrapolarne le statistiche di utilizzo
     * @param ID è la bevanda selezionata dal cliente
     * @return s: restituisce una stringa che poi sarà salvata nel file stats.txt
     */

    public String statsToText(String ID) {
        try {
            ArrayList<String[]> statistiche = stats.readFile();
            //int lastRow = statistiche.size() -1;

        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }

        String s = list.get(ID).getName() + "\t";

        s += cup + "\t" + spoon + "\t" + sugar + "\t" + milk + "\tTransazione avvenuta il:\t";

        return s;
    }

    /**
     * funziuone da usare nell'interfaccia per aggiungere i soldi
     * @param inserted è il valore associato al tasto di riferimento
     */

    public void addCredit(double inserted){
        double[] value=coins.getCOINS_VALUE();
        //cerco di capire la moneta inserita
        for (int i=0;i<value.length;i++){
            if (inserted == value[i]){
                //significa che ho trovato il valore corrispondente
                coins.addCoin(inserted,i);
            }
        }


    }

}