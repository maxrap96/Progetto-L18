package InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.*;

public class BeveragePanel extends JPanel {
    private final int NUMERO_PULSANTI_BEVANDE = 12;
    private Distributore distributore;
    private int index = 1;
    private JTextField sugarDisplay;
    private JTextArea display;

    public BeveragePanel(Distributore distributore, JTextArea display, JTextField sugarDisplay) {
        this.distributore = distributore;
        this.display = display;
        this.sugarDisplay = sugarDisplay;
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
        c.insets = new Insets(screenSize.height / 20,screenSize.height / 13,
                screenSize.height / 20,screenSize.height / 30);
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
            if (i < distributore.getListSize()) {
                index = i + 1;
                button = new RoundRectButton(distributore.getLabel(index));
                button.setMinimumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setMaximumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setFont(new Font("", Font.ITALIC, 22));

                ResetDisplay resetDisplay = new ResetDisplay(display, sugarDisplay, distributore);
                button.addActionListener(new BeverageListener(distributore, display, index, resetDisplay));
            }
            else {
                // Pulsante vuoto
                button = new RoundRectButton("");
                button.setMinimumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setMaximumSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                button.setPreferredSize(new Dimension(screenSize.width / 6, screenSize.height / 8));
                }

            this.add(button, c);
            c.gridx++;
        }
    }
}
