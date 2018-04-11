package Distributore;

import Errori.FileNotReadable;
import Errori.FileNotWritable;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Coins {
    private int money[] = new int[6];
    private double profit=0;
    private double credit=0;
    private String[] addedCoins;
    private final double COINS_VALUE[] = {0.05, 0.10, 0.20, 0.50 , 1.00, 2.00};

    private Data data = new Data("src/Distributore/monete.txt");

    public Coins() {
        initCoins();
    }


    public double[] getCOINS_VALUE() {
        return COINS_VALUE;
    }

    /**
     * Inizializza i tagli delle monete tramite lettura da file. Nel caso di errori usiamo una inizializzazione
     * di default.
     */
    public void initCoins() {
        try {
            ArrayList<String[]> coinsText = data.readFile();
            int last = coinsText.size() - 1;    // last e' l'ultima riga del file che mi serve.

            for (int i = 0; i < money.length; i++) {
                money[i] = parseInt(coinsText.get(last)[i]);
            }
        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();

            this.money[0] = 20;
            this.money[1] = 30;
            this.money[2] = 20;
            this.money[3] = 10;
            this.money[4] = 5;
            this.money[5] = 5;
        }

        // TODO: Catch nel caso il file venga scritto male
    }

    /**
     * Funzione che scrive il numero delle monete rimanenti sul file.
     * @return s: stringa contenente numero di monete per il file di statistiche.
     */

    public String moneyOnFile() {
        String s = "";

        for (int i = 0; i < money.length; i++) {
            s += (money[i] + "\t");
        }

        return s;
    }


    public void updateBalance(double vendita) {
        profit += vendita;
        credit = (credit)*100 - (vendita)*100;
        credit = credit/100;
    }

    /**
     * Funzione per identificare il tipo e la quantità di monete inserite
     * @param index è la stringa da analizzare per identificare, in base alla posizione.
     */

    public void addCredit(String number, int index) {
        credit += parseInt(number) * COINS_VALUE[index];
        money[index] += parseInt(number);

        if (index == money.length - 1){
            try {
                data.writeFile(moneyOnFile());
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
        }
    }

    public double getCredit() {
        return credit;
    }

    /**
     * Che cosa fa?
     * @return
     */

    private double getBalance() {
        double balance = 0;
        for (int i = 0; i < money.length; i++) {
            balance += money[i] * COINS_VALUE[i];
        }
        return balance;
    }

    /**
     * Che cosa fa?
     * Per altro troppa roba in cascata, un If con dentro un For con dentro un If, fate delle sotto funzioni
     */

    public void giveChange() {

        if (checkChange()) {
            int[] change = new int[6];                      //(change[0], change[1], ecc...) è il numero di
                                                            // monete  del tipo indicato
            int[] divisor = {5, 10, 20, 50, 100, 200};
            double resto;

            resto = (credit * 100);

            for (int i = 5; i > -1; i--) {
                change[i] = (int) (resto) / divisor[i];
                resto = resto % divisor[i];

                if (change[i] > money[i]) {
                    resto += (change[i] - money[i]) * divisor[i];
                    change[i] = money[i];
                }
                money[i] -= change[i];
            }

            try {
                data.writeFile(moneyOnFile());
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }

            //TODO Abbellire output
            System.out.println("Erogazione resto: " +credit);
            System.out.println("5c: " + change[0] + "\n10c: " + change[1] + "\n20c: " + change[2] + "\n50c: "
                    + change[3] + "\n1E: " + change[4] + "\n2E: " + change[5]);


        }  else {
            System.out.println("Resto NON disponibile");
        }

    }

    /**
     * Che cosa fa?
     * @return
     */

    private boolean checkChange() {
        if (credit<=getBalance()){
            return true;
        }
        else {
            return false;
        }
    }
}



