package Distributore;

import Bevande.*;
import PersonalExceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Distributore implements MaxValue {

    private HashMap<String, HotDrink> list;
    private int cup, spoon, selected_sugar;
    private double sugar, milk, vodka;
    private Coins coins;
    private Data stats = new Data("src/File_Testo/stats.txt");
    private Data ingredientsData = new Data("src/File_Testo/dati.txt");
    private Data menu = new Data("src/File_Testo/menu.txt");
    private ArrayList<String[]> dati;

    public Distributore() {
        this.list = new HashMap<>();
        this.coins = new Coins();
        setSugarToDefault();

        try {
            int lastRow = setValues(ingredientsData.readFile());
            createList(menu.readFile(), ingredientsData.readFile(), lastRow);
        } catch (FileNotReadable fileNotReadable) {
           fileNotReadable.printStackTrace();
        }
    }

    /**
     * Funzione che carica le quantità residue leggendole da file.
     */
    private int setValues(ArrayList<String[]> statistics) {
        setSugarToDefault();
        this.milk = Double.parseDouble(statistics.get(0)[1]);
        this.sugar = Double.parseDouble(statistics.get(1)[1]);
        this.spoon = parseInt(statistics.get(2)[1]);
        this.cup = parseInt(statistics.get(3)[1]);
        this.vodka = Double.parseDouble(statistics.get(4)[1]);
        int lastrow = 4; // Ultima riga letta dal file
        dati = statistics;
        checkIfMachineIsEmpty(); // Controllo se c'è bisogno di ricaricare la macchinetta.
        return lastrow;
    }

    /**
     * Funzione che controlla se la macchinetta deve essere ricaricata.
     */
    //TODO MJ: ci sarebbe anche da estendere il controllo ai vari ingredienti.
    private void checkIfMachineIsEmpty() {
        if (cup < 20 || spoon < 10 || sugar < 0.5 || milk < 0.2) {
            System.out.println("Refilling machine...\n");
            resetToMaxVendingMachine();
        }
    }

    /**
     * Ricarica alcuni elementi della macchinetta.
     */
    private void resetToMaxVendingMachine() {
        this.sugar = SUGARMAX;
        this.milk = MILKMAX;
        this.cup = CUPMAX;
        this.spoon = SPOONMAX;

        //TODO MJ: Inserire anche refill degli ingredienti.
    }

    /**
     * Funzione che crea il menu nella macchinetta.
     *
     * Nota: "data" viene utilizzata a partire dalla riga 5 (indice 4).
     *
     * @param listFromFile arraylist di stringhe fornito all'apertura del file.
     * @param data è l'arraylist contenente le quantità rimanenti delle bevande.
     * @param dataRow
     */
    private void createList(ArrayList<String[]> listFromFile, ArrayList<String[]> data, int dataRow) {
        String storedID = "";
        int numColID = 0; // Numero della colonna in cui è salvato il dato
        int numColType = 1;

        for (int i_menu = 0; i_menu < listFromFile.size(); i_menu++) {
            dataRow++;
            String currentID = listFromFile.get(i_menu)[numColID];
            Tipo hotDrinkType = Tipo.valueOf(listFromFile.get(i_menu)[numColType]);
            if (dataRow < data.size()) { // controllo di non aver superato la lunghezza del file delle quantità rimaste
                storedID = data.get(dataRow)[numColID];
            }

            if (!storedID.isEmpty() && currentID.equals(storedID)) {
                String quantityLeft = data.get(dataRow)[1];
                createDrink(hotDrinkType.ordinal(), listFromFile, i_menu, quantityLeft);
            } else {
                //ingredientsData.writeData(dataToWrite(listFromFile, i_menu));     // Togliendo la funzione ho il
                                                // numero corretto di righe di output ma non aggiorno i dati.
                createDrink(hotDrinkType.ordinal(), listFromFile, i_menu);
            }
        }
    }


    /**
     * Funzione per identificare il tipo della bevanda e aggiungerla al distributore nel caso non siano presenti dati
     * riguardanti la sua quantià residua.
     *
     * @param type         tipo della bevanda.
     * @param listFromFile è il file aperto contenente il menù.
     * @param i            è la riga a cui si è arrivati a leggere.
     */
    private void createDrink(int type, ArrayList<String[]> listFromFile, int i) {
        HotDrink hotDrink;

        switch (type) {
            case 0:
                hotDrink = new Macinato(listFromFile.get(i));
                list.put(listFromFile.get(i)[0], hotDrink);
                break;
            case 1:
                hotDrink = new Capsula(listFromFile.get(i));
                list.put(listFromFile.get(i)[0], hotDrink);
                break;
            case 2:
                hotDrink = new Solubile(listFromFile.get(i));
                list.put(listFromFile.get(i)[0], hotDrink);
                break;
            default:
                new InvalidType();
        }

    }

    /**
     * Funzione per identificare il tipo della bevanda e aggiungerla al distributore nel caso siano presenti dati
     * riguardanti la sua quantià residua.
     *
     * @param type         tipo della bevanda.
     * @param listFromFile è il file aperto contenente il menù.
     * @param index        è la riga a cui si è arrivati a leggere.
     * @param qtyLeft      è la quantità rimanente nella macchinetta.
     */
    private void createDrink(int type, ArrayList<String[]> listFromFile, int index, String qtyLeft) {
        HotDrink hotDrink;

        switch (type) {
            case 0:
                hotDrink = new Macinato(listFromFile.get(index), qtyLeft);
                list.put(listFromFile.get(index)[0], hotDrink);
                break;
            case 1:
                hotDrink = new Capsula(listFromFile.get(index), qtyLeft);
                list.put(listFromFile.get(index)[0], hotDrink);
                break;
            case 2:
                hotDrink = new Solubile(listFromFile.get(index), qtyLeft);
                list.put(listFromFile.get(index)[0], hotDrink);
                break;
            default:
                new InvalidType();
        }
    }

    // MJ: Dario dacci un'occhiata please e vediamo se abbiamo risolto.

    /**
     * Funzione per scrivere su file i dati di nuove bevande aggiunte sul menù.
     *
     * @param menu:  File menù, da cui ottengo ID e quantità iniziale delle bevande.
     * @param index: indice della riga in cui aggiungo la bevanda.
     * @return
     */
    private String dataToWrite(ArrayList<String[]> menu, int index) {
        String s = "";

        return s += (menu.get(index)[0] + "\t" + menu.get(index)[4] + "\n");  // scrivo ID + quantità massima;
    }

    /**
     * Funzione per recepire i comandi testuali ed analizzarli.
     */
    public void textualInput() {
        String input;
        do {
            showList();
            System.out.println("Inserire l'ID della bevanda e la quantità di zucchero richiesta (da 0 a 5) separate da uno " +
                    "spazio.\nNel caso non venga inserito nulla sarà di default a 3");
            input = keyboard();
        }while (input.isEmpty()); // Finchè non ricevo un input non proseguo
        String[] splitted = input.split("\\s+");

        if (splitted.length == 1){
            setSugarToDefault();
        }
        else { // Ho espresso una preferenza
            selected_sugar = parseInt(splitted[1]);
        }
        // Mi chiedo se la bevanda è disponibile
        try {
            if (list.get(splitted[0]).isAvailable()) {
                askForMoneyInput();
                selectBeverage(splitted[0]); // Funzione da usare nell'interfaccia per l'erogazione della bevanda
            } else {
                System.out.println("Bevanda non disponibile");
            }
        }
        catch (Exception e){
            new BeverageNotAvailable().printStackTrace();
        }
    }

    /**
     * Funzione per recepire input da tastiera e restituirli sotto forma di stringa.
     */
    private String keyboard() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Funzione che chiede quante monete inserire da tastiera.
     */
    private void askForMoneyInput(){
        double[] coinsValue = coins.getCOINS_VALUE();
        for (int i = 0; i < coinsValue.length; i++) {
            System.out.println("Inserire le monete da " + String.format("%.2f", coinsValue[i]) + " cent");
            String input = keyboard();
            if (parseInt(input) > 0) {
                coins.addCredit(input, i);
            }
        }
    }

    /**
     * Funzione per selezionare una bevanda. Essa controlla anche che il credito sia sufficiente.
     *
     * @param ID: è l'id della bevanda selezionata.
     */
    public String selectBeverage(String ID) {

        if (coins.getCredit() >= list.get(ID).getPrice() && list.get(ID).isAvailable()) {
            // Se il credito è uguale o maggiore singifica che posso potenzialmente acquistare la bevanda

            subtractIngredients(ID, selected_sugar);
            coins.updateBalance(list.get(ID).getPrice());
            setSugarToDefault();

            // Scrittura statistiche su file:
            try {
                stats.writeFile(statsToText(ID), true);
                updateDati(ID);
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
            coins.giveChange();
            if (coins.getCredit() != 0) {
                System.out.println("Bevanda erogata. Ritirare il resto");
                return "Bevanda erogata. Ritirare il resto" ;
            } else {
                System.out.println("Bevanda erogata.");
                return "Bevanda erogata";
            }
        } else {
            if (!list.get(ID).isAvailable()) {
                return "Bevanda non disponibile";
            }
            try {
                stats.writeFile(statsToText(ID), false);
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
            new UnsufficientCredit();
            return "Credito non sufficiente";
        }
    }

    /**
     * Funzione per sottrarre quantità necessarie per preparare la bevanda.
     *
     * @param ID:   della bevanda da cui prendere le dosi.
     * @param sugar
     */
    private void subtractIngredients(String ID, int sugar) {
        milk -= list.get(ID).getMilk();
        subtractSugar(sugar);
        cup--;
        vodka -= list.get(ID).getVodka();
    }

    /**
     * Funzione che sottrae lo zucchero usato.
     *
     * @param qty valore tra 0 e 5.
     */
    private void subtractSugar(int qty) {
        if (qty != 0) {
            sugar -= (double) qty * SUGARDOSE;
            spoon--;
        }
    }

    /**
     * Funzione che mostra la lista delle bevande contenute nel distributore.
     */
    private void showList() {
        for (int i = 1; i < list.size() + 1; i++) {
            System.out.println(list.get("0" + i));
        }
    }

    /**
     * Funzione che genera la stringa di statistiche.
     *
     * @param ID è la bevanda selezionata dal cliente.
     * @return s: restituisce una stringa con dei dati.
     */
    private String statsToText(String ID) {
        // TODO MJ: Da parametrizzare se possibile.
        return (list.get(ID).getName() + "\t" + cup + "\t" + spoon + "\t" + sugar + "\t" + milk + "\t");
    }

    /**
     * Funzione da usare nell'interfaccia per aggiungere i soldi.
     *
     * @param inserted è il valore associato al tasto di riferimento.
     */
    public void addCredit(double inserted) {
        coins.addCoin(inserted);
    }

    public String getLabel(int i) {
        return (list.get("0" + i).getName());
    }

    public int getListSize() {
        return list.size();
    }

    public String getID(int id) {
        return list.get("0" + id).getId();
    }

    public ArrayList<String[]> getDati() {
        return dati;
    }

    /**
     * Dopo l'erogazione della bevanda lo zucchero viene riportato alla quantità di default.
     */
    public void setSugarToDefault() {
        selected_sugar = 3;
    }

    /**
     * Funzione per aumentare lo zucchero selezionato (Tasto +).
     *
     * Nota: Da utilizzare nell'interfaccia.
     */
    public void moreSugar() {
        if (selected_sugar < 5) {
            selected_sugar++;
        }
    }

    /**
     * Funzione per diminuire lo zucchero selezionato (Tasto -).
     *
     * Nota: Da utilizzare nell'interfaccia.
     */
    public void lessSugar() {
        if (selected_sugar > 0) {
            selected_sugar--;
        }
    }

    public int getSelected_sugar() {
        return selected_sugar;
    }

    /**
     * Funzione per aggiornare il file dati.txt, contenente le quantità di oggetti e di ingredienti.
     */
    private void updateDati(String ID) {
        String valDati[] = {"" + milk, "" + sugar, "" + spoon, "" + cup, "" + vodka};
        String newLine = "";
        String current = "";

        try {
            for (int i = 0; i < valDati.length; i++) {
                current = dati.get(i)[0] + "\t" + dati.get(i)[1];

                if(dati.get(i)[0].equals("Cups") || dati.get(i)[0].equals("Spoons")){
                    newLine = dati.get(i)[0] + "\t" + valDati[i];
                }
                else{newLine = dati.get(i)[0] + "\t" + Double.parseDouble(valDati[i]);}
                ingredientsData.overwriteFile(newLine, current);
            }

            current = ID + "\t" + list.get(ID).getLeftQuantity();
            list.get(ID).subtractDose();
            newLine = ID + "\t" + (float)list.get(ID).getLeftQuantity();
            ingredientsData.overwriteFile(newLine, current);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getCredit() {
        return coins.getCredit();
    }

    /**
     * Funzione che associa i nomi dei pulsanti ai relativi valori.
     *
     * Nota: Da utilizzare nell'interfaccia
     *
     * @return sono i valori delle monete in forma vettore.
     */
    public double[] getCoinsValue() {
        return coins.getCOINS_VALUE();
    }

    public double getPrice(String ID) {
        return list.get(ID).getPrice();
    }

    public void giveChange() {
        coins.giveChange();
    }
}