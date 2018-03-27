package Distributore;

import static java.lang.Integer.parseInt;

public class Coins {
    private int cent5, cent10, cent20, cent50, euro1, euro2;
    private double balance, profit;
    private double credit;
    private boolean changeAvailbe;
    private String[] AddedCoins;

    public Coins() {
        this.cent5 = 20;
        this.cent10 = 30;
        this.cent20 = 20;
        this.cent50 = 20;
        this.euro1 = 5;
        this.euro2 = 5;
        this.balance = cent5*0.05 +cent10*0.10 +cent20*0.2 + cent50*0.50 + euro1*1 + euro2*2;
        this.credit = 0;
        this.profit=0;
        this.changeAvailbe=true;
    }

    public void updateBalance(double vendita) {
        balance += vendita;
        profit += vendita;
        credit -= vendita;
    }

    /**
     * funzione per identificare le monete inserite in base alla stringa in input (da tastiera o interfaccia)
     * @param input è la stringa da analizzare per identificare, in base alla posizione.
     * taglio: 0.05 0.10. 0.20 0.50 1 2
     * input:  1     0    3    1   0 0
     * ad esempio e posso capire quante monete per ogni taglio relativo alla posizione essendo separate da spazi.
     */

    public void addCredit(String input){
        AddedCoins = input.split("\\s+"); //i tagli sono separati da spazi.
        if (AddedCoins.length == 6 ) {
            credit = parseInt(AddedCoins[0]) * 0.05 + parseInt(AddedCoins[1]) * 0.10 + parseInt(AddedCoins[2]) * 0.2 + parseInt(AddedCoins[3]) * 0.50 + parseInt(AddedCoins[4]) * 1 + parseInt(AddedCoins[5]) * 2;
        } //significa che ho inserito tutti i dati riferiti ai singoli tagli.
        else {
            System.out.println("Restituzione delle monete data l'assenza di tutti i campi");
        }
    }

    public double getCredit() {
        return credit;
    }


    //TODO funzione da fare per dare il resto nel minor numero di monete se e solo se non ho esaurito alcun tipo di monete
    public void giveChange() {
        //TODO si deve creare prima una funzione che mi corntrolli che ci sia almeno una moneta per poterlo restituire.
    }
}
