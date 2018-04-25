package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ListenerOverwrite implements ActionListener, FileServer {

    File fileListener;
    JTextField[] jTextFieldsVectL;
    int columns;

    public ListenerOverwrite(JTextField[] vectTextFields, int howManyCols) {
        this.fileListener = fileMenuServer;
        this.jTextFieldsVectL = vectTextFields;
        this.columns = howManyCols;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            emptyFile(fileListener);
            String tmp = createFileString(jTextFieldsVectL);
            writeFileReceived(tmp, fileListener);
        }catch (IOException e1){
            e1.printStackTrace();
        }
    }

    /**
     * Svuoto il file.
     *
     * @param file il file da svuotare.
     * @throws IOException
     */
    private static void emptyFile(File file) throws IOException{
        try {
            PrintWriter emptyFile =
                    new PrintWriter(file.getPath());
            emptyFile.write(""); // Svuoto il file
            emptyFile.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Scrivo il file ricevuto.
     *
     * @param stringToWrite stringa da scrivere su file.
     * @param file dove salvo ciò che arriva.
     * @throws IOException
     */
    private static void writeFileReceived(String stringToWrite, File file)throws IOException{
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(file.getPath(), true); // Scrivo il file
            fileOutputStream.write((stringToWrite).getBytes());
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Funzione che crea una stringa unica dai JTextField.
     *
     * @param jTextFields vettore di JTextField che contengono i dati da scrivere nelle stringa.
     * @return la stringa creata.
     */
    private String createFileString(JTextField[] jTextFields){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("* le righe con * vengono saltate nella lettura\n" +
                "* ID  TIPO  \tNOME  \t    COSTO  Q_MAX TEMP DOSE latte acqua\tvodka\n");
        int jTmp;
        for (int i = columns; i < jTextFields.length; i++){
            jTmp = i;
            if (!jTextFields[i].getText().isEmpty()) {
                if ((jTmp + 1) % columns == 0) { // i + 1 perchè il vettore parte da 0
                    stringBuilder.append(jTextFields[i].getText());
                    stringBuilder.append("\n");
                } else {
                    stringBuilder.append(jTextFields[i].getText());
                    stringBuilder.append("\t");
                }
            }
        }
        stringBuilder.append("*"); // Carattere finale al file come convenzione
        return stringBuilder.toString();
    }
}
