package InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.*;

public class BeveragePanel extends JPanel {
    private final int NUMERO_PULSANTI_BEVANDE = 12;
    private Distributore distributorePanel;
    private int index = 1;

    public BeveragePanel(Distributore distributore) {
        this.distributorePanel = distributore;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        makeBeverageButton();
    }

    private void makeBeverageButton() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width,screenSize.height);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(50,40,50,40);
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;

        for (int i = 0; i < NUMERO_PULSANTI_BEVANDE; i++) {
            if (c.gridx == 3) {
                c.gridx = 0;
                c.gridy++;
            }

            JButton button;
            if (i < distributorePanel.getListSize()) {
                index = i + 1;
                button = new RoundRectButton(distributorePanel.getLabel(index));
                button.setMinimumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setMaximumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setFont(new Font("", Font.BOLD, 15));

               // ResetDisplay resetDisplay = new ResetDisplay(display, sugarDisplay, distributorePanel);
               // button.addActionListener(new BeverageListener(distributorePanel, display, index, resetDisplay));
            }
            else {
                // Pulsante vuoto
                button = new RoundRectButton("");
                button.setMinimumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setMaximumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                //ResetDisplay resetDisplay = new ResetDisplay(display, sugarDisplay, distributore);
                //button.addActionListener(new BeverageListener(distributore, display, index, resetDisplay));
            }

            this.add(button, c);
            c.gridx++;
        }
    }
}
