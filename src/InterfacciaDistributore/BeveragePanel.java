package InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.*;

public class BeveragePanel extends JPanel {
    private Distributore distributorePanel;
    private int index = 1;

    public BeveragePanel(Distributore distributore) {
        this.distributorePanel = distributore;
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.RED);
        makeBeverageButton();


    }

    private void makeBeverageButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(50,40,50,40); // Spazio dai bordi
        c.weightx = 1;  // Riempio la metà orizzontale dello spazio del bottone. Valori ammessi 0..1
        c.weighty = 1;  // Riempio la metà verticale dello spazio del bottone. Valori ammessi 0..1
        c.gridx = 0;
        c.gridy = 0;

        for (int i = 0; i < 12; i++) {
            if (c.gridx == 3) {
                c.gridx = 0;
                c.gridy++;
            }

            JButton button;
            if (i < distributorePanel.getListSize()) {
                index = i + 1;
                button = new RoundRectButton(distributorePanel.getLabel(index));
             // button.setPreferredSize(new Dimension(800, 100));
                button.setFont(new Font("", Font.BOLD, 15));

               // ResetDisplay resetDisplay = new ResetDisplay(display, sugarDisplay, distributorePanel);
               // button.addActionListener(new BeverageListener(distributorePanel, display, index, resetDisplay));
            }
            else {
                // Pulsante vuoto
                button = new RoundRectButton("");
                //ResetDisplay resetDisplay = new ResetDisplay(display, sugarDisplay, distributore);
                //button.addActionListener(new BeverageListener(distributore, display, index, resetDisplay));
            }

            this.add(button, c);
            c.gridx++;
        }
    }
}
