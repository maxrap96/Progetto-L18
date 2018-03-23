package Distributore;

import Bevande.*;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class Distributore {
    private HashMap<String,Bevanda> list;
    private int cup,cupMax, spoon,spoonmax;
    private double water,watermax;
    private double sugar,sugarMax;
    private double credit,bank;
    private ArrayList<String[]> listFromFile;
    private String[] statistics;
    private OpenFile input=new OpenFile(); //è una vecchia parte di un programma di Hexrebuilt. apre files e li splitta in base alla tabulazione. inoltre ha  già un metodo per recepire comandi da tastiera.

    public Distributore(String pathFile) {
        this.list = new HashMap<>();
        this.listFromFile=input.apriFile(pathFile);
        SetVendingMachine();
    }

    private void SetVendingMachine() {
        /** il file è impostato in modo tale che la prima riga siano le informazioni della macchinetta
         * bicchierini  cucchianini acqua   zucchero    e poi server
         * 0            1           2       3           4
         *
        */
        this.cupMax=parseInt(listFromFile.get(0)[0]);
        this.cup=cupMax;
        this.spoonmax=parseInt(listFromFile.get(0)[1]);
        this.spoon=spoonmax;
        this.watermax=parseInt(listFromFile.get(0)[2]);
        this.water=watermax;
        this.sugarMax=parseInt(listFromFile.get(0)[3]);
        this.sugar=sugarMax;
        //todo add server quando ci sarà


        //mi devo ricordare che dalla seconda riga in poi sono le bevande
        CreateList();
    }

    private void CreateList() {
        Bevanda tmp;
        Tipo type;
        //per ogni riga di file leggo e analizzo il contenuto epr creare la bevanda. ricordandomi che la riga 0 è della macchinetta. quindi inizio da 1
        for (int i=1; i<listFromFile.size();i++){
            if (listFromFile.get(i)[1].equals(Tipo.CIALDA)) {
                type = Tipo.CIALDA;
            }
            else if (listFromFile.get(i)[1].equals(Tipo.MACINATO)){
                type=Tipo.MACINATO;
            }
            else {
                type=Tipo.SOLUBILE; // è l'ultimo che resta
            }

            switch (type.ordinal()){
                case 1: //Macinato
                    tmp=new Macinato(listFromFile.get(i));
                    //ora associo l'id alla bevanda
                    list.put(listFromFile.get(i)[0],tmp);

                    break;
                case 2: //solubile
                    tmp=new Solubile(listFromFile.get(i));
                    //ora associo l'id alla bevanda
                    list.put(listFromFile.get(i)[0],tmp);
                    break;
                case 3: //cialda
                    tmp=new Cialda(listFromFile.get(i));
                    //ora associo l'id alla bevanda
                    list.put(listFromFile.get(i)[0],tmp);
                    break;
            }
        }
    }
}
