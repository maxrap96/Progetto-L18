package InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowFocusListener;

/* Quando eseguite il programma aprite la finestra a schermo intero perchè altrimenti mostra i pulsanti
uno sull'altro.
 */

public class Distributore extends JFrame{

    public static final int LUNGHEZZA = 600;
    public static final int ALTEZZA = 500;

    public Distributore() {
        setSize(LUNGHEZZA, ALTEZZA);
        setTitle("Distributore Automatico");

        Container pannello = getContentPane();
        pannello.setBackground(Color.black);

        ChiudiFinestra ascoltatore = new ChiudiFinestra();
        addWindowListener(ascoltatore);

        JPanel pannelloGrande = new JPanel();
        pannelloGrande.setLayout(new GridLayout(1, 3));

        JPanel p = new JPanel();
        p.setBackground(Color.BLACK);
        p.setLayout(new GridLayout(4, 1));
        JButton b1 = new JButton("Caffè");
        b1.setBackground(Color.WHITE);
        b1.setBorder(new RoundedBorder(50));
        p.add(b1);
        JButton b2 = new JButton("Cappuccino");
        b2.setBackground(Color.WHITE);
        b2.setBorder(new RoundedBorder(50));
        p.add(b2);
        JButton b3 = new JButton("Tè");
        b3.setBackground(Color.WHITE);
        b3.setBorder(new RoundedBorder(50));
        p.add(b3);
        JButton b4 = new JButton("Nettare degli dei");
        b4.setBackground(Color.WHITE);
        b4.setBorder(new RoundedBorder(50));
        p.add(b4);

        JPanel p3 = new JPanel();
        p3.setBackground(Color.BLACK);
        p3.setLayout(new GridLayout(2, 1));
        JPanel p1 = new JPanel();
        p1.setBackground(Color.BLACK);
        p1.setLayout(new GridLayout(3, 2));
        JButton cent5 = new JButton("5 cent.");
        cent5.setBackground(Color.WHITE);
        cent5.setBorder(new RoundedBorder(50));
        p1.add(cent5);
        JButton cent10 = new JButton("10 cent.");
        cent10.setBackground(Color.WHITE);
        cent10.setBorder(new RoundedBorder(50));
        p1.add(cent10);
        JButton cent20 = new JButton("20 cent.");
        cent20.setBackground(Color.WHITE);
        cent20.setBorder(new RoundedBorder(50));
        p1.add(cent20);
        JButton cent50 = new JButton("50 cent.");
        cent50.setBackground(Color.WHITE);
        cent50.setBorder(new RoundedBorder(50));
        p1.add(cent50);
        JButton euro1 = new JButton("1 euro");
        euro1.setBackground(Color.WHITE);
        euro1.setBorder(new RoundedBorder(50));
        p1.add(euro1);
        JButton euro2 = new JButton("2 euro");
        euro2.setBackground(Color.WHITE);
        euro2.setBorder(new RoundedBorder(50));

        p1.add(euro2);
        p3.add(p1);

        JPanel p4 = new JPanel();
        p4.setBackground(Color.BLACK);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.LIGHT_GRAY);
        p2.setLayout(new GridLayout(1, 2));
        p2.setPreferredSize(new Dimension(300, 40));
        JButton piuzucchero = new JButton("+");
        piuzucchero.setBackground(Color.WHITE);
        piuzucchero.setBorder(new RoundedBorder(50));
        p2.add(piuzucchero);
        JButton menozucchero = new JButton("-");
        menozucchero.setBackground(Color.WHITE);
        menozucchero.setBorder(new RoundedBorder(50));
        p2.add(menozucchero);
        p4.add(p2);

        pannelloGrande.add(p3);
        pannelloGrande.add(p4);
        pannelloGrande.add(p);
        add(pannelloGrande);
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }

}
