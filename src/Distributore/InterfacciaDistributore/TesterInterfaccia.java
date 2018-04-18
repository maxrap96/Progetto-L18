package Distributore.InterfacciaDistributore;

import Distributore.Distributore;
import Distributore.Coins;

public class TesterInterfaccia {
    public static void main(String[] args) {
        Distributore distributore = new Distributore();
        Coins coins = new Coins();
        VendingMachine d = new VendingMachine(distributore, coins);
        d.setVisible(true);
    }
}