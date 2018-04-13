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
                40,40)); //GridLayout mi dispone in forma di griglia
        container.add(pannelloBevande, BorderLayout.WEST);

        JPanel pannelloSelezione = makePanel(560, 1000, Color.LIGHT_GRAY, new BorderLayout());
        container.add(pannelloSelezione, BorderLayout.EAST);

        // Creo i tasti delle bevande e li aggiungo
        JButton caffeEspresso = makeRoundRectButton("Caffè Espresso");
        JButton cappuccino = makeRoundRectButton("Cappuccino");
        JButton te = makeRoundRectButton("Tè");
        JButton caffeCorretto = makeRoundRectButton("Caffè Corretto");
        JButton caffeLungo = makeRoundRectButton("Caffè Lungo");
        JButton vuoto1 = makeRoundRectButton("");

        pannelloBevande.add(caffeEspresso);
        pannelloBevande.add(cappuccino);
        pannelloBevande.add(te);
        pannelloBevande.add(caffeCorretto);
        pannelloBevande.add(caffeLungo);
        pannelloBevande.add(vuoto1);

        JTextArea display = new JTextArea(5, 1);
        pannelloSelezione.add(display, BorderLayout.NORTH);
        display.setFont(new Font("", Font.ITALIC,25));
        display.setEditable(false);                 //Cosi non posso scriverci sopra da interfaccia

        // Ho creato 2 pannelli, uno per i tasti e uno per la chiavetta, se no non riuscivo a fare la disposizione
        JPanel pannelloChiavetta = makePanel(560, 109, Color.LIGHT_GRAY, new BorderLayout());
        pannelloSelezione.add(pannelloChiavetta, BorderLayout.CENTER);

        JButton chiavetta = makeRoundRectButton("Chiavetta") ;
        pannelloChiavetta.add(chiavetta, BorderLayout.SOUTH);

        JPanel pannelloMonete = makePanel(560,545, Color.LIGHT_GRAY, new GridLayout(4,3,
                50, 10));
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        // Creo i vari tasti della sezione moenete
        JButton euro2 = makeRoundButton("2");
        JButton euro1 = makeRoundButton("1");
        JButton cent50 = makeRoundButton("0.50");
        JButton cent20 = makeRoundButton("0.20");
        JButton cent10 = makeRoundButton("0.10");
        JButton cent5 = makeRoundButton("0.05");
        JButton piuZucchero = makeRoundButton("+");
        JButton menoZucchero = makeRoundButton("-");
        JButton resto = makeRoundButton("Resto");


        // Questa disposizione non influisce sulla loro effettiva posizione nell'interfaccia
        pannelloMonete.add(resto); pannelloMonete.add(menoZucchero); pannelloMonete.add(piuZucchero);
        pannelloMonete.add(euro2); pannelloMonete.add(euro1); pannelloMonete.add(cent50);
        pannelloMonete.add(cent20); pannelloMonete.add(cent10); pannelloMonete.add(cent5);

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
     * @return
     */
    private JButton makeRoundButton(String string){
        JButton button = new RoundButton(string);
        button.setFont(new Font("", Font.ITALIC,25));
        button.setBackground(Color.WHITE);
        return button;
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }
}
