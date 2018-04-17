package Distributore.InterfacciaDistributore;

import Distributore.Distributore;

import javax.swing.*;
import java.awt.*;

public class VendingMachine extends JFrame{

    private final int XBUTTONINDEX = 27;
    private final int YBUTTONINDEX = 50;
    private final int[] XINDEX = {27 , 332, 637};
    private final int[] YINDEX = {50, 220, 390, 560} ;

    private Distributore distributore;
    /**
     * Creazione interfaccia grafica distributore
     * @param distributore è il distributore a cui fa riferimento l'interfaccia
     */

    public VendingMachine(Distributore distributore) {
        this.distributore=distributore;

        //Inizializzazione JFrame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        pack();
        setSize(screenSize.width,screenSize.height);
        setTitle("Hot Drinks Vending Machine");

        Container container = getContentPane();
        container.setBackground(Color.YELLOW);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        container.setLayout(new BorderLayout());

        // Creazione del pannello contenente il display e i pulsanti monete, zucchero ecc...
        JPanel pannelloSelezione = makePanel(screenSize.width / 4, screenSize.width / 4, Color.LIGHT_GRAY, new BorderLayout());
        container.add(pannelloSelezione, BorderLayout.EAST);

        // Creazione del pannello dove vengono disposte le bevande
        JPanel pannelloBevande = makePanel( 3 * screenSize.width / 4, 3 * screenSize.height / 4, Color.BLACK, null);
        container.add(pannelloBevande, BorderLayout.WEST);
/*        // Creazione del display e aggiunta al pannelloSelezione
        JTextArea display = new JTextArea(5, 1);
        display.setBackground(Color.BLUE);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        display.setEditable(false);                 //Cosi non posso scriverci sopra da interfaccia
        pannelloSelezione.add(display, BorderLayout.NORTH);

        // Creazione del pannello delle monete e aggiunta al pannelloSelezione
        JPanel pannelloMonete = makePanel(560,545, Color.LIGHT_GRAY, null);
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        //creazione ed aggiunta dei pulsanti

        int x=0,y=0; //per i vettori delle coordinate
        for (int i=0;i<12;i++){ //perchè abbiamo 12 pulsanti
            if (x==3){
                x=0;
                y++;
            }
            JButton button;
            if (i<distributore.getListSize()) {
                button = makeRoundRectButton(distributore.getLabel(i + 1), XINDEX[x], YINDEX[y]);
                button.addActionListener(new ListenerTry(display,distributore.getID(i+1)));
            }
            else {
                button = makeRoundRectButton("", XINDEX[x], YINDEX[y]);
            }
            pannelloBevande.add(button);
            x++;
        }



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
//        JButton chiavetta = makeRoundRectButton("Chiavetta") ;
//        chiavetta.setBounds(155, 36, 250, 80);

        pannelloMonete.add(resto); pannelloMonete.add(menoZucchero); pannelloMonete.add(piuZucchero);
        pannelloMonete.add(euro2); pannelloMonete.add(euro1); pannelloMonete.add(cent50);
        pannelloMonete.add(cent20); pannelloMonete.add(cent10); pannelloMonete.add(cent5); //pannelloMonete.add(chiavetta);
/*
        // Listener bevande
        //todo crearlo direttamente nella creazione dinamica del bottone. così da renderlo più veloce
        button1.addActionListener(new ListenerTry(display));
        button2.addActionListener(new ListenerTry(display));
        button3.addActionListener(new ListenerTry(display));
        button4.addActionListener(new ListenerTry(display));
        button5.addActionListener(new ListenerTry(display));
        button6.addActionListener(new ListenerTry(display));
        button7.addActionListener(new ListenerTry(display));
        button8.addActionListener(new ListenerTry(display));

        // Listener monete
        euro2.addActionListener(new ListenerTry(display));
        euro1.addActionListener(new ListenerTry(display));
        cent50.addActionListener(new ListenerTry(display));
        cent20.addActionListener(new ListenerTry(display));
        cent10.addActionListener(new ListenerTry(display));
        cent5.addActionListener(new ListenerTry(display));

        // Listener zucchero e chiavetta
        piuZucchero.addActionListener(new ListenerTry(display));
        menoZucchero.addActionListener(new ListenerTry(display));
        //chiavetta.addActionListener(new ListenerTry(display));
*/
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
    private JButton makeRoundRectButton(String string, int x, int y){
        JButton button = new RoundRectButton(string);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("", Font.ITALIC,25));
        button.setBounds(x, y, 250, 100);
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
