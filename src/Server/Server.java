package Server;

import Distributore.InterfacciaDistributore.WindowCloser;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Server extends JFrame {

    static File fileMenuServer =
            new File("src/FIle_Testo_Server/serverMenu.txt");
    static File fileStatsServer =
            new File("src/FIle_Testo_Server/serverStats.txt");

    /**
     * Creazione interfaccia grafica Server
     */

    public Server() {
        // Inizializzazione JFrame
        initJFrame("Remote Management System");

        Container container = this.getContentPane();
        container.setBackground(Color.BLACK);
        container.setLayout(new GridLayout(1,2));

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        JPanel textPanel = new JPanel(new FlowLayout());
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("", Font.ITALIC, 20));
        textPanel.add(textArea, FlowLayout.LEFT);

        JButton buttonMenu = makeButton("MENU");
        JButton buttonStats = makeButton("STATS");

        //buttonMenu.addActionListener(new ListenerLoad(fileMenuServer, textArea));
        ButtonPanel buttonPanel = new ButtonPanel(buttonMenu, buttonStats);

        container.add(buttonPanel);
        container.add(textPanel);
    }

    /**
     * Funzione che inizializza il JFrame secondo alcuni standard
     * @param title Nome della finestra
     */
    private void initJFrame(String title){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        pack();
        setSize(screenSize.width / 2,screenSize.height / 2); // Quando riduco la finestra prende queste
                                                                           // dimensioni
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Quando avvio si apre fullscreen
        setTitle(title);
    }

    private JButton makeButton(String nameButton){
        JButton buttonTmp = new JButton(nameButton);
        buttonTmp.setFont(new Font("", Font.ITALIC, 100));
        return buttonTmp;

    }
}

class ButtonPanel extends JPanel{

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
