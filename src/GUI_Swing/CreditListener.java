package GUI_Swing;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditListener implements ActionListener{

    private Distributore distributore;
    private JTextArea textAreaL;
    private double value;

    public CreditListener(Distributore distributore, JTextArea display, double value) {
        this.distributore = distributore;
        this.textAreaL = display;
        this.value = value;
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        distributore.addCredit(value);
        textAreaL.setText("\n\n\nCREDITO: " +  String.format("%.2f", distributore.getCredit()));
    }
}
