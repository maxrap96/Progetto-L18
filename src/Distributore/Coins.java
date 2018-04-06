package Distributore;

import static java.lang.Integer.parseInt;

public class Coins {
    private int cent5, cent10, cent20, cent50, euro1, euro2;
    private double balance, profit;
    private double credit;
    private boolean changeAvailbe;
    private String[] addedCoins;

    public Coins() {
        this.cent5 = 20;
        this.cent10 = 30;
        this.cent20 = 20;
        this.cent50 = 10;
        this.euro1 = 5;
        this.euro2 = 5;
        this.balance = cent5*0.05 + cent10*0.10 + cent20*0.2 + cent50*0.50 + euro1*1 + euro2*2;
        this.credit = 0;
        this.profit = 0;
        this.changeAvailbe = true;
    }

    public void updateBalance(double vendita) {
        balance += vendita;
        profit += vendita;
        credit -= vendita;
    }

    /**
     * Funzione per identificare le monete inserite in base alla stringa in input (da tastiera o interfaccia)
     * @param input è la stringa da analizzare per identificare, in base alla posizione.
     * taglio: 0.05 0.10. 0.20 0.50 1 2
     * input:  1     0    3    1   0 0
     * ad esempio posso capire quante monete per ogni taglio relativo alla posizione essendo separate da spazi.
     */

    public void addCredit(String input){
        addedCoins = input.split("\\s+"); //i tagli sono separati da spazi.
        if (addedCoins.length == 6 ) {
            credit = parseInt(addedCoins[0]) * 0.05 + parseInt(addedCoins[1]) * 0.10 + parseInt(addedCoins[2]) * 0.2 +
                    parseInt(addedCoins[3]) * 0.50 + parseInt(addedCoins[4]) * 1 + parseInt(addedCoins[5]) * 2;
        } // Significa che ho inserito tutti i dati riferiti ai singoli tagli.
        else {
            System.out.println("Restituzione delle monete data l'assenza di tutti i campi");
        }
    }

    public double getCredit() {
        return credit;
    }


    //TODO funzione da fare per dare il resto nel minor numero di monete se e solo se non ho esaurito
    //TODO alcun tipo di monete
    public void giveChange() {
        //TODO si deve creare prima una funzione che mi corntrolli che ci sia almeno una moneta per poterlo restituire.

        // Come mai un OR? Così facendo ne basta una che sia diversa da 0 no? Pensavo ad un AND dato che,
        // se solo una non è disponibile non do resto. Basterebbe un if(checkChange()) che restituisca true o false
        // nel caso sia possibile o meno erogare il resto. Quella funzione aggiorna changeAvaible così è possibile
        // tenere traccia della possibilità di dare resto. Può essere utile anche per le interfacce

            if(cent5 != 0 || cent10 != 0 || cent20 != 0 || cent50 != 0 || euro1 != 0 || euro2 != 0){
                int Rcent5, Rcent10, Rcent20, Rcent50, Reuro1, Reuro2; //(Rcent5, Rcent10, ecc...) è il numero di monete
                double resto;                                          // del tipo segnato

                Reuro2 =(int)(credit*100)/200;
                resto = (credit*100) % 200;


                if(Reuro2 <= euro2) {
                }
                else{
                    resto += (Reuro2-euro2)*200;
                    Reuro2 = euro2;
                }

                Reuro1 = (int) (resto) / 100;
                resto = (resto) % 100;

                if(Reuro1 <= euro1) {
                }
                else {
                    resto += (Reuro1-euro1)*100;
                    Reuro1 = euro1;
                }

                Rcent50 = (int)(resto)/50;
                resto = (resto) % 50;

                if(Rcent50 <= cent50){}
                else {
                    resto += (Rcent50-cent50)*50;
                    Rcent50 = cent50;
                }

                Rcent20 =(int)(resto)/20;
                resto = (resto) % 20;


                if(Rcent20 <= cent20){}
                else {
                    resto += (Rcent20-cent20)*20;
                    Rcent20 = cent20;
                }

                Rcent10 =(int)(resto)/10;
                resto = (resto) % 10;


                if(Rcent10 <= cent10){}
                else {
                    resto += (Rcent10-cent10)*10;
                    Rcent10 = cent10;
                }
                Rcent5 =(int)(resto)/5;


                if(Rcent5 <= cent5){}
                else {
                    resto += (Rcent50-cent50)*5;
                    Rcent50 = cent50;
                    System.out.println("Resto NON erogabile pari a: "+(resto/100)+" euro\r");
                }

                // Abbellirei un pochino l'output.
                // Metterlo sotto forma di stringa? Forse più comodo da usare nell'interfaccia con un
                // s....out.println(resto). Si possono accodare caratteri alle stringhe senza troppi fastidi (?)

                System.out.println("5c:" + Rcent5 + "\n10c:" + Rcent10 + "\n20c:" + Rcent20 + "\n50c:" + Rcent50 + "\n1E:"
                        + Reuro1 + "\n2E:" + Reuro2);
            }
    }
}




