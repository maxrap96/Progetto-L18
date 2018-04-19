package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ListenerLoad implements ActionListener {
    File fileListener;
    JTextArea textAreaListener;

    public ListenerLoad(File file, JTextArea jTextArea) {
        this.fileListener = file;
        this.textAreaListener = jTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(fileListener.getPath()));

            textAreaListener.read(bufferedReader, null);
            bufferedReader.close();
            textAreaListener.repaint();
        } catch (IOException exc){
            exc.printStackTrace();
        }

    }
}
