package HotDrinks;

import static java.lang.Double.parseDouble;

public class Solubile extends HotDrink {

    public Solubile(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.SOLUBILE;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);

    }

    public Solubile(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted, quantityLeft);
        this.type = Tipo.SOLUBILE;
        this.dispensedQuantity = parseDouble(rowSplitted[6]);


    }
}