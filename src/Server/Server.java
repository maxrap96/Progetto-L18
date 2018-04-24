package Server;

import InterfacciaDistributore.WindowCloser;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Server extends JFrame implements FileServer{

    private int panelRows;
    private int panelCols;
    private JTextField[][] textFieldsVect;

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

        initJTextFieldVect();

        JPanel textPanel = new JPanel(new GridLayout(this.panelRows, this.panelCols));
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        addTextField(textPanel);

        JButton buttonMenu = makeButton("MENU");
        JButton buttonStats = makeButton("STATS");

        ButtonPanel buttonPanel = new ButtonPanel(buttonMenu, buttonStats);

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
     * Inizializzo righe e colonne del textPanel.
     */
    private void initRowsAndCols(){
        initiRows();
        initCols();
    }

    /**
     * Inizializzo le colonne del textPanel.
     */
    private void initCols(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileMenuServer.getPath()));
            String firstLine = null;
            String tmp;
            int cols = 0;
            while((tmp  = bufferedReader.readLine()) != null){
                if(tmp.contains("ID")){
                    firstLine = tmp.substring(1);
                }
            }
            StringTokenizer stringTokenizer = new StringTokenizer(firstLine);
            while (stringTokenizer.hasMoreTokens()){
                stringTokenizer.nextToken();
                cols++;
            }
            bufferedReader.close();
            this.panelCols = cols;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Inizializzo le righe del textPanel.
     */
    private void initiRows(){
        try{
            BufferedReader bufferedReader = new BufferedReader( new FileReader(fileMenuServer.getPath()));
            String tmp;
            int rows = 2; // Parto da 2, una riga per ID... e una vuota.
            while ((tmp = bufferedReader.readLine()) != null){
                if (!tmp.contains("*")){
                    rows++;
                }
            }
            bufferedReader.close();
            this.panelRows = rows;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Funzione che inizializza il vettore di textField.
     */
    private void initJTextFieldVect(){
        initRowsAndCols();
        textFieldsVect = new JTextField[panelRows][panelCols];
        for (int row = 0; row < panelRows; row++ ){
            for (int col = 0; col < panelCols; col++){
                textFieldsVect[row][col] = makeTextField();
            }
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
        textFieldTmp.setFont(new Font("", Font.BOLD, 16));
        textFieldTmp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return textFieldTmp;
    }

    private void addTextField(JPanel whereToAdd){
        for (int row = 0; row < panelRows; row++ ){
            for (int col = 0; col < panelCols; col++){
                whereToAdd.add(textFieldsVect[row][col]);
            }
        }
    }
}
