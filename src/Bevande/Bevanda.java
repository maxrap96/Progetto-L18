package Bevande;

import static java.lang.Double.parseDouble;

public abstract class Bevanda {

    protected String id;
    protected Tipo type;
    protected String name;
    protected double price, quantityMax, temperature, quantityUsed, water, milk;
    protected double quantityLeft;

    /**
     * TIPO:    ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE
     * indice:  0    1     2     3       4                   5               6
     * @param rowSplitted riga proveniente dal file di testo
     */

    public Bevanda(String[] rowSplitted) {
        // Inizializzo tutto tranne type e quantityUsed che verranno inizializzati
        // nelle singole classi in base alle differenze.
        this.id = rowSplitted[0];
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.quantityMax = parseDouble(rowSplitted[4]);
        this.quantityLeft = quantityMax; //lo inizializzo come pieno
        this.temperature = parseDouble(rowSplitted[5]);
        this.milk = parseDouble(rowSplitted[7]);
        this.water = parseDouble(rowSplitted[8]);
    }


    /**
     * Funzione per sottrarre quantità dopo aver selezionato la bevanda
     */

    public void subtractDose() {
        this.quantityLeft -= this.quantityUsed;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Funzione che mi dice se la bevanda é disponibile
     */

    public boolean isAvaible() {
        if (quantityLeft > quantityUsed){
            return true;
        }
        else {
            return false;
        }
    }

    public double getMilk() {
        return milk;
    }

    public double getWater() {
        return water;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\tName: " + name + "\nPrice: " + price + "\n";
    }


    /**
     * TODO: Eventuale interfaccia.
     */
}
