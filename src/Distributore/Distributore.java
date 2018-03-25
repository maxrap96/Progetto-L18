package Distributore;

import Bevande.*;
import Errori.InvalidType;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class Distributore {

    /**
     * Commento di Dario: "Qunado ragioni con le quantità massime prova a vedere se riesci a fare attributi final
     * generici."
     * -- non so bene come farlo dato che devo inizializzarli dentro al costruttore e non so a priori quanto siano
     * finchè non apro il file.
     * se hai soluzioni in merito si fa subito.
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
                                                // commento simo: non serve tanto ce ne schiaffo sopra un'altro già fatto
        this.listFromFile = input.apriFile(pathFile);
        this.credit = 0;
        this.balance = 0;
        setVendingMachine();
    }

    /** il file è impostato in modo tale che la prima riga siano le informazioni della macchinetta
     * bicchierini cucchianini acqua   zucchero    e poi server
     * 0            1           2       3           4
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
            Tipo tipo = findType(listFromFile.get(i)[1]);
            Bevanda bevanda = null;
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
                default:
                    new InvalidType();
                    continue;
            }
            list.put(listFromFile.get(i)[0],bevanda);
        }
    }

    /**
     * Funzione grezza da raffinare per individuare il tipo della bevanda.
     * @param s stringa proveniente dal campo tipo del file di testo
     */

    //TODO MIGLIORARE LA FUNZIONE PER CONFRONTARE UNA STRINGA CON GLI ENUM

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

