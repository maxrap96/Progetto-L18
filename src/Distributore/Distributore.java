package Distributore;

import Bevande.*;
import Errori.BeverageNotAvaible;
import Errori.InvalidType;
import Errori.UnsufficientCredit;

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
    private double credit, balance;
    private ArrayList<String[]> listFromFile;
    private String[] statistics;
    private OpenFile file = new OpenFile();    // è una vecchia parte di un programma di Hexrebuilt. apre files e
                                                // li splitta in base alla tabulazione. inoltre ha  già un metodo per
                                                // recepire comandi da tastiera.

    public Distributore(String pathFile) {

        this.list = new HashMap<>();
        listFromFile = new ArrayList<>();
        this.listFromFile = file.apriFile(pathFile);
        this.credit = 0;
        this.balance = 0;
        setVendingMachine();
    }

    /** Il file è impostato in modo tale che la prima riga siano le informazioni della macchinetta
     * valore:  bicchierini cucchianini acqua   zucchero
     * indice:  0            1           2       3
     *
     */

    private void setVendingMachine() {

        this.cupMax = parseInt(listFromFile.get(0)[0]);
        this.cup = cupMax;
        this.spoonmax = parseInt(listFromFile.get(0)[1]);
        this.spoon = spoonmax;
        this.watermax = parseInt(listFromFile.get(0)[2]);
        this.water = watermax;
        this.sugarMax = parseInt(listFromFile.get(0)[3]);
        this.sugar = sugarMax;
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
     */

    public void textualInput (){
        showList();
        System.out.println("Inserire il codice della bevanda e il numero di monete separate da uno spazio. Per i centesimi utilizzare il punto (.)");
        String input = file.keyboard();
        String[] splitted = input.split("\\s+");
        credit+=parseDouble(splitted[1]);
        selectBeverage(splitted[0]);
    }

    /**
     * funzione per selezionare una bevanda. essa controlla che il credito sia sufficiente
     * @param beverage: è l'id della bevanda selezionata
     */

    private void selectBeverage(String beverage) {
        if (credit>=list.get(beverage).getPrice()){ //se il credito è uguale o più singifica che posso potenzialmente acquistare la bevanda
            if (list.get(beverage).isAvaible()){ //controllo che sia disponibile
                list.get(beverage).subtractDose();
                beverage+=list.get(beverage).getPrice();
                credit=credit-list.get(beverage).getPrice();
            }
            else{
                new BeverageNotAvaible();
            }
        }
        else {
            new UnsufficientCredit();
        }
    }

    /**
     * Funziona che mostra la lista delle bevande contenute nel distributore
     */
    private void showList() {
        for (int i=0;i<list.size();i++){
            //list.get(i).toString();
            System.out.println(i);
        }
    }
}

