package InterfacciaDistributore;

import Distributore.Distributore;
import javax.swing.*;
import java.awt.*;

public class VendingMachine extends JFrame{

    private final int NUMERO_PULSANTI_BEVANDE = 12;
    private final int NUMERO_MONETE = 6;
    private Distributore distributore = new Distributore();
    private int index = 1;
    private int sugar = 3;
    private final String DEFAULTMESSAGE = "     SCEGLIERE UNA BEVANDA";

    private ResetDisplay resetDisplay;

    /**
     * Creazione interfaccia grafica distributore
     */

    public VendingMachine() {
        // Inizializzazione JFrame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width,screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Hot Drinks Vending Machine");

        // Indici coordinate pulsanti bevande
        final int[] X_SCREEN_INDEX = {5 * screenSize.width / 100, 27 * screenSize.width / 100,
                                      49 * screenSize.width / 100};
        final int[] Y_SCREEN_INDEX = {7 * screenSize.height / 100, 29 * screenSize.height / 100,
                                      51 * screenSize.height / 100, 73 * screenSize.height / 100} ;

        // Indici coordinate pulsanti monete, zucchero e resto
        final int[] X_MON_INDEX = {2 * screenSize.width / 100, 11 * screenSize.width / 100,
                                   20 * screenSize.width / 100};
        final int[] Y_MON_INDEX = {37 * screenSize.height / 100, 54 * screenSize.height / 100};

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
        JPanel pannelloMonete = makePanel(5 * screenSize.width / 7,69 * screenSize.height / 100,
                                           Color.LIGHT_GRAY, null);
        pannelloSelezione.add(pannelloMonete, BorderLayout.SOUTH);

        // Creazione display zucchero aggiunta al pannelloSelezione
        JTextField sugarDisplay = new JTextField();
        sugarDisplay.setBackground(Color.BLUE);
        sugarDisplay.setForeground(Color.WHITE);
        sugarDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        sugarDisplay.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        sugarDisplay.setEditable(false);
        sugarDisplay.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        pannelloSelezione.add(sugarDisplay, BorderLayout.CENTER);

        // Creazione del display e aggiunta al pannelloSelezione
        JTextArea display = new JTextArea(4, 1);
        display.setBackground(Color.BLUE);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("", Font.BOLD & Font.ITALIC,25));
        display.setEditable(false);
        display.setText(DEFAULTMESSAGE);
        pannelloSelezione.add(display, BorderLayout.NORTH);

        resetDisplay = new ResetDisplay(display, sugarDisplay, distributore);
        resetDisplay.setDots();

        // Creazione e aggiunta dei dodici pulsanti delle bevande
        createDrinkButtons(X_SCREEN_INDEX, Y_SCREEN_INDEX, screenSize, display, sugarDisplay, pannelloBevande);

        // Creazione dei vari tasti della sezione moenete e aggiunta al pannelloMonete
        createMoneyButtons(X_MON_INDEX, Y_MON_INDEX, screenSize, display, pannelloMonete);

        // Creazione dei pulsanti più zucchero, meno zucchero e resto
        JButton giveChange = makeRoundButton("C",2 * screenSize.width / 100,20 * screenSize.height / 100,
                                             screenSize.height / 8,screenSize.height / 8);
        pannelloMonete.add(giveChange);
        giveChange.addActionListener(change -> {
            distributore.giveChange();
            display.setText("\n\n\nCREDITO: " +  String.format("%.2f", distributore.getCredit()));
            resetDisplay.run();
        });

        JButton minus = makeRoundButton("-",11 * screenSize.width / 100,20 * screenSize.height / 100,
                                        screenSize.height / 8,screenSize.height / 8);
        pannelloMonete.add(minus);

        minus.addActionListener(subtract -> {
            distributore.lessSugar();
            ResetDisplay resetDisplay = new ResetDisplay(display, sugarDisplay, distributore);
            resetDisplay.setDots();
            resetDisplay.run();
        });


