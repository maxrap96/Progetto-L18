package InterfacciaDistributore;

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
        jTextArea.setText("Caffé espresso costo 0.50");
        jTextArea.repaint();
    }
}
