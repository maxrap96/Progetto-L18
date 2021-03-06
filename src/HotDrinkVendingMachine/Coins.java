package HotDrinkVendingMachine;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Classe che si occupa della gestione delle monete.
 */

public class Coins implements CoinsNumbers, TextPathFiles{
    private int[] money;
    private int credit = 0;
    private final double[] COINS_VALUE = {5, 10, 20, 50 , 100, 200};
    private FileManager coinsTxt = new FileManager(COINS_PATH);

    public Coins() {
        this.money = new int[COINS_VALUE.length];
        initCoins();
    }

    /**
     * Funzione che restituisce un vettore contenente il valore dei soldi inseribili nella macchinetta.
     * @return vettore di valori.
     */
    public double[] getCOINS_VALUE() {
        double[] cValue = new double[COINS_VALUE.length];
        for (int i = 0; i < COINS_VALUE.length; i++) {
            cValue[i] = COINS_VALUE[i] / 100;
        }
        return cValue;
    }

    /**
     * Inizializza i tagli delle monete tramite lettura da file.
     *
     * Nota: Nel caso di errori si usa un'inizializzazione di default.
     */
    private void initCoins() {
        ArrayList<String[]> arrayCoinsText = coinsTxt.readFile();
        int last = arrayCoinsText.size() - 1;    // Last e' l'ultima riga del file che serve

        for (int i = 0; i < money.length; i++) {
            money[i] = parseInt(arrayCoinsText.get(last)[i]);
        }
    }

    /**
     * Funzione che crea una stringa contenente il numero di monete all'interno della macchinetta.
     * @return s contiene il numero di ogni moneta nella macchinetta.
     */
    public String moneyOnFile() {
        String s = "";

        for (int i = 0; i < money.length; i++) {
             s += (money[i] + "\t");
        }
        return s;
    }

    /**
     * Funzione che aggiora il bilancio ed il credito rimanente.
     * @param sale costo della bevanda richiesta.
     */
    public void updateBalance(double sale) {
        credit = credit - (int)((sale) * 100);
    }

    /**
     * Restituisce il credito in formato double.
     */
    public double getCredit() {
        double c = ((double)credit) / 100 ;
        return c;
    }

    /**
     * Restituisce il totale del bilancio nella macchinetta.
     */
    private double getBalance() {
        double balance = 0;
        for (int i = 0; i < money.length; i++) {
             balance += money[i] * COINS_VALUE[i];
        }
        return balance;
    }

    /**
     * Funzione che eroga il resto, massimizzando il numero di monete di taglio maggiore.
     */
    public void giveChange() {
        if (checkChange()) {
            // change[0], change[1], ecc. è il numero di monete del tipo indicato
            int[] change = new int[COINS_VALUE.length];
            optimizeChange(change);
            if (getCredit() != 0) {
                String coinsList = "5c: " + change[0] + "\n10c: " + change[1] + "\n20c: " + change[2] + "\n50c: "
                        + change[3] + "\n1E: " + change[4] + "\n2E: " + change[5];
                String changeSupplied = "Resto erogato: " + ((double) credit / 100);
                resetCredit(change);

                System.out.println(changeSupplied);
                System.out.println(coinsList);
            }
                coinsTxt.writeFile(moneyOnFile());
        }  else {
            System.out.println("Resto NON disponibile");
        }
    }

    /**
     * Funzione per aggiornare il credito dopo l'erogazione della bevanda.
     * @param change contiene il quantitativo di monete restituito.
     */
    private void resetCredit(int[] change) {
        for (int i = 0 ; i < change.length; i++) {
             credit = credit - (int)(change[i] * COINS_VALUE[i]);
        }
    }

    /**
     * Funzione che controlla che il resto possa essere erogato.
     */
    private boolean checkChange() {
        if (credit <= getBalance()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Funzione che restituisce il resto con il numero minimo di monete.
     * @param change vettore di interi per il numero di monete da erogare come resto.
     * @return change contiene il numero di monete ottimizzate per ogni taglio.
     */
    private int[] optimizeChange(int[] change) {
        int remnant = (credit);
        int[] divisor = {5, 10, 20, 50, 100, 200};

        for (int i = COINS_VALUE.length - 1; i > -1; i--) {
            change[i] = (remnant) / divisor[i];
            remnant = remnant % divisor[i];

            if (change[i] > money[i]) {
                remnant += (change[i] - money[i]) * divisor[i];
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
     * @param inserted valore della moneta inserita.
     */
    public void addCoin(double inserted) {
        credit += inserted * 100;
        // Identificazione della moneta inserita
        for (int i = 0; i < COINS_VALUE.length; i++) {
            if ((inserted * 100) == COINS_VALUE[i]) {
                // Ha trovato il valore corrispondente
                money[i]++;
                break;
            }
        }
        coinsTxt.writeFile(moneyOnFile());
    }

    /**
     * Metodo alternativo all'addCoin utilizzato per caricare il saldo della chiavetta.
     * @param inserted valore della moneta inserita.
     */
    public void chargeKey(double inserted) {
        // Identificazione della moneta inserita
        for (int i = 0; i < COINS_VALUE.length; i++) {
            if (inserted == COINS_VALUE[i]) {
                // Ha trovato il valore corrispondente
                money[i]++;
                break;
            }
        }
    }

    /**
     * Funzione che ripristina money ai valori di default.
     */
    public void resetStoredMoney(){
        this.money[0] = DEFAULT_MONEY[0];
        this.money[1] = DEFAULT_MONEY[1];
        this.money[2] = DEFAULT_MONEY[2];
        this.money[3] = DEFAULT_MONEY[3];
        this.money[4] = DEFAULT_MONEY[4];
        this.money[5] = DEFAULT_MONEY[5];
    }
}