        JButton plus = makeRoundButton("+",20 * screenSize.width / 100,20 * screenSize.height / 100,
                                       screenSize.height / 8,screenSize.height / 8);
        pannelloMonete.add(plus);
        plus.addActionListener(add -> {
            distributore.moreSugar();
            resetDisplay.setDots();
            resetDisplay.run();

        });

        JButton chiavetta = makeRoundRectButton("Chiavetta", 9 * screenSize.width / 200,
                                                3 * screenSize.width / 100, 20 * screenSize.width / 100,
                                                9 * screenSize.height / 100) ;
        pannelloMonete.add(chiavetta);
    }

    /**
     * funzione che crea i bottoni delle monete
     * @param X_MON_INDEX è l'indice x del tabellone
     * @param Y_MON_INDEX è l'indice y del tabellone
     * @param screenSize è la dimensione dello schermo
     * @param display è il display delle monete
     * @param pannelloMonete è il pannello delle monete
     */

    private void createMoneyButtons(int[] X_MON_INDEX, int[] Y_MON_INDEX, Dimension screenSize, JTextArea display,
                                    JPanel pannelloMonete) {
        int xButtonMon = 0, yButtonMon = 0; // coordinate dei pulsanti

        for (int i = 0; i < NUMERO_MONETE; i++) {
            if (xButtonMon == 3) {
                xButtonMon = 0;
                yButtonMon++;
            }

            String cValue = String.format("%.2f", distributore.getCoinsValue()[i]);
            JButton  button = makeRoundButton(cValue, X_MON_INDEX[xButtonMon], Y_MON_INDEX[yButtonMon],
                    screenSize.height / 8,screenSize.height / 8);

            // Aggiunta action listener associato ad ogni pulsante con relativo valore
            index = i;
            button.addActionListener(new CreditListener(distributore, display, distributore.getCoinsValue()[i]));
            pannelloMonete.add(button);
            xButtonMon++;
        }

    }

    /**
     * serve a creare i pulsanti delle bevande
     * @param X_SCREEN_INDEX è l'indice x del tabellone
     * @param Y_SCREEN_INDEX è l'indice y del tabellone
     * @param screenSize è la dimensione dello schermo
     * @param display è il display del distributore
     * @param sugarDisplay è la riga del display
     * @param pannelloBevande è il pannello delle bevande
     */
    private void createDrinkButtons(int[] X_SCREEN_INDEX, int[] Y_SCREEN_INDEX, Dimension screenSize, JTextArea display,
                                    JTextField sugarDisplay, JPanel pannelloBevande) {
        int xButton = 0, yButton = 0; // coordinate dei pulsanti

        for (int i = 0; i < NUMERO_PULSANTI_BEVANDE; i++ ){
            if (xButton == 3){
                xButton = 0;
                yButton++;
            }
            JButton button;
            if (i < distributore.getListSize()) {
                index = i + 1;
                String id = String.valueOf(index);
                button = makeRoundRectButton(distributore.getLabel(index), X_SCREEN_INDEX[xButton],
                        Y_SCREEN_INDEX[yButton],screenSize.width / 6,
                        screenSize.height / 8);
                ResetDisplay resetDisplay = new ResetDisplay(display, sugarDisplay, distributore);
                button.addActionListener(new BeverageListener(distributore,display, index, resetDisplay));
            }
            else {
                // Pulsante vuoto
                button = makeRoundRectButton("", X_SCREEN_INDEX[xButton], Y_SCREEN_INDEX[yButton],
                        screenSize.width / 6, screenSize.height / 8);
            }
            pannelloBevande.add(button);
            xButton++;
        }


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
     * @param x: coordinata x nello schermo
     * @param y: coordinata y nello schermo
     * @param screenW: lunghezza pulsante
     * @param screenH: altezza pulsante
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
     * @param string: etichetta del pulsante
     * @param x: coordinata x nello schermo
     * @param y: coordinata y nello schermo
     * @param screenW: lunghezza pulsante
     * @param screenH: altezza pulsante
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
