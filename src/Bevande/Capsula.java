package Bevande;

public class Capsula extends HotDrink {

    public Capsula(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.CAPSULA;
        this.dispensedQuantity = 1;
    }


    public Capsula(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted, quantityLeft);
        this.type = Tipo.CAPSULA;
        this.dispensedQuantity = 1;
    }
}
