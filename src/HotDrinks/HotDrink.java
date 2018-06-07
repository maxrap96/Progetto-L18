package HotDrinks;

import static java.lang.Double.parseDouble;

public abstract class HotDrink {
    protected String id;
    protected Type type;
    protected String name;
    protected double price, maxQuantity, temperature, dispensedQuantity, water, milk, vodka;
    protected double leftQuantity;

    /**
     * TIPO:    ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE
     * indice:  0    1     2     3       4                   5              6
     * @param rowSplitted riga proveniente dal file di testo.
     */
    public HotDrink(String[] rowSplitted) {
        // Inizializzazione di tutto tranne type e dispensedQuantity che verranno inizializzati
        // nelle singole classi in base alle differenze.
        this.id = rowSplitted[0];
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.maxQuantity = parseDouble(rowSplitted[4]);
        this.leftQuantity = maxQuantity;
        this.temperature = parseDouble(rowSplitted[5]);
        this.milk = parseDouble(rowSplitted[7]);
        this.water = parseDouble(rowSplitted[8]);
        this.vodka = parseDouble(rowSplitted[9]);
    }

    /**
     * Costruttore alternativo che serve a ripristinare la quantità rimanente, se presente, all'ultimo uso della
     * macchina.
     * @param rowSplitted contiene i dati della bevanda.
     * @param valueLeft contiene il dato della quantità rimanente.
     */
    public HotDrink(String[] rowSplitted, String valueLeft) {
        this.id = rowSplitted[0];
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.maxQuantity = parseDouble(rowSplitted[4]);
        this.leftQuantity = parseDouble(valueLeft);
        this.temperature = parseDouble(rowSplitted[5]);
        this.milk = parseDouble(rowSplitted[7]);
        this.water = parseDouble(rowSplitted[8]);
        this.vodka = parseDouble(rowSplitted[9]);
    }

    /**
     * Funzione per sottrarre quantità dopo aver selezionato la bevanda.
     */
    public void subtractDose() {
        this.leftQuantity -= this.dispensedQuantity;
    }

    /**
     * Funzione che verifica se la bevanda é disponibile.
     */
    public boolean isAvailable() {
        if (leftQuantity > dispensedQuantity) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return Stringa che descrive la bevanda.
     */
    @Override
    public String toString() {
        return "ID: " + id + "\tName: " + name + "\nPrice: " + String.format("%.2f", price) + "\n";
    }

    public double getPrice() {
        return price;
    }

    public double getMilk() {
        return milk;
    }

    public double getVodka() {
        return vodka;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getLeftQuantity() {
        return leftQuantity;
    }
}
