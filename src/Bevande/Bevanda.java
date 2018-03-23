package Bevande;

public abstract class Bevanda {
    protected String name;
    protected Tipo type;
    protected double costo, dose, quantityLeft, quantityMax, temperature;

    public abstract void subtractDose();
}
