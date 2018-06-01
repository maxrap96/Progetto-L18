package HotDrinks;

public class Capsule extends HotDrink {

    public Capsule(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Type.CAPSULE;
        this.dispensedQuantity = 1;
    }

    public Capsule(String[] rowSplitted, String quantityLeft) {
        super(rowSplitted, quantityLeft);
        this.type = Type.CAPSULE;
        this.dispensedQuantity = 1;
    }
}
