package Distributore.InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CretiListener implements ActionListener{

private Distributore distributore;
private JTextArea textAreaL;
private double value;

public CretiListener(Distributore distributore, JTextArea display, double value) {
        this.distributore = distributore;
        this.textAreaL = display;
        this.value = value;
}


public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    distributore.addCredit(value);
    textAreaL.setText("Credito: " +  String.format("%.2f", distributore.getCredit()));
    }

}

