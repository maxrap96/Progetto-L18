package Bevande;

public abstract class Bevanda {

    protected String name;
    protected String id;
    protected Tipo type;

    /**
     * Commento di Dario: "Ti prego trova un altro nome per dose, sembra che spacciamo droga e aggiungi una classe
     * Ingrediente, perchè dobbiamo fare in modo che ogni Bevanda abbia un certo numero di ingredienti anch'essi da
     * ricaricare nel caso.
     * Ho cambiato Costo in Price perchè così non mischi italiano e inglese"
     */

    protected double price, dose, quantityLeft, quantityMax, temperature;

    /**
     * Funzione da implementare nelle classi derivate per sottrarre quantità
     */

    /**
     * Commento di Dario: "Troppo poco generica. O fai 2 funzioni, una che aggiunge (caso ricarica) e una che
     * toglie, oppure fai una classe unica modifiyDose e gli passi un argomento positivo o negativo, sarebbe meglio
     * la seconda, ma nel caso ne discutiamo"
     */

    public abstract void subtractDose();

    /**
     * Commento di Dario: "Se vuoi esagerare con la finezza, fai un'interfaccia con una funzione modifyQuantity(float ..)
     * e la implmenti alle bisogna"
     */
}
