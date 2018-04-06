package Distributore;

import static java.lang.Integer.parseInt;

public class Coins {
    private int money[] = new int[6];
    private double balance, profit;
    private double credit;
    private boolean changeAvailable;
    private String[] addedCoins;

    public Coins() {
        this.money[0] = 20;
        this.money[1] = 30;
        this.money[2] = 20;
        this.money[3] = 10;
        this.money[4] = 5;
        this.money[5] = 5;
        this.balance = money[0]*0.05 + money[1]*0.10 + money[2]*0.2 + money[3]*0.50 + money[4]*1 + money[5]*2;
        this.credit = 0;
        this.profit = 0;
        this.changeAvailable = true;
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

            //Utilizzo le motenete inserite al credit per dare resto
           for(int i = 0; i < 6; i++){
               money[i] += parseInt(addedCoins[i]);
           }

        } // Significa che ho inserito tutti i dati riferiti ai singoli tagli.
        else {
            System.out.println("Restituzione delle monete data l'assenza di tutti i campi");
        }
    }

    public double getCredit() {
        return credit;
    }


    public void giveChange() {

        //Basterebbe un if(checkChange()) che restituisca true o false
        // nel caso sia possibile o meno erogare il resto. Quella funzione aggiorna changeAvailable così è possibile
        // tenere traccia della possibilità di dare resto. Può essere utile anche per le interfacce

            if(checkChange()){
                int[] change = new int[6];                        //(change[0], change[1], ecc...) è il numero di monete
                int [] divisor = {5,10,20,50,100,200};            // del tipo indicato
                double resto;

                resto = (credit*100);

                for(int i = 5; i > -1; i--){
                    change[i] = (int)(resto)/ divisor[i];
                    resto = resto % divisor[i];

                    if(change[i] > money[i]){
                        change[i] = money[i];
                        resto += (change[i] - money[i])* divisor[i];
                    }
                    money[i] -= change[i];
                }

                if(credit > balance){
                    double abb = credit-balance;
                    System.out.println("Resto NON erogabile pari a: "+(float)abb);
                }

                //TODO Abbellire output
                System.out.println("5c:" + change[0] + "\n10c:" + change[1] + "\n20c:" + change[2] + "\n50c:"
                        + change[3] + "\n1E:" +change[4] + "\n2E:" + change[5]);



                //TODO Decidere se va bene l'utilizzo di array oppure tenere il metodo vecchio
                /*
                Reuro2 = (int)credit*100/200;
                    resto = credit % 200;

                    if(Reuro2 > euro2){
                    resto += (Reuro2-euro2)*200;
                    Reuro2 = euro2;
                }
                euro2 -= Reuro2;

                Reuro1 = (int) (resto) / 100;
                resto = (resto) % 100;


                if(Reuro1 > euro1) {
                    resto += (Reuro1-euro1)*100;
                    Reuro1 = euro1;
                }
                euro1 -= Reuro1;

                Rcent50 = (int)(resto)/50;
                resto = (resto) % 50;

                if(Rcent50 > cent50){
                    resto += (Rcent50-cent50)*50;
                    Rcent50 = cent50;
                }

                cent50 -= Rcent50;

                Rcent20 =(int)(resto)/20;
                resto = (resto) % 20;



                if(Rcent20 > cent20){
                    resto += (Rcent20-cent20)*20;
                    Rcent20 = cent20;
                }
                cent20 -= Rcent20;

                Rcent10 =(int)(resto)/10;
                resto = (resto) % 10;



                if(Rcent10 > cent10){
                    resto += (Rcent10-cent10)*10;
                    Rcent10 = cent10;
                }
                cent10 -= Rcent10;
                Rcent5 =(int)(resto)/5;


                if(Rcent5 > cent5){
                    resto += (Rcent5-cent5)*5;
                    Rcent5 = cent5;

                    //Se avanza resto dico che resto rimane affinche possa essere riutilizzato

                    System.out.println("Resto NON erogabile pari a: "+(resto/100)+" euro\r");

                }
                cent5 -= Rcent5;

                // Abbellirei un pochino l'output.
                // Metterlo sotto forma di stringa? Forse più comodo da usare nell'interfaccia con un
                // s....out.println(resto). Si possono accodare caratteri alle stringhe senza troppi fastidi (?)

                System.out.println("5c:" + Rcent5 + "\n10c:" + Rcent10 + "\n20c:" + Rcent20 + "\n50c:" + Rcent50
                        + "\n1E:" + Reuro1 + "\n2E:" + Reuro2); */
            }

    }

    public boolean checkChange(){
        for(int i= 0; i <6; i++) {
            if (money[i] == 0) {
                return false;
            }
        }
        return true;
    }
}



