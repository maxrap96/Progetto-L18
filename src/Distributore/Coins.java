package Distributore;

import Errori.FileNotReadable;
import Errori.FileNotWritable;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Coins {
    private int[] money;
    private double profit = 0.0;
    private double credit = 0.0;
    private final double COINS_VALUE[] = {0.05, 0.10, 0.20, 0.50 , 1.00, 2.00};

    private Data moneteTxt =
            new Data("src/File_Testo/monete.txt");

    public Coins() {
        this.money = new int[COINS_VALUE.length];
        initCoins();
    }

    public double[] getCOINS_VALUE() {
        return COINS_VALUE;
    }

    /**
     * Inizializza i tagli delle monete tramite lettura da file. Nel caso di errori si usa un'inizializzazione
     * di default.
     */
    private void initCoins() {
        try {
            ArrayList<String[]> coinsText = moneteTxt.readFile();
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
     * Funzione che crea una stringa contenente il numero di monete all'interno della macchinetta.
     * @return s: stringa contenente il numero di ogni moneta nella macchinetta.
     */
    private String moneyOnFile() {
        String s = "";

        for (int i = 0; i < money.length; i++) {
            s += (money[i] + "\t");
        }

        return s;
    }

    /**
     * Funzione che aggiora il bilancio ed il credito rimanente.
     * @param vendita è il costo della bevanda richiesta.
     */
    public void updateBalance(double vendita) {
        profit += vendita;
        float tmp = (float)(credit - vendita);
        //credit = (credit)*1000 - (vendita)*1000;    //moltiplico per 1000 per evitare errori strani di approsimazione
        credit = tmp;
    }

    /**
     * Funzione per identificare il tipo e la quantità di monete inserite.
     * @param index è la stringa da analizzare per identificare, in base alla posizione.
     */
    public void addCredit(String number, int index) {
        credit += parseInt(number) * COINS_VALUE[index];
        money[index] += parseInt(number);

        if (index == money.length - 1){
            try {
                moneteTxt.writeFile(moneyOnFile());
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
        }
    }

    /**
     *
     * @return
     */
    public double getCredit() {
        return credit;
    }

    /**
     * Restituisce il totale dei nella macchinetta.
     * @return la quantità di soldi presenti nella macchinetta.
     */
    private double getBalance() {
        double balance = 0;
        for (int i = 0; i < money.length; i++) {
            balance += money[i] * COINS_VALUE[i];
        }
        return balance;
    }

    /**
     * Funzione per erogare il resto, massimizzando il numero di monete di taglio maggiore.
     */
    public String giveChange() {

        if (checkChange()) {
            int[] change = new int[COINS_VALUE.length]; // change[0], change[1], ecc. è il numero di monete
                                                        // del tipo indicato
            optimizeChange(change);

            String coinsList = "5c: " + change[0] + "\n10c: " + change[1] + "\n20c: " + change[2] + "\n50c: "
                    + change[3] + "\n1E: " + change[4] + "\n2E: " + change[5];
            String changeSupplied = "Resto erogato:\n" + String.format("%.2f",credit);

            resetCredit(change);

            try {
                moneteTxt.writeFile(moneyOnFile());
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }

            System.out.println(changeSupplied);
            System.out.println(coinsList);
            return (changeSupplied + coinsList);
        }  else {
            return ("Resto NON disponibile");
        }

    }

    /**
     * funzione per aggiornare il credito dopo l'erogazione della bevanda
     * @param change è il vettore contente il quantitativo di monete restituito
     */
    private void resetCredit(int[] change) {
        for (int i=0 ;i < change.length; i++){
            credit = credit - change[i]*COINS_VALUE[i];
        }
    }

    /**
     * Funzione che controlla che il resto possa essere erogato.
     * @return boolean
     */
    private boolean checkChange() {
        if (credit <= getBalance()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Funzione che restituisce il resto con il numero minimo di monete.
     * @param change vettore di interi per il numero di monete da erogare come resto.
     * @return change vettore contenente il numero di monete ottimizzate per ogni taglio.
     */
    private int[] optimizeChange(int[] change){

        double resto = (credit * 100);
        int[] divisor = {5, 10, 20, 50, 100, 200};

        for (int i = COINS_VALUE.length-1; i > -1; i--) {
            change[i] = (int) (resto) / divisor[i];
            resto = resto % divisor[i];

            if (change[i] > money[i]) {
                resto += (change[i] - money[i]) * divisor[i];
                change[i] = money[i];

            }
            money[i] -= change[i];
        }
        return change;
    }

    /**
     * Funzione per aggiungere monete ed aggiornare il credito.
     *
     * Nota: utilizzata dall'interfaccia.
     *
     * @param inserted è il valore della moneta inserita.
     * @param i è l'indice corrispondente al valore della moneta inserita in COINS_VALUE[].
     */
    public void addCoin(double inserted, int i) {
        credit += inserted;
        money[i]++;
    }
}