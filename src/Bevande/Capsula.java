package Bevande;

import static java.lang.Double.parseDouble;

public class Capsula extends Bevanda {

    /**
     * Il file di testo è composto in forma
     * ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE (NEL CASO CI SIA dato che la cialda è 1)
     * 0    1     2     3       4                   5               6
     */

    /**
     * Non vedo nessun riferimento ad ID, dove lo salvi?
     * @param rowSplitted: "A cosa serve?"
     */

    public Capsula(String[] rowSplitted) {
        this.type = Tipo.CAPSULA;
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.quantityMax = parseDouble(rowSplitted[4]);
        this.quantityLeft = quantityMax; //lo inizializzo come pieno
        this.temperature = parseDouble(rowSplitted[5]);
        this.dose = 1;
    }


    @Override
    public void subtractDose() {
        this.quantityLeft -= 1;
    }
}
