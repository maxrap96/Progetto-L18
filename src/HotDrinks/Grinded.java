package HotDrinks;

import static java.lang.Double.parseDouble;

public class Grinded extends HotDrink {

    public Grinded(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Type.GRINDED;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);
    }

    public Grinded(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted, quantityLeft);
        this.type = Type.GRINDED;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);
    }
}
