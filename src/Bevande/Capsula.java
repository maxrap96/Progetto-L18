package Bevande;

public class Capsula extends Bevanda {

    public Capsula(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.CAPSULA;
        this.usedQuantity = 1;
    }

}
