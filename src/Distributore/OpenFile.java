package Distributore;
import java.io.*;
import java.util.ArrayList;

public class OpenFile {

    /**
     * Commento di Dario: "Siamo sicuri sia necessaria uníntera classe solo per leggere ad file?"
     */
    private String nome_file;
    private BufferedReader lettore;
    private FileReader file;
    private ArrayList<String[]> fileaperto;

    public OpenFile(){
        this.nome_file = null;
        this.lettore = null;
        this.file = null;
        this.fileaperto = new ArrayList<>();

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
        try {
            file = new FileReader(nome_file);
        } catch (FileNotFoundException e) {
            //TODO new fileNullo();
        }
        lettore = new BufferedReader(file);
        splitFile();
        //todo try catch

        return fileaperto;

    }

    private void splitFile() /*todo throws fileNullo*/ {
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

