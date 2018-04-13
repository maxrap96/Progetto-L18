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

        JButton euro2 = makeRoundButton("2", 43, 290);
        pannelloMonete.add(euro2);

        JButton euro1 = makeRoundButton("1", 229, 290);
        pannelloMonete.add(euro1);

        JButton cent50 = makeRoundButton("0.50", 416, 290);
        pannelloMonete.add(cent50);

        JButton cent20 = makeRoundButton("0.20", 43, 426);
        pannelloMonete.add(cent20);

        JButton cent10 = makeRoundButton("0.10", 229, 426);
        pannelloMonete.add(cent10);

        JButton cent5 = makeRoundButton("0.05", 416, 426);
        pannelloMonete.add(cent5);

        JButton piuZucchero = makeRoundButton("+", 229, 154);
        pannelloMonete.add(piuZucchero);

        JButton menoZucchero = makeRoundButton("-", 416, 154);
        pannelloMonete.add(menoZucchero);

        JButton resto = makeRoundButton("Resto", 43, 154);
        pannelloMonete.add(resto);

        JButton chiavetta = new RoundRectButton("Chiavetta");
        chiavetta.setFont(new Font("", Font.ITALIC,25));
        chiavetta.setBounds(155, 36, 250, 80);
        chiavetta.setBackground(Color.WHITE);
        pannelloMonete.add(chiavetta);

        caffeEspresso.addActionListener(new ListenerTry(display));
    }

    /**
     * Funzione per creare pannelli standard
     * @param width
     * @param height
     * @param color
     * @param layoutManager
     * @return
     */
    private JPanel makePanel(int width, int height, Color color, LayoutManager layoutManager){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(color);
        panel.setLayout(layoutManager);
        return panel;
    }

    /**
     * Funzione per creare bottoni a rettangolo con angoli smussati
     * @param string
     * @return
     */

    private JButton makeRoundRectButton(String string){
        JButton button = new RoundRectButton(string);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("", Font.ITALIC,25));
        return button;
    }

    /**
     * Funzione per creare bottoni circolari
     * @param string
     * @param x
     * @param y
     * @return
     */
    private JButton makeRoundButton(String string, int x, int y){
        JButton button = new RoundButton(string);
        button.setFont(new Font("", Font.ITALIC,25));
        button.setBounds(x, y, 100, 100);
        button.setBackground(Color.WHITE);
        return button;
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }
}
