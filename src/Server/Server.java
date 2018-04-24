package Server;

import InterfacciaDistributore.WindowCloser;

import javax.swing.*;
import java.awt.*;

public class Server extends JFrame implements FileServer{

    private int panelRows;
    private int panelCols;
    private JTextField[][] textFieldsVect;

    /**
     * Creazione interfaccia grafica Server.
     */
    public Server() {
        // Inizializzazione JFrame
        initJFrame("Remote Management System");

        Container container = this.getContentPane();
        container.setBackground(Color.BLACK);
        container.setLayout(new GridLayout(1,2));

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        JPanel textPanel = new JPanel(new GridLayout(this.panelRows, this.panelCols));
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        JButton buttonMenu = makeButton("MENU");
        JButton buttonStats = makeButton("STATS");

        ButtonPanel buttonPanel = new ButtonPanel(buttonMenu, buttonStats);

        container.add(buttonPanel);
        container.add(textPanel);
    }

    /**
     * Funzione che inizializza il JFrame secondo alcuni standard.
     *
     * @param title nome della finestra.
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

    private void initRowsAndCols(){

    }

    private void initJTextField(){

    }

    /**
     * Funzione che crea un bottone.
     *
     * @param nameButton nome da assegnare al bottone.
     * @return il bottone creato.
     */
    private JButton makeButton(String nameButton){
        JButton buttonTmp = new JButton(nameButton);
        buttonTmp.setFont(new Font("", Font.ITALIC, 100));
        return buttonTmp;

    }
}
