package Bevande;

public abstract class Bevanda {

    /**
     * Il file di testo è composto in forma
     * ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE (NEL CASO CI SIA dato che la cialda è 1)
     * 0    1     2     3       4                   5               6
     */

    protected String id;
    protected Tipo type;
    protected String name;
    protected double price, quantityMax, temperature, dose; // Trovare altro nome per dose
    protected double quantityLeft;

    /**
     * Funzione da implementare nelle classi derivate per sottrarre quantità
     */

    public abstract void subtractDose();

    /**
     * Commento di Dario: "Se vuoi esagerare con la finezza, fai un'interfaccia con una funzione modifyQuantity(float ..)
     * e la implmenti alle bisogna"
     */
}
