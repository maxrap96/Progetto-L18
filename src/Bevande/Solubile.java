package Bevande;

import static java.lang.Double.parseDouble;

public class Solubile extends Bevanda {

    public Solubile(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.SOLUBILE;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);

    }
}
