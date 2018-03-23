package Bevande;

import static java.lang.Double.parseDouble;

public class Cialda extends Bevanda {
    /** Il file di testo è composto in forma
     * ID   TIPO  NOME  COSTO   QUANTITA_MASSIMA    TEMPERATURA    DOSE (NEL CASO CI SIA dato che la cialda è 1)
     * 0    1     2     3       4                   5               6
     *
     */
    public Cialda (String[] rowSplitted) {
        this.type=Tipo.CIALDA;
        this.name=rowSplitted[2];
        this.costo=parseDouble(rowSplitted[3]);
        this.quantityMax=parseDouble(rowSplitted[4]);
        this.quantityLeft=quantityMax; //lo inizializzo come pieno
        this.temperature=parseDouble(rowSplitted[5]);
        this.dose=1;
    }


    @Override
    public void subtractDose() {
        quantityLeft=quantityLeft-1;
    }
}
