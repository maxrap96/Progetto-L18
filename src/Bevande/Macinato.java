package Bevande;

import static java.lang.Double.parseDouble;

public class Macinato extends Bevanda {

    /**
     * Il file di testo è composto in forma
     * ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE (NEL CASO CI SIA dato che la cialda è 1)
     * 0    1     2     3       4                   5               6
     */

    public Macinato(String[] rowSplitted) {
        super(rowSplitted);
        this.type = Tipo.MACINATO;
        this.dose = parseDouble(rowSplitted[6]);

    }

}
