package Bevande;

import static java.lang.Double.parseDouble;

public class Macinato extends Bevanda {

    public Macinato(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.MACINATO;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);

    }

}
