package Distributore;

import Errori.FileNotExisting;
import Errori.FileNotReadable;

import java.io.*;
import java.util.ArrayList;

public class Data {
    public ArrayList<String[]> readFile() {
        try {
            BufferedReader Breader = new BufferedReader(new FileReader("dati.txt"));
            ArrayList<String[]> openedFile = split(Breader);
            Breader.close();

            return openedFile;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
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


}