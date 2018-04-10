import Errori.FileNotExisting;
import Errori.FileNotReadable;

import java.io.*;
import java.util.ArrayList;

public class OpenFile {

    private String nomeFile;

    public OpenFile(String nomeFile) {
    this.nomeFile = nomeFile;
    }

    /**
     * Apre un file e restituisce un ArrayList<String[]> contenente i dati del menu.
     */

    public ArrayList<String[]> apriFile(){
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeFile));
            ArrayList<String[]> fileAperto = splitFile(bufferedReader);
            bufferedReader.close();

            return fileAperto;

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
     * Separa ogni riga in singole stringhe ogni volta che incontra un carattere di tabulazione.
     * Salva e restituisce i frammenti come ArrayList<String[]>.
     * @param bufferedReader è il BufferedReader associato al file di testo.
     */

    private ArrayList<String[]> splitFile(BufferedReader bufferedReader) throws FileNotReadable {
        ArrayList<String[]> menuSplit = new ArrayList<>();
        String rigaMenu;

        try{
            while ((rigaMenu = bufferedReader.readLine())!= null){
                if (!rigaMenu.contains("*")){              // è una riga da saltare se inizia con *
                    String[] rigaMenuSplit = rigaMenu.split("\t");
                    menuSplit.add(rigaMenuSplit);
                }else {
                    // Do Nothing ?
                }
            }
            return menuSplit;
        }catch (Exception e){
            throw new FileNotReadable();
        }
    }

}

