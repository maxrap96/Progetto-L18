package Bevande;

import static java.lang.Double.parseDouble;

public class Capsula extends HotDrink {

    public Capsula(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.CAPSULA;
        this.dispensedQuantity = 1;
    }


    public Capsula(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted, quantityLeft);
        this.type = Tipo.CAPSULA;
        this.dispensedQuantity = parseDouble(rowSplitted[6]); // Qua non andrebbe 1?
    }
}
