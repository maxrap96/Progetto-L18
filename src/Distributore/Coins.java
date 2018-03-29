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
        this.cent50 = 20;
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
     * funzione per identificare le monete inserite in base alla stringa in input (da tastiera o interfaccia)
     * @param input è la stringa da analizzare per identificare, in base alla posizione.
     * taglio: 0.05 0.10. 0.20 0.50 1 2
     * input:  1     0    3    1   0 0
     * ad esempio e posso capire quante monete per ogni taglio relativo alla posizione essendo separate da spazi.
     */

    public void addCredit(String input){
        addedCoins = input.split("\\s+"); //i tagli sono separati da spazi.
        if (addedCoins.length == 6 ) {
            credit = parseInt(addedCoins[0]) * 0.05 + parseInt(addedCoins[1]) * 0.10 + parseInt(addedCoins[2]) * 0.2 + parseInt(addedCoins[3]) * 0.50 + parseInt(addedCoins[4]) * 1 + parseInt(addedCoins[5]) * 2;
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

        //come mai un or? così dacendo ne basta una che sia diversa da 0 no? pensavo ad un and dato che se solo una non è disponibile non do resto
        //basterebbe un if(checkChange()) che restituisce un true o false nel caso sia possibile o meno erogare il resto. quella funzione aggiorna
        //changeAvaible così è possibile tenere traccia della possibilità di dare resto. può essere utile anche per le interfacce

        if(cent5 != 0 || cent10 != 0 || cent20 != 0 || cent50 != 0 || euro1 != 0 || euro2 != 0){
            int Rcent5, Rcent10, Rcent20, Rcent50, Reuro1, Reuro2;
            double resto;

            Reuro2 =(int)(credit*100)/200; //questo è il numero di monete che da oppure è quanto do di resto?
            resto = (int)(credit*100) % 200;

            //per calcolare le monete, nel caso quello sia il quantitativo in euro, si potrebbe fare euro2+=Reuro2/2

            Reuro1 =(int)(resto)/100;
            resto = (int)(resto) % 100;

            //per calcolare le monete, nel caso quello sia il quantitativo in euro, si potrebbe fare cent50+=Rcent50/0.50 NB non so se funzia

            Rcent50 =(int)(resto)/50;
            resto = (int)(resto) % 50;
            
            Rcent20 =(int)(resto)/20;
            resto = (int)(resto) % 20;

            Rcent10 =(int)(resto)/10;
            resto = (int)(resto) % 10;

            Rcent5 =(int)(resto)/5;
            resto = (int)(resto) % 5;
            //abbellirei un pochino l'outpoot ma sticazzi va bene così per adesso. Sarebbe cos' schifoso metterlo sotto forma di stringa?
            //perchè così sarebbe più comoda da usare nell'interfaccia e basterebbe un s....out.println(resto). si possono accodare caratteri alle
            //stringhe senza troppi fastidi
            System.out.println("5c:"+Rcent5+"\n10c:" +Rcent10+"\n20c:" +Rcent20+"\n50c:" +Rcent50+"\n1E:" +Reuro1+"\n2E:" +Reuro2);
        }
    }
}
