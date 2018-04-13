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
        container.setBackground(Color.BLUE);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        setLayout(new BorderLayout());

<<<<<<< HEAD
        JPanel pannelloBevande = new JPanel();
        pannelloBevande.setPreferredSize(new Dimension(805, 1000));
        pannelloBevande.setBackground(Color.BLACK);
        pannelloBevande.setLayout(null);
        add(pannelloBevande, BorderLayout.WEST);

        JPanel pannelloSelezione = new JPanel();
        pannelloSelezione.setPreferredSize(new Dimension(560, 1000));
        pannelloSelezione.setBackground(Color.blue);
        pannelloSelezione.setLayout(new BorderLayout());
        add(pannelloSelezione, BorderLayout.EAST);
=======
        JPanel pannelloBevande = makePanel(805, 1000, Color.BLACK, null);
        container.add(pannelloBevande, BorderLayout.WEST);

        JPanel pannelloSelezione = makePanel(560, 1000, Color.LIGHT_GRAY, new BorderLayout());
        container.add(pannelloSelezione, BorderLayout.EAST);
>>>>>>> 18a0415fdac7feda2593b7613f2987658248bc06

        JButton caffeEspresso = new RoundRectButton("Caffè Espresso");
        caffeEspresso.setFont(new Font("", Font.ITALIC,25));
        caffeEspresso.setBounds(61, 50, 300, 100);
        caffeEspresso.setBackground(Color.WHITE);
        pannelloBevande.add(caffeEspresso);

        JButton cappuccino = new RoundRectButton("Cappuccino");
        cappuccino.setFont(new Font("", Font.ITALIC,25));
        cappuccino.setBounds(443, 50, 300, 100);
        cappuccino.setBackground(Color.WHITE);
        pannelloBevande.add(cappuccino);

        JButton te = new RoundRectButton("Tè");
        te.setFont(new Font("", Font.ITALIC,25));
        te.setBounds(61, 220, 300, 100);
        te.setBackground(Color.WHITE);
        pannelloBevande.add(te);

        JButton caffeCorretto = new RoundRectButton("Caffè Corretto");
        caffeCorretto.setFont(new Font("", Font.ITALIC,25));
        caffeCorretto.setBounds(443, 220, 300, 100);
        caffeCorretto.setBackground(Color.WHITE);
        pannelloBevande.add(caffeCorretto);

        JButton caffeLungo = new RoundRectButton("Caffè Lungo");
        caffeLungo.setFont(new Font("", Font.ITALIC,25));
        caffeLungo.setBounds(61, 390, 300, 100);
        caffeLungo.setBackground(Color.WHITE);
        pannelloBevande.add(caffeLungo);

        JButton vuoto1 = new RoundRectButton("");
        vuoto1.setFont(new Font("", Font.ITALIC,25));
        vuoto1.setBounds(443, 390, 300, 100);
        vuoto1.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto1);

        JButton vuoto2 = new RoundRectButton("");
        vuoto2.setFont(new Font("", Font.ITALIC,25));
        vuoto2.setBounds(61, 560, 300, 100);
        vuoto2.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto2);

        JButton vuoto3 = new RoundRectButton("");
        vuoto3.setFont(new Font("", Font.ITALIC,25));
        vuoto3.setBounds(443, 560, 300, 100);
        vuoto3.setBackground(Color.WHITE);
        pannelloBevande.add(vuoto3);

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

    private JButton makeRoundRectButton(){
        return null;
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }
}
