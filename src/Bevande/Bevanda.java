package Bevande;

import static java.lang.Double.parseDouble;

public abstract class Bevanda {

    protected String id;
    protected Tipo type;
    protected String name;
    protected double price, quantityMax, temperature, quantityDelivered;
    protected double quantityLeft;

    /**
     * Il file di testo è composto in forma. quindi dato che è splittato in stringhe singole per ogni campo risulta
     * semplice inizializzare una bevanda
     * TIPO:    ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE (NEL CASO CI SIA)
     * indice:  0    1     2     3       4                   5               6

     * @param rowSplitted riga proveniente dal file di testo
     */

    public Bevanda(String[] rowSplitted) {
        // Inizializzo solo le parti comuni. quindi tutto tranne type e quantityDelivered che verranno inizializzati
        // nelle singole classi in base alle differenze
        this.id = rowSplitted[0];
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.quantityMax = parseDouble(rowSplitted[4]);
        this.quantityLeft = quantityMax; //lo inizializzo come pieno
        this.temperature = parseDouble(rowSplitted[5]);
    }


    /**
     * Funzione per sottrarre quantità dopo aver selezionato la bevanda
     */

    public void subtractDose() {
        this.quantityLeft -= this.quantityDelivered;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvaible() {
        if (quantityLeft > quantityDelivered){
            return true;
        }
        else {
            return false;
        }
    }

    //TODO X LUCE. IL TOSTRING OVVERRIDE CHE RESTITUISCA: ID, NAME, PRICE

    /**
     * TODO: Eventuale interfaccia.
     */
}
