package Distributore;


import Errori.FileNotExisting;
import Errori.FileNotReadable;
import Errori.NoDigit;

import java.io.*;
import java.util.ArrayList;

public class OpenFile {

    /**
     * Vecchia classe di Hexrebuilt che ha dei metodi già fatti per leggere da tastiera, aprire e splittare un file in
     * base ad un carattere. in questo caso usiamo la tabulazione
     */

    public OpenFile(){
    }

    public String keyboard()  {
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

    private ArrayList<String[]> splitFile(BufferedReader lettore) throws FileNotReadable {
        ArrayList<String[]> readed=new ArrayList<>();
        try{
            String letta;
            while ((letta = lettore.readLine())!=null){
                //è una riga da skippare se inizia con *
                if (!letta.contains("*")){
                    String[] splittata=letta.split("\t");
                    readed.add(splittata);
                }
            }
            return readed;
        }catch (Exception e){
            throw new FileNotReadable();
        }
    }

}

