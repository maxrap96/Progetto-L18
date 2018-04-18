package Distributore.InterfacciaDistributore;

import Distributore.Distributore;
import Distributore.Coins;

import javax.swing.*;
import java.awt.*;

public class VendingMachine extends JFrame{

    private final int NUMERO_PULSANTI_BEVANDE = 12;
    private final int NUMERO_MONETE = 6;
    private final int NUMERO_ALTRI_PULSANTI = 3;
    private final String[] ALTRI_PULSANTI = {"", "+", "-"};
    private Distributore distributore;

    /**
     * Creazione interfaccia grafica distributore
     * @param distributore è il distributore a cui fa riferimento l'interfaccia
     */

    public VendingMachine(Distributore distributore) {
        this.distributore = distributore;

        //Inizializzazione JFrame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        pack();
        setSize(screenSize.width,screenSize.height);
        setTitle("Hot Drinks Vending Machine");

        // Indici coordinate pulsanti bevande
        final int[] X_SCREEN_INDEX = {5 * screenSize.width / 100, 27 * screenSize.width / 100,
                                      49 * screenSize.width / 100};
        final int[] Y_SCREEN_INDEX = {7 * screenSize.height / 100, 29 * screenSize.height / 100,
                                      51 * screenSize.height / 100, 73 * screenSize.height / 100} ;

        // Indici coordinate pulsanti monete, zucchero e resto
        final int[] X_MON_INDEX = {2 * screenSize.width / 100, 11 * screenSize.width / 100,
                                   20 * screenSize.width / 100};
        final int[] Y_MON_INDEX = {20 * screenSize.height / 100, 37 * screenSize.height / 100,
                                   54 * screenSize.height / 100};

        Container container = getContentPane();
        container.setBackground(Color.BLACK);

        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        container.setLayout(new BorderLayout());

        // Creazione del pannello contenente il display e i pulsanti monete, zucchero ecc...
        JPanel pannelloSelezione = makePanel(2 * screenSize.width / 7, 2 * screenSize.width / 7,
                                              Color.LIGHT_GRAY, new BorderLayout());
        container.add(pannelloSelezione, BorderLayout.EAST);

        // Creazione del pannello dove vengono disposte le bevande
        JPanel pannelloBevande = makePanel(5 * screenSize.width / 7, 5 * screenSize.height / 7,
                                            Color.BLACK, null);
        container.add(pannelloBevande, BorderLayout.WEST);

        // Creazione del pannello delle monete e aggiunta al pannelloSelezione
        JPanel pannelloMonete = makePanel(5 * screenSize.width / 7,5 * screenSize.height / 7,
                                           Color.LIGHT_GRAY, null);
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        // Creazione del display e aggiunta al pannelloSelezione
        JTextArea display = new JTextArea(5, 1);
        display.setBackground(Color.BLUE);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        display.setEditable(false);                 //Cosi non posso scriverci sopra da interfaccia
        pannelloSelezione.add(display, BorderLayout.NORTH);

        // Creazione e aggiunta dei dodici pulsanti delle bevande
        int xButton = 0, yButton = 0; // coordinate dei pulsanti

        for (int i = 0; i < NUMERO_PULSANTI_BEVANDE; i++ ){
            if (xButton == 3){
                xButton = 0;
                yButton++;
            }
            JButton button;
            if (i < distributore.getListSize()) {
                button = makeRoundRectButton(distributore.getLabel(i + 1), X_SCREEN_INDEX[xButton],
                                             Y_SCREEN_INDEX[yButton],screenSize.width / 6,
                                            screenSize.height / 8);
                //button.addActionListener(new ListenerTry( display, distributore.getID(i + 1)));
            }
            else {
                button = makeRoundRectButton("", X_SCREEN_INDEX[xButton], Y_SCREEN_INDEX[yButton],
                                             screenSize.width / 6, screenSize.height / 8);
            }

            pannelloBevande.add(button);
            xButton++;
        }

        // Creazione dei vari tasti della sezione moenete e aggiunta al pannelloMonete
        int xButtonMon = 0, yButtonMon = 0; // coordinate dei pulsanti
        String[] coinsValue = distributore.getCoinsValue();

        for (int i = -NUMERO_ALTRI_PULSANTI; i < NUMERO_MONETE; i++) {
            if (xButtonMon == 3) {
                xButtonMon = 0;
                yButtonMon++;
            }
            JButton button;
            if (i >= 0) {
                button = makeRoundButton(coinsValue[i],  X_MON_INDEX[xButtonMon], Y_MON_INDEX[yButtonMon],
                                        screenSize.height / 8,screenSize.height / 8);
              //button.addActionListener(new ListenerTry(display, distributore.getID(i + 1)));
            }
            else {
                button = makeRoundButton(ALTRI_PULSANTI[i + 3], X_MON_INDEX[xButtonMon], Y_MON_INDEX[yButtonMon],
                                        screenSize.height / 8,screenSize.height / 8);
            }

            pannelloMonete.add(button);
            xButtonMon++;
        }

        JButton chiavetta = makeRoundRectButton("Chiavetta", 9 * screenSize.width / 200,
                                                3 * screenSize.width / 100, 20 * screenSize.width / 100,
                                                9 * screenSize.height / 100) ;
        pannelloMonete.add(chiavetta);



        // Listener bevande
   /*   //todo crearlo direttamente nella creazione dinamica del bottone. così da renderlo più veloce
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
    private JButton makeRoundRectButton(String string, int x, int y, int screenW, int screenH){
        JButton button = new RoundRectButton(string);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("", Font.ITALIC,25));
        button.setBounds(x, y, screenW, screenH);
        return button;
    }

    /**
     * Funzione per creare bottoni circolari
     *
     * @return ritorna il pulsante
     */
    private JButton makeRoundButton(String string, int x, int y, int screenW, int screenH){
        JButton button = new RoundButton(string);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("", Font.ITALIC,25));
        button.setBounds(x, y, screenW, screenH);
        return button;
    }
}
