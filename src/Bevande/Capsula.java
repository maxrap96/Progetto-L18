package Bevande;

import static java.lang.Double.parseDouble;

public class Capsula extends Bevanda {

    public Capsula(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.CAPSULA;
        this.dispensedQuantity = 1;
    }


    public Capsula(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted,quantityLeft);
        this.type = Tipo.CAPSULA;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);
    }
}
