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
            jTextArea.setText("CAFFE' ESPRESSO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("Cappuccino")) {
            jTextArea.setText("CAPPUCCINO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("Tè")){
            jTextArea.setText("TE'" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("Caffè Corretto")){
            jTextArea.setText("CAFFE' CORRETTO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("Caffè Lungo")){
            jTextArea.setText("CAFFE' LUNGO" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("Ginseng")){
            jTextArea.setText("GINSENG" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("Bicchiere")){
            jTextArea.setText("BICCHIERE" + "\n" + "COSTO: 0.50" + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("2")){
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("1")){
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("0.50")){
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("0.20")){
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("0.10")){
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("0.05")){
            jTextArea.setText("INSERITI: " + "\n" + "COSTO: " + "\n\n\n" + "CREDITO:");
        }
        else if (stringaPulsante.equals("")){
            jTextArea.setText("BEVANDA NON DISPONIBILE" + "\n\n\n\n" + "CREDITO:");
        }
        jTextArea.repaint();
    }
}
