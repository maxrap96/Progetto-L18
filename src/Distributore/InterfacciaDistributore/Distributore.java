package Distributore.InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;

public class Distributore extends JFrame{

    public static final int LUNGHEZZA = 600;
    public static final int ALTEZZA = 500;

    // Dario: quando hai tempo facci un po' di commenti

    public Distributore() {
        setSize(LUNGHEZZA, ALTEZZA);
        setTitle("Hot Drinks Vending Machine");

        Container container = getContentPane();
        container.setBackground(Color.BLUE);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        setLayout(new BorderLayout());

        JPanel pannelloBevande = makePanel(805, 1000, Color.BLACK, new GridLayout(4,2,
                40,40));
        container.add(pannelloBevande, BorderLayout.WEST);

        JPanel pannelloSelezione = makePanel(560, 1000, Color.LIGHT_GRAY, new BorderLayout());
        container.add(pannelloSelezione, BorderLayout.EAST);


        JButton caffeEspresso = makeRoundRectButton("Caffè Espresso");
        pannelloBevande.add(caffeEspresso);

        JButton cappuccino = makeRoundRectButton("Cappuccino");
        pannelloBevande.add(cappuccino);

        JButton te = makeRoundRectButton("Tè");
        pannelloBevande.add(te);

        JButton caffeCorretto = makeRoundRectButton("Caffè Corretto");
        pannelloBevande.add(caffeCorretto);

        JButton caffeLungo = makeRoundRectButton("Caffè Lungo");
        pannelloBevande.add(caffeLungo);

        JButton vuoto1 = makeRoundRectButton("");
        pannelloBevande.add(vuoto1);

        JTextArea display = new JTextArea(5, 1);
        pannelloSelezione.add(display, BorderLayout.NORTH);
        display.setFont(new Font("", Font.ITALIC,25));
        display.setEditable(false);                 //Cosi non posso scriverci sopra da interfaccia

        JPanel pannelloMonete = new JPanel();
        pannelloMonete.setBackground(Color.LIGHT_GRAY);
        pannelloMonete.setLayout(null);
        pannelloMonete.setPreferredSize(new Dimension(560, 545));
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        JButton euro2 = new RoundButton("2");
        euro2.setFont(new Font("", Font.ITALIC,25));
        euro2.setBackground(Color.WHITE);
        euro2.setBounds(43, 290, 100 ,100);
        pannelloMonete.add(euro2);

        JButton euro1 = new RoundButton("1");
        euro1.setFont(new Font("", Font.ITALIC,25));
        euro1.setBounds(229, 290, 100, 100);
        euro1.setBackground(Color.WHITE);
        pannelloMonete.add(euro1);

        JButton cent50 = new RoundButton("50");
        cent50.setFont(new Font("", Font.ITALIC,25));
        cent50.setBounds(416, 290, 100, 100);
        cent50.setBackground(Color.WHITE);
        pannelloMonete.add(cent50);

        JButton cent20 = new RoundButton("20");
        cent20.setFont(new Font("", Font.ITALIC,25));
        cent20.setBounds(43, 426, 100, 100);
        cent20.setBackground(Color.WHITE);
        pannelloMonete.add(cent20);

        JButton cent10 = new RoundButton("10");
        cent10.setFont(new Font("", Font.ITALIC,25));
        cent10.setBounds(229, 426, 100, 100);
        cent10.setBackground(Color.WHITE);
        pannelloMonete.add(cent10);

        JButton cent5 = new RoundButton("5");
        cent5.setFont(new Font("", Font.ITALIC,25));
        cent5.setBounds(416, 426, 100, 100);
        cent5.setBackground(Color.WHITE);
        pannelloMonete.add(cent5);

        JButton piuZucchero = new RoundButton("+");
        piuZucchero.setFont(new Font("", Font.ITALIC,25));
        piuZucchero.setBounds(229, 154, 100, 100);
        piuZucchero.setBackground(Color.WHITE);
        pannelloMonete.add(piuZucchero);

        JButton menoZucchero = new RoundButton("-");
        menoZucchero.setFont(new Font("", Font.ITALIC,25));
        menoZucchero.setBounds(416, 154, 100, 100);
        menoZucchero.setBackground(Color.WHITE);
        pannelloMonete.add(menoZucchero);

        JButton resto = new RoundButton("");
        resto.setBounds(43, 154, 100, 100);
        resto.setBackground(Color.WHITE);
        pannelloMonete.add(resto);

        JButton chiavetta = new RoundRectButton("Chiavetta");
        chiavetta.setFont(new Font("", Font.ITALIC,25));
        chiavetta.setBounds(155, 36, 250, 80);
        chiavetta.setBackground(Color.WHITE);
        pannelloMonete.add(chiavetta);

        caffeEspresso.addActionListener(new ListenerTry(display));
    }

    private JPanel makePanel(int width, int height, Color color, LayoutManager layoutManager){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(color);
        panel.setLayout(layoutManager);
        return panel;
    }

    private JButton makeRoundRectButton(String string){
        JButton button = new RoundRectButton(string);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("", Font.ITALIC,25));
        return button;
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }
}
