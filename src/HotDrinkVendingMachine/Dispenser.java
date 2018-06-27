package HotDrinkVendingMachine;

import HotDrinks.HotDrink;

/**
 * Classe che si occupa della distribuzione delle bevande.
 */

public class Dispenser implements MaxValue {
    private double milk;
    private double sugar;
    private double vodka;
    private double spoon, cup;

    public Dispenser(double milk, double sugar, double spoon, double cup, double vodka) {
        this.milk = milk;
        this.sugar = sugar;
        this.spoon = spoon;
        this.cup = cup;
        this.vodka = vodka;
    }

    /**
     * Funzione per sottrarre quantità necessarie per preparare la bevanda.
     * @param beverage bevanda da cui prendere le dosi.
     * @param sugar zucchero selezionato.
     */
    protected void subtractIngredients(HotDrink beverage, int sugar) {
        milk -= beverage.getMilk();
        subtractSugar(sugar);
        cup--;
        vodka -= beverage.getVodka();
    }

    /**
     * Funzione che controlla che sia possibile erogare gli ingredienti
     * @param beverage bevanda richiesta con le varie informazioni
     * @param sugar zucchero richiesto
     * @return segnala la possibilità o meno di eseguire una operazione
     */
    public boolean checkAvailability(HotDrink beverage, int sugar){
        double askedSugar = (double) sugar * SUGAR_DOSE;
        double askedVodka = beverage.getVodka();
        double askedMilk = beverage.getMilk();
        if (askedMilk < milk && askedSugar < sugar && askedVodka < vodka && cup >0 && spoon > 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Funzione che sottrae lo zucchero usato.
     * @param qty valore tra 0 e 5.
     */
    private void subtractSugar(int qty) {
        if (qty != 0) {
            sugar -= (double) qty * SUGAR_DOSE;
            spoon--;
        }
    }

    /**
     * Funzione che controlla se la macchinetta deve essere ricaricata.
     */
    protected void checkIfMachineIsEmpty() {
        if (cup < 20 || spoon < 10 || sugar < 0.5 || milk < 0.2) {
            System.out.println("WARNING: Low level\n");
        }
    }

    /**
     * Funzione che genera la stringa dei dati.
     * @return s stringa.
     */
    public String[] getData() {
            return new String[]{"" + milk, "" + sugar, "" + spoon, "" + cup, "" + vodka};
    }
}
