package Distributore;

import Bevande.*;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class Distributore {

    /**
     * Commento di Dario: "Qunado ragioni con le quantità massime prova a vedere se riesci a fare attributi final
     * generici."
     */

    private HashMap<String,Bevanda> list;
    private int cup, cupMax, spoon, spoonmax;
    private double water, watermax;
    private double sugar, sugarMax;
    private double credit, balance;
    private ArrayList<String[]> listFromFile;
    private String[] statistics;
    private OpenFile input = new OpenFile();    // è una vecchia parte di un programma di Hexrebuilt. apre files e
                                                // li splitta in base alla tabulazione. inoltre ha  già un metodo per
                                                // recepire comandi da tastiera.

    public Distributore(String pathFile) {
        this.list = new HashMap<>();
        listFromFile = new ArrayList<>();       // Commento di Dario: Non avevi inizializzato l'array
        this.listFromFile = input.apriFile(pathFile);
        this.credit = 0;
        this.balance = 0;
        SetVendingMachine();
    }
    /** il file è impostato in modo tale che la prima riga siano le informazioni della macchinetta
     * bicchierini cucchianini acqua   zucchero    e poi server
     * 0            1           2       3           4
     *
     * inoltre è fatto in modo da ignorare le righe che iniziano con *
     */

    private void SetVendingMachine() {

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
        CreateList();
    }

    /**
     * Per ogni riga di file leggo e analizzo il contenuto per creare la bevanda.
     * Ricordandomi che la riga 0 è della macchinetta. quindi inizio da 1
     */

    /**
     * Commento di Dario: "Appena possibile implementa un try - catch nel caso sia inserito un nome non valido"
     *
     */

    private void CreateList() {
        /*
         MACINATO, CAPSULA,SOLUBILE
         l'uso degli enum con ordinal creava bug
         */
        for (int i = 1; i < listFromFile.size(); i++){
            Tipo tipo=findType(listFromFile.get(i)[1]);
            Bevanda bevanda=null;
            switch (tipo.ordinal()) {
                case 0:
                    bevanda = new Macinato(listFromFile.get(i));
                    break;
                case 1:
                    bevanda = new Capsula(listFromFile.get(i));
                    break;
                case 2:
                    bevanda = new Solubile(listFromFile.get(i));
                    break;
            }
            list.put(listFromFile.get(i)[0],bevanda);
        }
    }

    private Tipo findType(String s) {
            if (s.equals(String.valueOf((Tipo.MACINATO)))){
                return Tipo.MACINATO;
            }
            else {
                if (s.equals(String.valueOf((Tipo.CAPSULA)))){
                    return Tipo.CAPSULA;
                }
                else {
                    return Tipo.SOLUBILE;
                }
            }
    }
}

