package Distributore;

import Bevande.*;
import Errori.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    /**
     * Funzione che carica le quantità residue leggendole da file.
     */

    public void setValues() {
        String[] valuesToTrack = new String[5]; // MJ: Valore impostato a 5.
                                                // Sarebbe da parametrizzare ma al momento non saprei che metterci.

        try {
            ArrayList<String[]> statistiche = stats.readFile();
            int lastRow = statistiche.size() - 1;

            for(int i = 0; i < valuesToTrack.length; i++) {
                valuesToTrack[i] = statistiche.get(lastRow)[i];
            }

            this.cup = parseInt(valuesToTrack[1]);
            this.spoon = parseInt(valuesToTrack[2]);
            this.sugar = Double.parseDouble(valuesToTrack[3]);
            this.milk = Double.parseDouble(valuesToTrack[4]);

            // MJ: Controllo se c'è bisogno di ricaricare la macchinetta.
            checkIfMachineIsEmpty();

        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }
    }

    private void checkIfMachineIsEmpty() {
        if(cup==0 || spoon==0 || sugar==0 || milk==0) {
            resetToMaxVendingMachine();
        }
    }

    /**
     * Imposto i valori massimi di alcuni parametri del distributore, come la quantità di zucchero, latte, bicchierini
     * e cucchiani.
     */
    private void resetToMaxVendingMachine() {
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
            else{
                new BeverageNotAvailable();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Funzione per recepire input da tastiera e restituirli sotto forma di stringa.
     */

    public String keyboard() throws NoDigit{
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Funzione per selezionare una bevanda. Essa controlla che il credito sia sufficiente.
     * il credito è già contenuto dentro a credit.
     * @param ID: è l'id della bevanda selezionata
     * @param sugar: è la qunatità di zucchero da 0 a 5
     */

    public String selectBeverage(String ID, int sugar){

        if (coins.getCredit() >= list.get(ID).getPrice() && list.get(ID).isAvailable()){  // Se il credito è uguale o maggiore singifica che posso
                                                            // potenzialmente acquistare la bevanda
            subtractIngredients(ID,sugar);
            coins.updateBalance(list.get(ID).getPrice());

            // Scrittura statistiche su file:
            try {
                stats.writeFile(statsToText(ID));
                //stats.writeFile(statsToText(ID), true);   // MJ: Variante. Controllare con gli "amici" quale usare.
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
            if (coins.getCredit()!= 0) {
                return "bevanda erogata " + coins.giveChange();
            }
            else {
                System.out.println("Bevanda erogata");
                return "Bevanda erogata";
            }
        }
        else {
            try {
                stats.writeFile(statsToText(ID));    // Nel caso non ci sia credito sufficiente la
                //stats.writeFile(statsToText(ID), false);    // Nel caso non ci sia credito sufficiente la
                                                                       // transazione fallisce.
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
            new UnsufficientCredit();
            return "Credito insufficiente o bevanda non disponibile";
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
     * Funzione per tener traccia di ciò che accade nella macchinetta
     * @param ID è la bevanda selezionata dal cliente
     * @return s: restituisce una stringa che poi sarà salvata nel file stats.txt
     */

    public String statsToText(String ID) {
        String s = list.get(ID).getName() + "\t";

        // MJ: Da parametrizzare se possibile.
        return s + cup + "\t" + spoon + "\t" + sugar + "\t" + milk + "\t";
        //TODO Sistemare output del numero di cifre decimali dei valori double.
    }

    /**
     * Funziuone da usare nell'interfaccia per aggiungere i soldi
     * @param inserted è il valore associato al tasto di riferimento
     */

    public void addCredit(double inserted){
        double[] value = coins.getCOINS_VALUE();
        //cerco di capire la moneta inserita
        for (int i=0;i<value.length;i++){
            if (inserted == value[i]){
                //significa che ho trovato il valore corrispondente
                coins.addCoin(inserted,i);
            }
        }
    }

    /**
     * funzione per fornire alla interfaccia l'etichetta da mettere sui bottoni
     * @param i bevanda da inserire nella macchinetta
     * @return restituisce una stringa contenente il nome ed il costo della bevanda
     */
    public String getLabel(int i){
        return (list.get("0"+i).getName() + "\tCosto: " + list.get("0"+i).getPrice());
    }

    /**
     * funzione per restituire alla macchinetta la quantità di bevande da aggiungere
     * @return è il numero di bevande presenti nel distributore
     */
    public int getListSize(){
        return list.size();
    }

    /**
     * returna l'id corrispondente della bevanda da associare al bottone
     * @param id è il numero della bevanda per ricaverne l'id
     * @return ritorna l'id della bevanda corrispondente
     */

    public String getID(int id){
        return list.get("0"+id).getId();
    }

}