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
        pannelloBevande.setPreferredSize(new Dimension(750, 500));
        pannelloBevande.setBackground(Color.BLACK);
        pannelloBevande.setLayout(null);
        add(pannelloBevande, BorderLayout.WEST);

        JPanel pannelloSelezione = new JPanel();
        pannelloSelezione.setPreferredSize(new Dimension(550, 500));
        pannelloSelezione.setBackground(Color.LIGHT_GRAY);
        pannelloSelezione.setLayout(new BorderLayout());
        add(pannelloSelezione, BorderLayout.EAST);

        JButton caffeEspresso = new RoundRectButton("Caffè Espresso");
        caffeEspresso.setBounds(20, 20, 300, 100);
        caffeEspresso.setBackground(Color.WHITE);
        pannelloBevande.add(caffeEspresso);

        JButton cappuccino = new RoundRectButton("Cappuccino");
        cappuccino.setBounds(400, 20, 300, 100);
        cappuccino.setBackground(Color.WHITE);
        pannelloBevande.add(cappuccino);

        JButton te = new RoundRectButton("Tè");
        te.setBounds(20, 250, 300, 100);
        te.setBackground(Color.WHITE);
        pannelloBevande.add(te);

        JButton caffeCorretto = new RoundRectButton("Caffè Corretto");
        caffeCorretto.setBounds(400, 250, 300, 100);
        caffeCorretto.setBackground(Color.WHITE);
        pannelloBevande.add(caffeCorretto);

        JButton caffeLungo = new RoundRectButton("Caffè Lungo");
        caffeLungo.setBounds(20, 400, 300, 100);
        caffeLungo.setBackground(Color.WHITE);
        pannelloBevande.add(caffeLungo);

        JButton vuoto1 = new RoundRectButton("Vuoto");
        vuoto1.setBounds(400, 400, 300, 100);
        vuoto1.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto1);

        JButton vuoto2 = new RoundRectButton("Vuoto");
        vuoto2.setBounds(20, 600, 300, 100);
        vuoto2.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto2);

        JButton vuoto3 = new RoundRectButton("Vuoto");
        vuoto3.setBounds(400, 600, 300, 100);
        vuoto3.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto3);

        JTextArea display = new JTextArea(10, 30);
        pannelloSelezione.add(display, BorderLayout.NORTH);
        display.setEditable(true);

        JPanel pannelloMonete = new JPanel();
        pannelloMonete.setBackground(Color.BLUE);
        pannelloMonete.setLayout(null);
        pannelloMonete.setPreferredSize(new Dimension(550, 545));
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        JButton euro2 = new RoundButton("2");
        euro2.setBackground(Color.green);
        euro2.setBounds(50, 50, 100 ,100);
        pannelloMonete.add(euro2);

        JButton euro1 = new RoundButton("1");
        euro1.setBounds(200, 50, 100, 100);
        euro1.setBackground(Color.green);
        pannelloMonete.add(euro1);

        JButton cent50 = new RoundButton("50");
        cent50.setBounds(350, 50, 100, 100);
        cent50.setBackground(Color.green);
        pannelloMonete.add(cent50);

        JButton cent20 = new RoundButton("20");
        cent20.setBounds(50, 200, 100, 100);
        cent20.setBackground(Color.green);
        pannelloMonete.add(cent20);

        JButton cent10 = new RoundButton("10");
        cent10.setBounds(200, 200, 100, 100);
        cent10.setBackground(Color.green);
        pannelloMonete.add(cent10);

        JButton cent5 = new RoundButton("5");
        cent5.setBounds(350, 200, 100, 100);
        cent5.setBackground(Color.green);
        pannelloMonete.add(cent5);

        JButton piuZucchero = new RoundButton("+");
        piuZucchero.setBounds(30, 300, 100, 100);
        piuZucchero.setBackground(Color.green);
        pannelloMonete.add(piuZucchero);

        JButton menoZucchero = new RoundButton("-");
        menoZucchero.setBounds(150, 300, 100, 100);
        menoZucchero.setBackground(Color.green);
        pannelloMonete.add(menoZucchero);

        JButton resto = new RoundButton("");
        resto.setBounds(350, 300, 100, 100);
        resto.setBackground(Color.green);
        pannelloMonete.add(resto);

        JButton chiavetta = new RoundRectButton("Chiavetta");
        chiavetta.setBounds(100, 400, 300, 100);
        chiavetta.setBackground(Color.WHITE);
        pannelloMonete.add(chiavetta);

        caffeEspresso.addActionListener(new ListenerTry(display));
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }
}
