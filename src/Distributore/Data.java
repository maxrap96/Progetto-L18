package Distributore;

import Errori.FileNotExisting;
import Errori.FileNotReadable;
import Errori.FileNotWritable;

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
     * Funzione che apre e legge i dati iniziali da un file esterno passato come parametro.
     * Il file viene chiuso, ma i dati vengono salvati per poi essere modificati da altre funzioni che
     * aggiornano i dati dopo ogni transazione della macchinetta
     * @return openedFile
     * @throws FileNotReadable
     */

    protected ArrayList<String[]> readFile() throws FileNotReadable {
        try {
            BufferedReader Breader = new BufferedReader(new FileReader(pathFile));
            ArrayList<String[]> openedFile = split(Breader);
            Breader.close();

            return openedFile;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileNotReadable fileNotReadable) {
            throw new FileNotReadable();
        }

        return null;
    }

    /**
     * Funzione che riceve i dati copiati dal file e li divide. Dopo ogni carattere di "Tab" viene effettuata la
     * divisione.
     * @param bReader
     * @return dataSplit
     * @throws FileNotReadable
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
     * Funzione per la scrittura su file. Tengo traccia di ciò che accade nella macchinetta
     * @param scrittura è la stringa da accodare al file contenente le informazioni necessarie
     * @throws FileNotWritable è l'eccezione lanciata nel caso non sia possibile scrivere sul file indicato da pathfile
     *                         in tal caso, la classe chiamante questa funzione si occuperà di gestire l'eccezione
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
     * Funzione per ottenere la data e ora locali. Questi dati vengono usati nei file di statistiche e nei file di
     * aggiornamento dei dati della macchinetta.
     * @return strDate
     */

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); //stringa per data ora tempo
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}