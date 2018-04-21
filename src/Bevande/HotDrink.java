package Bevande;

import static java.lang.Double.parseDouble;

public abstract class HotDrink {

    protected String id;
    protected Tipo type;
    protected String name;
    protected double price, maxQuantity, temperature, dispensedQuantity, water, milk;
    protected double leftQuantity;

    /**
     * TIPO:    ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE
     * indice:  0    1     2     3       4                   5               6
     * @param rowSplitted riga proveniente dal file di testo.
     */
    public HotDrink(String[] rowSplitted) {
        // Inizializzo tutto tranne type e dispensedQuantity che verranno inizializzati
        // nelle singole classi in base alle differenze.
        this.id = rowSplitted[0];
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.maxQuantity = parseDouble(rowSplitted[4]);
        this.leftQuantity = maxQuantity; // todo Inizializzo come pieno ma c'è da sistemarlo
        this.temperature = parseDouble(rowSplitted[5]);
        this.milk = parseDouble(rowSplitted[7]);
        this.water = parseDouble(rowSplitted[8]);
    }

    /**
     * Costruttore alternativo che serve a ripristinare la quantità rimanente, se presente, all'ultimo uso della
     * macchina.
     * @param rowSplitted è la stringa contenente i dati della bevanda.
     * @param valueleft è la stringa contenente il double della quantità rimanente.
     */
    public HotDrink(String[] rowSplitted, String valueleft) {
        this.id = rowSplitted[0];
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.maxQuantity = parseDouble(rowSplitted[4]);
        this.leftQuantity = parseDouble(valueleft); // todo Inizializzo come pieno ma c'è da sistemarlo
        this.temperature = parseDouble(rowSplitted[5]);
        this.milk = parseDouble(rowSplitted[7]);
        this.water = parseDouble(rowSplitted[8]);
    }

    /**
     * Funzione per sottrarre quantità dopo aver selezionato la bevanda.
     */
    public void subtractDose() {
        this.leftQuantity -= this.dispensedQuantity;
    }

    /**
     * @return l'attributo price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Funzione che mi dice se la bevanda é disponibile.
     */
    public boolean isAvailable() {
        if (leftQuantity > dispensedQuantity){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return l'attributo milk.
     */
    public double getMilk() {
        return milk;
    }

    /**
     * @return Una stringa che mi descrive la bevanda.
     */
    @Override
    public String toString() {
        return "ID: " + id + "\tName: " + name + "\nPrice: " + String.format("%.2f", price) + "\n";
    }

    /**
     * @return l'attributo name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return l'attributo id.
     */
    public String getId() {
        return id;
    }

    /**
     * @return l'attributo leftQuantity.
     */
    public double getLeftQuantity() {
        return leftQuantity;
    }
}
