package Bevande;

import static java.lang.Double.parseDouble;

public class Macinato extends Bevanda {

    /**
     * Il file di testo è composto in forma
     * ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE (NEL CASO CI SIA dato che la cialda è 1)
     * 0    1     2     3       4                   5               6
     */

    public Macinato(String[] rowSplitted) {
        this.id=rowSplitted[0];
        this.type = Tipo.MACINATO;
        this.name = rowSplitted[2];
        this.price = parseDouble(rowSplitted[3]);
        this.quantityMax = parseDouble(rowSplitted[4]);
        this.quantityLeft = quantityMax;                //lo inizializzo come pieno
        this.temperature = parseDouble(rowSplitted[5]);
        this.dose = parseDouble(rowSplitted[6]);

    }

    @Override
    public void subtractDose() {
        quantityLeft=quantityLeft-dose;
    }
}
