package InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

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
        Timer t = new Timer();
      TimerTask tr = new TimerTask() {
          @Override
          public void run() {
              System.out.println("c");
          }
      };
        t.schedule(tr, 3000);
        textAreaL.setText(distributoreL.getLabel(indexL).toUpperCase() + "\n" + "COSTO: "
                          + String.format("%.2f", distributoreL.getPrice("0" + indexL)));
        distributoreL.selectBeverage("0" + indexL);
    }
}



