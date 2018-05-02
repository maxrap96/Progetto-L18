package InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe serve per verificare il corretto funzionamento della classe DisplayPanel
 */

public class TesterDisplayPanel extends JFrame {

    public TesterDisplayPanel() {
        // Inizializzazione JFrame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Tester DiplayPanel");

        Container container = getContentPane();
        container.setBackground(Color.WHITE);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        this.setLayout(new GridLayout(1, 2));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.RED);
        this.add(rightPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setLayout(new GridLayout(5,1));
        this.add(leftPanel);

        JPanel panel = new DisplayPanel();
        panel.setBackground(Color.BLACK);
        leftPanel.add(panel);
    }

    public static void main(String[] args) {
        TesterDisplayPanel tester = new TesterDisplayPanel();
        tester.setVisible(true);
    }
}
