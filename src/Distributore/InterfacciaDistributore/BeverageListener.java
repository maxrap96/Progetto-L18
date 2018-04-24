package Distributore.InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeverageListener implements ActionListener {

    private Distributore distributoreL;
    private JTextArea textAreaL;
    private int indexL;

    public BeverageListener(Distributore distributore, JTextArea display, int index) {
        this.distributoreL = distributore;
        this.textAreaL = display;
        this.indexL = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals(distributoreL.getLabel(indexL))) {
            textAreaL.setText(distributoreL.getLabel(indexL).toUpperCase() + "\n" + "COSTO: "
                    + String.format("%.2f", distributoreL.getPrice("0" + indexL)));
        }
    }
}



