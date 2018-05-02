package InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che crea un pannello per il display del distributore
 * con due JTextField (uno per il credito e uno per lo zucchero)
 * e una JTextArea per le informazioni della bevanda e di sistema
 */

public class DisplayPanel extends JPanel {
    private JTextArea jTextArea;
    private JTextField jTextFieldCredit;
    private JTextField jTextFieldSugar;

    public DisplayPanel() {
        this.jTextArea = new JTextArea(4, 1);
        this.jTextFieldCredit = new JTextField();
        this.jTextFieldSugar = new JTextField();

        this.setLayout(new BorderLayout());

        this.add(jTextArea, BorderLayout.NORTH);
        jTextArea.setBackground(Color.BLUE);
        jTextArea.setForeground(Color.WHITE);
        jTextArea.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        jTextArea.setEditable(false);
        jTextArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        this.add(jTextFieldCredit, BorderLayout.CENTER);
        jTextFieldCredit.setBackground(Color.BLUE);
        jTextFieldCredit.setForeground(Color.WHITE);
        jTextFieldCredit.setHorizontalAlignment(SwingConstants.CENTER);
        jTextFieldCredit.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        jTextFieldCredit.setEditable(false);
        jTextFieldCredit.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        this.add(jTextFieldSugar, BorderLayout.SOUTH);
        jTextFieldSugar.setBackground(Color.BLUE);
        jTextFieldSugar.setForeground(Color.WHITE);
        jTextFieldSugar.setHorizontalAlignment(SwingConstants.CENTER);
        jTextFieldSugar.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        jTextFieldSugar.setEditable(false);
        jTextFieldSugar.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }
}
