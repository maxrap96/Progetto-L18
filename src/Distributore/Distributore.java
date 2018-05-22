package Distributore;

import Bevande.*;
import PersonalExceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Distributore implements MaxValue, TextFiles {

    private HashMap<String, HotDrink> list;
    private int selected_sugar;
    private Coins coins;
    private Data stats = new Data(TextFiles.STATSPATH);
    private Data ingredientsData = new Data(DATAPATH);
    private Data menu = new Data(MENUPATH);
    private ArrayList<String[]> dati;
    private Erogatore erogatore;

    private Chiavetta chiavetta = new Chiavetta();

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

    private int setValues(ArrayList<String[]> data) {
        setSugarToDefault();
        double milk = Double.parseDouble(data.get(0)[1]);
        double sugar = Double.parseDouble(data.get(1)[1]);
        int spoon = parseInt(data.get(2)[1]);
        int cup = parseInt(data.get(3)[1]);
        double vodka = Double.parseDouble(data.get(4)[1]);
        int lastrow = 4; // Ultima riga letta dal file
        erogatore = new Erogatore(milk, sugar, spoon, cup, vodka);
        dati = data;
        erogatore.checkIfMachineIsEmpty(); // Controllo se c'è bisogno di ricaricare la macchinetta.
        return lastrow;
    }


    /**
     * Funzione che crea il menu nella macchinetta.
     *
     * Nota: "data" viene utilizzata a partire dalla riga 5 (indice 4).
     *
     * @param listFromFile arraylist di stringhe fornito all'apertura del file.
     * @param data è l'arraylist contenente le quantità rimanenti delle bevande.
     * @param dataRow è la riga dalla quale iniziano i dati delle bevande
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

    /**
     * Funzione per recepire i comandi testuali ed analizzarli.
     */
    public void textualInput() {
        String input;
        do {
            showList();
            System.out.println("Inserire l'ID della bevanda e la quantità di zucchero richiesta (da 0 a 5) separate " +
                    "da uno spazio.\nNel caso non venga inserito nulla sarà di default a 3");
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
        if (!list.get(ID).isAvailable()) {
            return "Bevanda non disponibile";
        }


        boolean transaction = false;

        if (chiavetta.isConnected()){

            transaction = chiavetta.Pay(list.get(ID).getPrice());
            // Scrittura statistiche su file:
            try {
                stats.writeFile(list.get(ID).getName(),transaction);
                updateDati(ID);
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }

            if (transaction){
                return "Bevanda erogata";
            }
            else {
                return "Saldo non sufficiente";
            }
        }

        if (coins.getCredit() >= list.get(ID).getPrice() && list.get(ID).isAvailable()) {
            // Se il credito è uguale o maggiore singifica che posso potenzialmente acquistare la bevanda
            transaction = true;
            erogatore.subtractIngredients(list.get(ID), selected_sugar);
            coins.updateBalance(list.get(ID).getPrice());
            setSugarToDefault();
            coins.giveChange();

            // Scrittura statistiche su file:
            try {
                stats.writeFile(list.get(ID).getName(), transaction);
                updateDati(ID);
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }

            if (coins.getCredit() != 0) {
                System.out.println("Bevanda erogata. Ritirare il resto");
                return "Bevanda erogata. Ritirare il resto" ;
            } else {
                System.out.println("Bevanda erogata.");
                return "Bevanda erogata";
            }
        } else {
            return "Credito non sufficiente";
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
     * Funzione da usare nell'interfaccia per aggiungere i soldi.
     *
     * @param inserted è il valore associato al tasto di riferimento.
     */
    public void addCredit(double inserted) {
        if (chiavetta.isConnected()){
            chiavetta.AddSaldo(inserted);
            coins.charcheKey(inserted);
        }
        else {
            coins.addCoin(inserted);
        }
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
        String valDati[] = erogatore.getData();
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
        if (chiavetta.isConnected()){
            return chiavetta.getSaldo();
        }
        else {
            return coins.getCredit();
        }
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

    public void setconnectionChiavetta(){
        chiavetta.setConnected();
        if (coins.getCredit() !=0 ){
            chiavetta.AddSaldo(coins.getCredit());
            coins.updateBalance(coins.getCredit());
        }
    }
}