package HotDrinks;

import static java.lang.Double.parseDouble;

public class Soluble extends HotDrink {

    public Soluble(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Type.SOLUBLE;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);
    }

    public Soluble(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted, quantityLeft);
        this.type = Type.SOLUBLE;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);
    }
}
