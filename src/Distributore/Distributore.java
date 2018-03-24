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
    private double credit, bank;
    private ArrayList<String[]> listFromFile;
    private String[] statistics;
    private OpenFile input = new OpenFile();    // è una vecchia parte di un programma di Hexrebuilt. apre files e
                                                // li splitta in base alla tabulazione. inoltre ha  già un metodo per
                                                // recepire comandi da tastiera.

    public Distributore(String pathFile) {
        this.list = new HashMap<>();
        listFromFile = new ArrayList<>();       // Commento di Dario: Non avevi inizializzato l'array
        this.listFromFile = input.apriFile(pathFile);
        SetVendingMachine();
    }

    private void SetVendingMachine() {
        /** il file è impostato in modo tale che la prima riga siano le informazioni della macchinetta
         * bicchierini  cucchianini acqua   zucchero    e poi server
         * 0            1           2       3           4
         *
        */

        int i=0;
        while(listFromFile.get(i)[0].startsWith("*")){
            i++;
        }

        this.cupMax = parseInt(listFromFile.get(i)[0]);
        this.cup = cupMax;
        this.spoonmax = parseInt(listFromFile.get(i)[1]);
        this.spoon = spoonmax;
        this.watermax = parseInt(listFromFile.get(i)[2]);
        this.water = watermax;
        this.sugarMax = parseInt(listFromFile.get(i)[3]);
        this.sugar = sugarMax;
        //todo add server quando ci sarà


        //mi devo ricordare che dalla seconda riga in poi sono le bevande
        CreateList(i);
    }

    /**
     * Per ogni riga di file leggo e analizzo il contenuto per creare la bevanda.
     * Ricordandomi che la riga 0 è della macchinetta. quindi inizio da 1
     */

    /**
     * Commento di Dario: "Appena possibile implementa un try - catch nel caso sia inserito un nome non valido"
     * @param infoVendingmachine riga delle informazioni della macchinetta
     */

    private void CreateList(int infoVendingmachine) {
        Bevanda tmp;
        Tipo type;


        for (int i = infoVendingmachine + 1; i < listFromFile.size(); i++){

            if (listFromFile.get(i)[0].startsWith("*")){
                continue; //significa che è una riga da ignorare
            }

            if (listFromFile.get(i)[1].equals(Tipo.CAPSULA)) {
                type = Tipo.CAPSULA;
            }
            else if (listFromFile.get(i)[1].equals(Tipo.MACINATO)){
                type = Tipo.MACINATO;
            }
            else {
                type = Tipo.SOLUBILE; // è l'ultimo che resta
            }

            switch (type.ordinal()){
                /**
                 * Macinato
                 */
                case 1:
                    tmp = new Macinato(listFromFile.get(i));
                    // Ora associo l'id alla bevanda
                    list.put(listFromFile.get(i)[0],tmp);
                    break;
                /**
                 * Solubile
                 */
                case 2:
                    tmp = new Solubile(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],tmp);
                    break;
                /**
                 * Capsula
                 */
                case 3:
                    tmp = new Capsula(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],tmp);
                    break;
            }
        }
    }
}
