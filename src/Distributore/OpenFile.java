package Distributore;


import Errori.FileNotExisting;
import Errori.FileNotReadable;
import Errori.NoDigit;

import java.io.*;
import java.util.ArrayList;

public class OpenFile {

    /**
     * Vecchia classe di Hexrebuilt che ha dei metodi già fatti per leggere da tastiera, aprire e dividere un file in
     * base ad un carattere. In questo caso usiamo la tabulazione
     */

    public OpenFile(){
    }

    /**
     * A cosa serve sta funzione?
     */

    public String keyboard() {
        InputStreamReader keyboard = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(keyboard);
        try {
            String letta= bufferedReader.readLine();
            keyboard.close();
            bufferedReader.close();
            return letta;
        } catch (IOException e) {
            new NoDigit();
        }
        return null;
    }

    /**
     * A cosa serve sta funzione?
     */

    public ArrayList<String[]> apriFile(String nome_file){
         try {
            FileReader file = new FileReader(nome_file);
            BufferedReader lettore = new BufferedReader(file);
            ArrayList<String[]> fileaperto=splitFile(lettore);
            lettore.close();
            file.close();
            return fileaperto;

        } catch (FileNotFoundException e) {
            new FileNotExisting();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileNotReadable fileNotReadable) {
             fileNotReadable.printStackTrace();
         }
        return null;

    }

    /**
     * A cosa serve sta funzione?
     */

    private ArrayList<String[]> splitFile(BufferedReader lettore) throws FileNotReadable {
        ArrayList<String[]> readed = new ArrayList<>();
        try{
            String letta;
            while ((letta = lettore.readLine())!=null){
                if (!letta.contains("*")){              // è una riga da saltare se inizia con *
                    String[] splittata = letta.split("\t");
                    readed.add(splittata);
                }
            }
            return readed;
        }catch (Exception e){
            throw new FileNotReadable();
        }
    }

}

