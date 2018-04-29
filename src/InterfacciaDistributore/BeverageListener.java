package InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class BeverageListener implements ActionListener {

    private Distributore distributore;
    private JTextArea textArea;
    private int index;
    private ResetDisplay resetDisplay;

    public BeverageListener(Distributore distributore, JTextArea display, int index, ResetDisplay resetDisplay) {
        this.distributore = distributore;
        this.textArea = display;
        this.index = index;
        this.resetDisplay = resetDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (distributore.getCredit() >= distributore.getPrice("0" + index)) {
            //String message = distributore.selectBeverage("0" + index);
            textArea.setText(distributore.selectBeverage("0" + index));
            resetDisplay.run();
        }
        else if (distributore.getCredit() > 0 && distributore.getCredit() < distributore.getPrice("0" + index)){
            textArea.setText(distributore.selectBeverage("0" + index) + "\n" + "COSTO: " +
                             String.format("%.2f", distributore.getPrice("0" + index)) + "\n\nCREDITO: " +
                             String.format("%.2f", distributore.getCredit()));
            resetDisplay.run();
        }
        else {
            textArea.setText(distributore.getLabel(index).toUpperCase() + "\n" + "COSTO: " +
                             String.format("%.2f", distributore.getPrice("0" + index)) + "\n\nCREDITO: " +
                             String.format("%.2f", distributore.getCredit()));
            resetDisplay.run();
        }
    }
}
