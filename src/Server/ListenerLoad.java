package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ListenerLoad implements ActionListener {
    File fileListener;
    JTextField[] textFieldListener;

    public ListenerLoad(File file, JTextField[] jTextField) {
        this.fileListener = file;
        this.textFieldListener = jTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(fileListener.getPath()));

            bufferedReader.close();
        } catch (IOException exc){
            exc.printStackTrace();
        }

    }

    /**
     * Funzione che aggiunge un certo numero di textArea.
     *
     * @param panel pannello a cui aggiungo le textArea.
     * @param size quante textArea aggiungere.
     */
    private void addTextAreas(JPanel panel, int size){
        for(int i = 0; i < size; i++){
            JTextArea textArea = new JTextArea("A");
            textArea.setFont(new Font("", Font.ITALIC, 20));
            textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            panel.add(textArea);
        }
    }
}
