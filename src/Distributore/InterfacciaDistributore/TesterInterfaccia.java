package Distributore.InterfacciaDistributore;

import Distributore.Distributore;

public class TesterInterfaccia {
    public static void main(String[] args) {
        Distributore distributore = new Distributore();
        VendingMachine d = new VendingMachine(distributore);
        d.setVisible(true);
    }
}