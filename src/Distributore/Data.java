package Distributore;

import PersonalExceptions.FileNotReadable;
import PersonalExceptions.FileNotWritable;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Data {
    private String pathFile;

    protected Data(String pathFile) {
        this.pathFile = pathFile;
    }

    /**
     * Funzione che legge da un file esterno i dati su cui bisogna lavorare.
     *
     * @return openedFile: è il file aperto e letto.
     * @throws FileNotReadable
     */
    protected ArrayList<String[]> readFile() throws FileNotReadable {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(pathFile));
            ArrayList<String[]> openedFile = split(bReader);
            bReader.close();

            return openedFile;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileNotReadable fileNotReadable) {
            throw new FileNotReadable();
        }

        return null;
    }

    /**
     * Funzione che riceve i dati copiati dal file e li divide dopo ogni carattere di "Tab".
     *
     * @param bReader è il reader associato al file.
     * @return dataSplit è il file aperto e separato in base alle tabulazioni.
     * @throws FileNotReadable è l'eccezione lanciata nel caso il file non sia presente o leggibile.
     */
    private ArrayList<String[]> split(BufferedReader bReader) throws FileNotReadable {
        ArrayList<String[]> dataSplit = new ArrayList<>();
        String row;

        try{
            while ((row = bReader.readLine()) != null) {
                if(! row.contains("*")) {
                    String[] rowDataSplit = row.split("\t");
                    dataSplit.add(rowDataSplit);
                }
            }
            return dataSplit;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funzione per la scrittura su file dei dati di interesse.
     *
     * @param scrittura è la stringa da accodare al file contenente le informazioni necessarie.
     * @throws FileNotWritable è l'eccezione lanciata nel caso non sia possibile scrivere sul file indicato da pathfile.
     */
    protected void writeFile(String scrittura) throws FileNotWritable {
        try {
            FileWriter writer = new FileWriter(pathFile, true);
            writer.write(scrittura + "\t" + getCurrentTimeStamp() + "\n");
            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotWritable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per la scrittura su file dei dati di interesse.
     *
     * @param scrittura è la stringa da accodare al file contenente le informazioni necessarie.
     * @param transaction parametro che mi dice se la transazione avviene o fallisce.
     * @throws FileNotWritable è l'eccezione lanciata nel caso non sia possibile scrivere sul file indicato da pathfile.
     */
    protected void writeFile(String scrittura, boolean transaction) throws FileNotWritable {
        try {
            FileWriter writer = new FileWriter(pathFile, true);

            if(transaction) {
                writer.write(scrittura + "Transazione avvenuta il:\t" + getCurrentTimeStamp() + "\n");
            }

            if(! transaction) {
                writer.write(scrittura + "Transazione fallita.\t" + getCurrentTimeStamp() + "\n");
            }

            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotWritable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param scrittura
     */
    protected void writeData(String scrittura) {
        try {
            FileWriter writer = new FileWriter(pathFile, true);
            writer.write(scrittura);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per ottenere data e ora locali.
     *
     * @return strDate è la stringa con i dati necessari.
     */
    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); //stringa per data ora tempo
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    /**
     * Funzione per la sovrascrittura di una specifica riga di testo.
     *
     * @param currentLine è la riga da aggiornare.
     * @param newLine è la nuova riga che sovrascriverà la riga selezionata.
     */
    protected void overwriteFile(String newLine, String currentLine) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(pathFile));
        String line;
        String input = "";

        while ((line = file.readLine()) != null)
            input += line + System.lineSeparator();

        input = input.replace(currentLine, newLine);

        FileOutputStream os = new FileOutputStream(pathFile);
        os.write(input.getBytes());

        file.close();
        os.close();
    }
}