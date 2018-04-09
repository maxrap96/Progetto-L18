package InterfacciaDistributore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowFocusListener;

/* Quando eseguite il programma aprite la finestra schermo intero perchè altrimenti mostra i pulsanti
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

        setLayout(new BorderLayout());

        JPanel p = new JPanel();
        p.setBackground(Color.LIGHT_GRAY);
        p.setPreferredSize(new Dimension(450, 200));
        p.setLayout(new GridLayout(4, 1));

        JButton b1 = new JButton("Caffè");
        b1.setBackground(Color.WHITE);
        p.add(b1);
        JButton b2 = new JButton("Cappuccino");
        b2.setBackground(Color.WHITE);
        p.add(b2);
        JButton b3 = new JButton("Tè");
        b3.setBackground(Color.WHITE);
        p.add(b3);
        JButton b4 = new JButton("Nettare degli dei");
        b4.setBackground(Color.WHITE);
        p.add(b4);

        add(p, BorderLayout.EAST);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.LIGHT_GRAY);
        p1.setPreferredSize(new Dimension(450, 5));
        p1.setLayout(new GridLayout(3, 2));

        JButton cent5 = new JButton("5 cent.");
        cent5.setBackground(Color.WHITE);
        p1.add(cent5);
        JButton cent10 = new JButton("10 cent.");
        cent10.setBackground(Color.WHITE);
        p1.add(cent10);
        JButton cent20 = new JButton("20 cent.");
        cent20.setBackground(Color.WHITE);
        p1.add(cent20);
        JButton cent50 = new JButton("50 cent.");
        cent50.setBackground(Color.WHITE);
        p1.add(cent50);
        JButton euro1 = new JButton("1 euro");
        euro1.setBackground(Color.WHITE);
        p1.add(euro1);
        JButton euro2 = new JButton("2 euro");
        euro2.setBackground(Color.WHITE);
        p1.add(euro2);

        add(p1, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        Distributore d = new Distributore();
        d.setVisible(true);
    }

}
