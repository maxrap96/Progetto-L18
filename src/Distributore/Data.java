package Distributore;

import Errori.FileNotExisting;
import Errori.FileNotReadable;
import Errori.FileNotWritable;

import java.io.*;
import java.util.ArrayList;

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

    public void writeFile(String scrittura) throws FileNotWritable {
        try {
            FileWriter writer = new FileWriter(pathFile, true);

            writer.write(scrittura + "\n");

            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotWritable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}