package Server;

import GUI_Swing.WindowCloser;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Server extends JFrame implements FileServer{

    private int panelRows;
    private int panelCols;
    private JTextField[] jTextFieldsVect;

    /**
     * Creazione interfaccia grafica Server.
     */
    public Server() {
        // Inizializzazione JFrame
        initJFrame("Remote Management System");
        Container container = this.getContentPane();
        container.setBackground(Color.BLACK);
        container.setLayout(new GridLayout(1,2));
        WindowCloser windowCloserListener = new WindowCloser();
        addWindowListener(windowCloserListener);

        // Inizializzo Righe e Colonne
        this.panelRows = initRows();
        this.panelCols = initCols();

        // Inizializzo il vettore dei textField
        jTextFieldsVect = new JTextField[panelRows * panelCols];
        initJTextFieldVect(jTextFieldsVect);

        // Creo il pannello in cui mostrare le informazioni
        JPanel textPanel = new JPanel(new GridLayout(this.panelRows, this.panelCols));
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Creo i bottoni
        JButton buttonMenu = makeButton("MENU");
        JButton buttonStats = makeButton("STATS");

        // Creo il pannello dei bottoni
        ButtonPanel buttonPanel = new ButtonPanel(buttonMenu, buttonStats);

        // Aggiungo i listener
        addListenerTextField(jTextFieldsVect, panelCols);
        buttonMenu.addActionListener(new ListenerLoad(jTextFieldsVect, textPanel));
        buttonStats.addActionListener(new TabsListener(textPanel));

        container.add(buttonPanel);
        container.add(textPanel);
    }

    /**
     * Funzione che inizializza il JFrame secondo alcuni standard.
     *
     * @param title nome della finestra.
     */
    private void initJFrame(String title){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        pack();
        setSize(screenSize.width / 2,screenSize.height / 2); // Quando riduco la finestra prende queste
                                                                           // dimensioni
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Quando avvio si apre fullscreen
        setTitle(title);
    }

    /**
     * Inizializzo le colonne del textPanel.
     */
    private int initCols(){
        String tmp = "ID\tTIPO\tNOME\tCOSTO\tQ_MAX\tTEMP\tDOSE\tlatte\tacqua\tvodka";
        int cols = 0;
        // Calcolo di quanti elemente è composta
        StringTokenizer stringTokenizer = new StringTokenizer(tmp, "\t");
        while (stringTokenizer.hasMoreTokens()){
            stringTokenizer.nextToken();
            cols++;
        }
        return cols;
    }

    /**
     * Inizializzo le righe del textPanel.
     */
    private int initRows(){
        try{
            BufferedReader bufferedReader = new BufferedReader( new FileReader(fileMenuServer.getPath()));
            String tmp;
            int rows = 2; // Parto da 2, una riga per ID... e una vuota nel caso di aggiunte future.
            while ((tmp = bufferedReader.readLine()) != null){
                if (!tmp.contains("*")){
                    rows++;
                }
            }
            bufferedReader.close();
            return rows;
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Funzione che inizializza il vettore di textField.
     */
    private void initJTextFieldVect(JTextField[] jTextFields){
        for (int i = 0; i < jTextFields.length; i++ ){
            jTextFields[i] = makeTextField();
        }
    }

    /**
     * Funzione che crea un bottone.
     *
     * @param nameButton nome da assegnare al bottone.
     * @return il bottone creato.
     */
    private JButton makeButton(String nameButton){
        JButton buttonTmp = new JButton(nameButton);
        buttonTmp.setFont(new Font("", Font.ITALIC, 100));
        return buttonTmp;
    }

    /**
     * Funzione che crea i textField.
     *
     * @return la textField creata.
     */
    private JTextField makeTextField(){
        JTextField textFieldTmp = new JTextField("");
        textFieldTmp.setFont(new Font("", Font.BOLD, 13));
        textFieldTmp.setHorizontalAlignment(JTextField.CENTER);
        textFieldTmp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return textFieldTmp;
    }

    /**
     *  Aggiungo ad ogni textField l'actionListener.
     *
     * @param jTextFields vettore dei textField.
     * @param cols intero da passare al ListenerOverwrite.
     */
    private void addListenerTextField(JTextField[] jTextFields, int cols){
        for (int i = 0; i < jTextFields.length; i++){
            jTextFields[i].addActionListener(new ListenerOverwrite(jTextFields, cols));
        }
    }
}
