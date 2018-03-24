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
        ArrayList<String[]> fileaperto=new ArrayList<>();
        FileReader file=null;

        BufferedReader lettore;
        try {
            file = new FileReader(nome_file);
        } catch (FileNotFoundException e) {
            //TODO new fileNullo();
        }
        lettore = new BufferedReader(file);

        try{
            splitFile(lettore,fileaperto);
        }
        catch (Exception e){
            //todo try catch
        }
        try {
            lettore.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileaperto;

    }

    private void splitFile(BufferedReader lettore, ArrayList<String[]> fileaperto) /*todo throws fileNullo*/ {
        try{
            String letta;
            while ((letta = lettore.readLine())!=null){
                if (letta.startsWith("*")){
                    continue; //è una riga da skippare
                }

                String[] splittata=letta.split("\t");
                fileaperto.add(splittata);
            }

        }catch (Exception e){
            //todo throw new fileNullo();
        }
    }

}

