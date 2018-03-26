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

    /**
     * Funzione per recepire input da tastiera e restituirli sotto forma di stringa. Essa poi dovrà essere analizzata
     * adeguatamente dalla funzione che la va a richiamare.
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
     * Funzione per aprire un file e restituire un arraylist composto dalle singole righe del file di testo aperto. ogni
     * riga per praticità viene separata in base ad un carattere, in questo caso la tabulazione, così da rendere più
     * veloce la creazione della lista delle bevande nel distributore.
     *
     * @param nome_file è il nome_file, compreso di estensione .txt, riferito al percorso relativo rispetto alla route
     *                  del programma.
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
     * Funzione che presa un BufferedReader, lo legge riga per riga. Una volta letta una row, essa viene separata in
     * base ad un carattere specifico, in questo caso la tabulazione, che identifica i singoli campi nel file di testo
     * @param lettore è il BufferedReader associato al file di testo da aprire per ottenere le informazioni necessarie.
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

