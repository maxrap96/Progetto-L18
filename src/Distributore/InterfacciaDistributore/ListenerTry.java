package Distributore.InterfacciaDistributore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerTry implements ActionListener {

    private JTextArea jTextArea;
    private double credit = 0.0;
    private final String monete[] = {"0.05", "0.10", "0.20", "0.50", "1", "2"};
    private final String bevande[] = {"Caffè Espresso", "Cappuccino", "Tè", "Caffè Corretto", "Caffè Lungo",
            "Ginseng", "Bicchiere"};
    private final double costo[] = {0.5, 0.6, 0.5, 0.7, 0.55, 0.65, 0.2};

    public ListenerTry(JTextArea textArea) {
        this.jTextArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String stringaPulsante = e.getActionCommand();

        // Stampa nome e prezzo della bevanda selezionata e il credito disponibile
        for (int j = 0; j < bevande.length; j++) {
            if (stringaPulsante.equals(bevande[j])) {
                jTextArea.setText(bevande[j].toUpperCase() + "\n" + "COSTO: " + costo[j] + "\n\n\n" + "CREDITO: " + credit);
            }
        }
        if (stringaPulsante.equals("")) {
            jTextArea.setText("BEVANDA NON DISPONIBILE" + "\n\n\n\n" + "CREDITO:");
        }

        // Stampa il credito disponibile dopo avers inserito una moneta
       /* for (int i = 0; i < monete.length; i++) {
            if (stringaPulsante.equals(monete[i])) {
                credit += Double.parseDouble(monete[i]);

            }
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO: " + credit);

        }*/


        //jTextArea.repaint();
    }
}
