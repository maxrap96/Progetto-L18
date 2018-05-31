package Distributore;

import Bevande.HotDrink;

public class Erogatore implements MaxValue {
    private double milk;
    private double sugar;
    private double vodka;
    private int spoon, cup;

    public Erogatore(double milk, double sugar, int spoon, int cup, double vodka) {
        this.milk = milk;
        this.sugar = sugar;
        this.spoon = spoon;
        this.cup = cup;
        this.vodka = vodka;
    }

    /**
     * Funzione per sottrarre quantità necessarie per preparare la bevanda.
     * @param beverage bevanda da cui prendere le dosi.
     * @param sugar zucchero selezionato
     */
    protected void subtractIngredients(HotDrink beverage, int sugar) {
        milk -= beverage.getMilk();
        subtractSugar(sugar);
        cup--;
        vodka -= beverage.getVodka();
    }

    /**
     * Funzione che sottrae lo zucchero usato.
     * @param qty valore tra 0 e 5.
     */
    private void subtractSugar(int qty) {
        if (qty != 0) {
            sugar -= (double) qty * SUGARDOSE;
            spoon--;
        }
    }

    /**
     * Funzione che controlla se la macchinetta deve essere ricaricata.
     */
    protected void checkIfMachineIsEmpty() {
        if (cup < 20 || spoon < 10 || sugar < 0.5 || milk < 0.2) {
            System.out.println("Refilling machine...\n");
            refill();
        }
    }

    /**
     * Ricarica alcuni elementi della macchinetta.
     */
    private void refill() {
        this.sugar = SUGARMAX;
        this.milk = MILKMAX;
        this.cup = CUPMAX;
        this.spoon = SPOONMAX;
    }

    /**
     * Funzione che genera la stringa dei dati.
     * @return s restituisce una stringa.
     */
    public String[] getData() {
            return new String[]{"" + milk, "" + sugar, "" + spoon, "" + cup, "" + vodka};
    }
}
