package Server;

import javax.swing.*;
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
}
