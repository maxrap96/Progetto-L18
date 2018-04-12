package InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowFocusListener;

public class Distributore extends JFrame{

    public static final int LUNGHEZZA = 600;
    public static final int ALTEZZA = 500;

    // Dario: quando hai tempo facci un po' di commenti

    public Distributore() {
        setSize(LUNGHEZZA, ALTEZZA);
        setTitle("Hot Drinks Vending Machine");

        Container container = getContentPane();
        container.setBackground(Color.black);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        setLayout(new BorderLayout());

        JPanel pannelloBevande = new JPanel();
        pannelloBevande.setBackground(Color.BLACK);
        pannelloBevande.setLayout(new GridLayout(4, 2, 30, 20));
        add(pannelloBevande, BorderLayout.WEST);

        JPanel pannelloSelezione = new JPanel();
        pannelloSelezione.setPreferredSize(new Dimension(550, 500));
        pannelloSelezione.setBackground(Color.LIGHT_GRAY);
        pannelloSelezione.setLayout(new BorderLayout());
        add(pannelloSelezione, BorderLayout.EAST);

        JButton caffeEspresso = new JButton("Caffè Espresso");
        caffeEspresso.setBackground(Color.WHITE);
        caffeEspresso.setPreferredSize(new Dimension(375, 100));
        pannelloBevande.add(caffeEspresso);

        JButton cappuccino = new JButton("Cappuccino");
        cappuccino.setBackground(Color.WHITE);
        pannelloBevande.add(cappuccino);

        JButton te = new JButton("Tè");
        te.setBackground(Color.WHITE);
        pannelloBevande.add(te);

        JButton caffeCorretto = new JButton("Caffè Corretto");
        caffeCorretto.setBackground(Color.WHITE);
        pannelloBevande.add(caffeCorretto);

        JButton caffeLungo = new JButton("Caffè Lungo");
        caffeLungo.setBackground(Color.WHITE);
        pannelloBevande.add(caffeLungo);

        JButton vuoto1 = new JButton();
        vuoto1.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto1);

        JButton vuoto2 = new JButton();
        vuoto2.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto2);

        JButton vuoto3 = new JButton();
        vuoto3.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto3);

        JTextArea display = new JTextArea(19, 30);
        pannelloSelezione.add(display, BorderLayout.NORTH);

        JPanel pannelloMonete = new JPanel();
        pannelloMonete.setBackground(Color.BLUE);
        pannelloMonete.setPreferredSize(new Dimension(550, 400));
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }
}
