package Bevande;

import static java.lang.Double.parseDouble;

public class Capsula extends Bevanda {

    public Capsula(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.CAPSULA;
        this.dose = 1;
    }

}
