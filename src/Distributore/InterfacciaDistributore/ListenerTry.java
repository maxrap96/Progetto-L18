package Distributore.InterfacciaDistributore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerTry implements ActionListener {

    private JTextArea jTextArea;

    public ListenerTry(JTextArea textArea) {
        this.jTextArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String stringaPulsante = e.getActionCommand();
        if (stringaPulsante.equals("Caffè Espresso")) {
            jTextArea.setText("Caffé espresso" + "\n" + "Costo 0.50");
        }
        else if (stringaPulsante.equals("Cappuccino")) {
            jTextArea.setText("Cappuccino" + "\n" + "Costo 0.50");
        }
        else if (stringaPulsante.equals("Tè")){
            jTextArea.setText("Tè" + "\n" + "Costo 0.50");
        }
        else if (stringaPulsante.equals("Caffè Corretto")){
            jTextArea.setText("Caffè Corretto" + "\n" + "Costo 0.50");
        }
        else if (stringaPulsante.equals("Caffè Lungo")){
            jTextArea.setText("Caffè Lungo" + "\n" + "Costo 0.50");
        }
        else if (stringaPulsante.equals("")){
            jTextArea.setText("Bevanda non disponibile");
        }
        jTextArea.repaint();
    }
}
