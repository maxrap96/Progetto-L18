package Bevande;

import static java.lang.Double.parseDouble;

public class Capsula extends Bevanda {

    public Capsula(String[] rowSplitted) {
        this.id = rowSplitted[0];
        this.type = Tipo.CAPSULA;
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.quantityMax = parseDouble(rowSplitted[4]);
        this.quantityLeft = quantityMax; //lo inizializzo come pieno
        this.temperature = parseDouble(rowSplitted[5]);
        this.dose = 1;
    }


    @Override
    public void subtractDose() {
        this.quantityLeft -= 1;
    }
}
