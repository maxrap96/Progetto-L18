package Bevande;

import static java.lang.Double.parseDouble;

public class Macinato extends Bevanda {

    public Macinato(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.MACINATO;
        this.usedQuantity = parseDouble(rowSplitted[6]);

    }

}
