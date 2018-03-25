package Bevande;

import static java.lang.Double.parseDouble;

public class Solubile extends Bevanda {

    /**
     * Il file di testo è composto in forma
     * ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE (NEL CASO CI SIA dato che la cialda è 1)
     * 0    1     2     3       4                   5               6
     */

    public Solubile(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.SOLUBILE;
        this.dose = parseDouble(rowSplitted[6]);

    }

    /**
     * Commento di Dario: "Ma così non rimane sempre lo stesso valore?
     */

}
