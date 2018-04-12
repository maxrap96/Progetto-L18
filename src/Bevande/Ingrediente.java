package Bevande;

public class Ingrediente {

    private String nomeIngrediente;
    private double quantità;
    private boolean isTimeToReload;

    public Ingrediente(String name, double startQuantity) {
        this.nomeIngrediente = name;
        this.quantità = startQuantity;
        this.isTimeToReload = false;
    }

    /**
     * Sottraggo la quantità utilizzata
     * @param quantity quanto tolgo
     */

    public void subtract(double quantity){
        this.quantità -= quantity;
        if(this.quantità <= 0){
            isTimeToReload = true;
        }
    }

    /**
     * Ricarico l'ingrediente
     * @param amount nuova quantità
     */

    public void reload(double amount){
        this.quantità = amount;
    }

    public boolean isTimeToReload() {
        return isTimeToReload;
    }

    // Questi metodi li ho aggiunti, ma possono benissimo non servire

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nomeIngrediente ='" + nomeIngrediente + '\'' +
                ", quantità =" + quantità +
                '}';
    }
}
