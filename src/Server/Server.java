package Server;

import Distributore.InterfacciaDistributore.ImagePanel;
import Distributore.InterfacciaDistributore.WindowCloser;

import javax.swing.*;
import java.awt.*;

public class Server extends JFrame {

    /**
     * Creazione interfaccia grafica Server
     */

    public Server() {
        // Inizializzazione JFrame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        pack();
        setSize(screenSize.width,screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Remote Management System");

        Container container = getContentPane();
        container.setBackground(Color.BLACK);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);
    }
}
