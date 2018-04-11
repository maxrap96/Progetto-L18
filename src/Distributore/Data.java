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

    public Data(String pathFile) {
        this.pathFile = pathFile;
    }

    public ArrayList<String[]> readFile() throws FileNotReadable {
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

    public ArrayList<String[]> split(BufferedReader bReader) throws FileNotReadable {
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
     * funzione per la scrittura su file per tenere traccia di ciò che accade nella macchinetta
     * @param scrittura è la stringa da accodare al file contenente le informazioni necessarie
     * @throws FileNotWritable è l'eccezione lanciata nel caso non sia possibile scrivere sul file indicato da pathfile
     * in tal caso, la classe chiamante questa funzione si occuperà di gestire l'eccezione
     */

    public void writeFile(String scrittura) throws FileNotWritable {
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

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); //stringa per data ora e tempo
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}