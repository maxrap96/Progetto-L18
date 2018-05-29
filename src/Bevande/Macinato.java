package Bevande;

import static java.lang.Double.parseDouble;

public class Macinato extends HotDrink {

    public Macinato(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.MACINATO;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);
    }

    public Macinato(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted, quantityLeft);
        this.type = Tipo.MACINATO;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);
    }
}
