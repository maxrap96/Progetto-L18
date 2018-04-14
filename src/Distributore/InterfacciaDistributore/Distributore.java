package Distributore.InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;

public class Distributore extends JFrame{

    private static final int LUNGHEZZA = 600;
    private static final int ALTEZZA = 500;

    /** Creazione interfaccia grafica distributore*/

    public Distributore() {
        //Inizializzazione JFrame
        setSize(LUNGHEZZA, ALTEZZA);
        setTitle("Hot Drinks Vending Machine");

        Container container = getContentPane();
        container.setBackground(Color.BLUE);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        setLayout(new BorderLayout());

        // Creazione pannello dove vengono disposte le bevande
        JPanel pannelloBevande = makePanel(805, 1000, Color.BLACK, null);
        add(pannelloBevande, BorderLayout.WEST);

        // Creazione pannello contenente il display e i pulsanti monete, zucchero ecc...
        JPanel pannelloSelezione = makePanel(560, 1000, Color.LIGHT_GRAY, new BorderLayout());
        add(pannelloSelezione, BorderLayout.EAST);

        // Creazione dei tasti delle bevande e successiva aggiunta al pannelloBevande
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
        JButton ginseng = makeRoundRectButton("Ginseng");
        ginseng.setBounds(443, 390, 300, 100);
        JButton bicchiere = makeRoundRectButton("Bicchiere");
        bicchiere.setBounds(61, 560, 300, 100);
        JButton vuoto = makeRoundRectButton("");
        vuoto.setBounds(443, 560, 300, 100);

        pannelloBevande.add(caffeEspresso);
        pannelloBevande.add(cappuccino);
        pannelloBevande.add(te);
        pannelloBevande.add(caffeCorretto);
        pannelloBevande.add(caffeLungo);
        pannelloBevande.add(ginseng);
        pannelloBevande.add(bicchiere);
        pannelloBevande.add(vuoto);

        // Creazione del display e aggiunta al pannelloSelezione
        JTextArea display = new JTextArea(5, 1);
        display.setBackground(Color.BLUE);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        display.setEditable(false);                 //Cosi non posso scriverci sopra da interfaccia
        pannelloSelezione.add(display, BorderLayout.NORTH);

        // Creazione pannelle delle monete e aggiunta al pannelloSelezione
        JPanel pannelloMonete = makePanel(560,545, Color.LIGHT_GRAY, null);
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        // Creazione dei vari tasti della sezione moenete e aggiunta al pannelloMonete
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
        ginseng.addActionListener(new ListenerTry(display));
        bicchiere.addActionListener(new ListenerTry(display));
        vuoto.addActionListener(new ListenerTry(display));

        //Listener monete
        euro2.addActionListener(new ListenerTry(display));
        euro1.addActionListener(new ListenerTry(display));
        cent50.addActionListener(new ListenerTry(display));
        cent20.addActionListener(new ListenerTry(display));
        cent10.addActionListener(new ListenerTry(display));
        cent5.addActionListener(new ListenerTry(display));

        //Listener zucchero e chiavetta
        piuZucchero.addActionListener(new ListenerTry(display));
        menoZucchero.addActionListener(new ListenerTry(display));
        chiavetta.addActionListener(new ListenerTry(display));

    }

    /**
     * Funzione per creare pannelli standard
     * @param width: lunghezza  del pannello
     * @param height: altezza  del pannello
     * @param color: colore dello sfondo
     * @param layoutManager: tipo di layout del pannello
     * @return ritorna il pannello
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
     * @param string: etichetta del pulsante
     * @return ritorna il pulsante
     */
    private JButton makeRoundRectButton(String string){
        JButton button = new RoundRectButton(string);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("", Font.ITALIC,25));
        return button;
    }

    /**
     * Funzione per creare bottoni circolari
     * @param string: etichetta del pulsante
     * @return ritorna il pulsante
     */
    private JButton makeRoundButton(String string){
        JButton button = new RoundButton(string);
        button.setFont(new Font("", Font.ITALIC,25));
        button.setBackground(Color.WHITE);
        return button;
    }
}
