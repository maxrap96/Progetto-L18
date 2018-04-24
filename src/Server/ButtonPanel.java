package Server;

import javax.swing.*;
import java.awt.*;

/**
 * Pannello contenente i bottoni.
 *
 * Nota: viene utilizzato il GridBagLayout.
 */
public class ButtonPanel extends JPanel {

    public ButtonPanel(JButton button1, JButton button2) {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(60,60,60,60); // Spazio dai bordi
        c.gridx = 0;    // Prima colonna
        c.gridy = 0;    // Prima riga
        c.weightx = 1;  // Riempio la metà orizzontale dello spazio del bottone. Valori ammessi 0..1
        c.weighty = 1;  // Riempio la metà verticale dello spazio del bottone. Valori ammessi 0..1
        this.add(button1, c);

        c.gridy = 1; // Seconda riga
        this.add(button2, c);
    }
}
