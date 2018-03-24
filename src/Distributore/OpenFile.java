package Distributore;
import java.io.*;
import java.util.ArrayList;

public class OpenFile {

    /**
     * Commento di Dario: "Siamo sicuri sia necessaria uníntera classe solo per leggere ad file?"
     */

    public OpenFile(){
    }

    public String tastiera()  {
        InputStreamReader keyboard = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(keyboard);
        try {
            String letta= bufferedReader.readLine();
            keyboard.close();
            bufferedReader.close();
            return letta;
        } catch (IOException e) {
            //TODO new nessunaDigitata();
        }
        return null;
    }

    public ArrayList<String[]> apriFile(String nome_file){
        BufferedReader lettore;
        try {
            FileReader file = new FileReader(nome_file);
            lettore = new BufferedReader(file);
            ArrayList<String[]> fileaperto=splitFile(lettore);
            lettore.close();
            file.close();
            return fileaperto;

        } catch (FileNotFoundException e) {
            //TODO new fileNullo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private ArrayList<String[]> splitFile(BufferedReader lettore) /*todo throws fileNullo*/ {
        ArrayList<String[]> fileaperto=new ArrayList<>();
        try{
            String letta;
            while ((letta = lettore.readLine())!=null){
                if (letta.startsWith("*")){
                    continue; //è una riga da skippare
                }

                String[] splittata=letta.split("\t");
                fileaperto.add(splittata);
            }
            return fileaperto;
        }catch (Exception e){
            //todo throw new fileNullo();
        }
        return null;
    }

}

