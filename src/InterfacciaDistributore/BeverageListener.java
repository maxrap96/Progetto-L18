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
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
          @Override
          public void run() {
              textArea.setText("     SCEGLIERE UNA BEVANDA");
          }
        };
        timer.schedule(timerTask, 10000);
        textArea.setText(distributore.getLabel(index).toUpperCase() + "\n" + "COSTO: "
                          + String.format("%.2f", distributore.getPrice("0" + index)) + "\nCredito: " + distributore.getCredit());
        distributore.selectBeverage("0" + index);
        resetDisplay.run();
    }
}
