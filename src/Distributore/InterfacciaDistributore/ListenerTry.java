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

        for (int j = 0; j < bevande.length; j++) {
            if (stringaPulsante.equals(bevande[j])) {
                jTextArea.setText(bevande[j].toUpperCase() + "\n" + "COSTO: " + costo[j] + "\n\n\n" + "CREDITO: " + credit);
            }
        }
        if (stringaPulsante.equals("")) {
            jTextArea.setText("BEVANDA NON DISPONIBILE" + "\n\n\n\n" + "CREDITO:");
        }

        /*if (stringaPulsante.equals("Caffè Espresso")) {
            jTextArea.setText("CAFFE' ESPRESSO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO: " + credit);
        }
        else if (stringaPulsante.equals("Cappuccino")) {
            jTextArea.setText("CAPPUCCINO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO: " + credit);
        }
        else if (stringaPulsante.equals("Tè")){
            jTextArea.setText("TE'" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO: " + credit);
        }
        else if (stringaPulsante.equals("Caffè Corretto")){
            jTextArea.setText("CAFFE' CORRETTO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO: " + credit);
        }
        else if (stringaPulsante.equals("Caffè Lungo")){
            jTextArea.setText("CAFFE' LUNGO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO: " + credit);
        }
        else if (stringaPulsante.equals("Ginseng")){
            jTextArea.setText("GINSENG" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO: " + credit);
        }
        else if (stringaPulsante.equals("Bicchiere")){
            jTextArea.setText("BICCHIERE" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO: " + credit);
        }
        else if (stringaPulsante.equals("")){
            jTextArea.setText("BEVANDA NON DISPONIBILE" + "\n\n\n\n" + "CREDITO:");
        }

*/
       /* for (int i = 0; i < monete.length; i++) {
            if (stringaPulsante.equals(monete[i])) {
                credit += Double.parseDouble(monete[i]);

            }
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO: " + credit);

        }*/


        //jTextArea.repaint();
    }
}
