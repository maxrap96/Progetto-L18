package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ListenerLoad implements ActionListener, FileServer {
    File fileListener;
    JTextField[] textFieldListener;
    JPanel panelListener;

    public ListenerLoad(JTextField[] jTextFields, JPanel panelToAddInfo) {
        this.fileListener = fileMenuServer;
        this.textFieldListener = jTextFields;
        this.panelListener = panelToAddInfo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setTextField();
        addTextField();
        panelListener.validate();
        panelListener.repaint();
    }

    /**
     * Funzione che aggiunge i textField.
     */
    private void addTextField(){
        for (int i = 0; i < textFieldListener.length; i++){
            panelListener.add(textFieldListener[i]);
        }
    }

    /**
     * Funzione che scrive il testo nei textField.
     */
    private void setTextField(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileListener.getPath()));
            String tmp;
            int index = 0;
            while((tmp = bufferedReader.readLine()) != null){
                if(tmp.contains("ID")){

                } else if (!tmp.contains("*")){
                    for (String pieceOfString : tmp.split("\t")){
                        textFieldListener[index].setText(pieceOfString);
                        index++;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
