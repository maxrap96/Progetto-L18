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

        JPanel pannelloBevande = makePanel(805, 1000, Color.BLACK, null);
        add(pannelloBevande, BorderLayout.WEST);

        JPanel pannelloSelezione = makePanel(560, 1000, Color.LIGHT_GRAY, new BorderLayout());
        add(pannelloSelezione, BorderLayout.EAST);

        // Creo i tasti delle bevande e li aggiungo
        JButton caffeEspresso = makeRoundRectButton("Caffè Espresso");
        caffeEspresso.setBounds(61, 50, 300, 100);
        JButton cappuccino = makeRoundRectButton("Cappuccino");
        cappuccino.setBounds(443, 50, 300, 100);
        JButton te = makeRoundRectButton("Tè");
        te.setBounds(61, 220, 300, 100);
        JButton caffeCorretto = makeRoundRectButton("Caffè Corretto");
        caffeCorretto.setBounds(443, 220, 300, 100);
        JButton caffeLungo = makeRoundRectButton("Caffè Lungo");
        caffeLungo.setBounds(61, 390, 300, 100);
        JButton vuoto1 = makeRoundRectButton("");
        vuoto1.setBounds(443, 390, 300, 100);

        pannelloBevande.add(caffeEspresso);
        pannelloBevande.add(cappuccino);
        pannelloBevande.add(te);
        pannelloBevande.add(caffeCorretto);
        pannelloBevande.add(caffeLungo);
        pannelloBevande.add(vuoto1);

        JTextArea display = new JTextArea(5, 1);
        display.setBackground(Color.BLUE);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        display.setEditable(false);                 //Cosi non posso scriverci sopra da interfaccia
        pannelloSelezione.add(display, BorderLayout.NORTH);

        JPanel pannelloMonete = makePanel(560,545, Color.LIGHT_GRAY, null);
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        // Creo i vari tasti della sezione moenete
        JButton euro2 = makeRoundButton("2");
        euro2.setBounds(43, 290, 100, 100);
        JButton euro1 = makeRoundButton("1");
        euro1.setBounds(229, 290, 100, 100);
        JButton cent50 = makeRoundButton("0.50");
        cent50.setBounds(416, 290, 100, 100);
        JButton cent20 = makeRoundButton("0.20");
        cent20.setBounds(43, 426, 100, 100);
        JButton cent10 = makeRoundButton("0.10");
        cent10.setBounds(229, 426, 100, 100);
        JButton cent5 = makeRoundButton("0.05");
        cent5.setBounds(416, 426, 100, 100);
        JButton piuZucchero = makeRoundButton("+");
        piuZucchero.setBounds(229, 154, 100, 100);
        JButton menoZucchero = makeRoundButton("-");
        menoZucchero.setBounds(416, 154, 100, 100);
        JButton resto = makeRoundButton("Resto");
        resto.setBounds(43, 154, 100, 100);
        JButton chiavetta = makeRoundRectButton("Chiavetta") ;
        chiavetta.setBounds(155, 36, 250, 80);

        pannelloMonete.add(resto); pannelloMonete.add(menoZucchero); pannelloMonete.add(piuZucchero);
        pannelloMonete.add(euro2); pannelloMonete.add(euro1); pannelloMonete.add(cent50);
        pannelloMonete.add(cent20); pannelloMonete.add(cent10); pannelloMonete.add(cent5); pannelloMonete.add(chiavetta);

        // Listener bevande
        caffeEspresso.addActionListener(new ListenerTry(display));
        cappuccino.addActionListener(new ListenerTry(display));
        te.addActionListener(new ListenerTry(display));
        caffeCorretto.addActionListener(new ListenerTry(display));
        caffeLungo.addActionListener(new ListenerTry(display));
        vuoto1.addActionListener(new ListenerTry(display));

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
}
