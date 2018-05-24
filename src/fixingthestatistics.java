import Distributore.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class fixingthestatistics {
    public static void main(String[] args) {
        Data stats = new Data("src/File_Testo/stats.txt");

        ArrayList<String[]> letto = stats.readFile();

        //Tè al limone	Transazione avvenuta il:	14-04-2018 00:08:23
        //0             1   2

        int previuslenght;
        int currenctlenght;

        do {
            previuslenght = letto.size();
            for (int i = 0; i < letto.size(); i++) {
                if (letto.get(i)[1].equals("Transazione fallita.")) {
                    letto.remove(i);
                }
                System.out.println(i);
            }
            currenctlenght = letto.size();
        } while (currenctlenght != previuslenght);

        ArrayList<String> newstat = new ArrayList<>();

        for (int i=0; i<letto.size(); i++){
            newstat.add((letto.get(i)[0] +"\t"+ letto.get(i)[1] +"\t"+ letto.get(i)[2]) + "\n");
        }

        refactorStats(newstat, "src/File_Testo/stats.txt");
    }

    public static void refactorStats(ArrayList<String> newstat, String pathfile) {
        try {
            FileWriter writer = new FileWriter(pathfile, false);
            writer.write(newstat.get(0));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(pathfile, false);
            for (int i=1; i< newstat.size(); i++){
                writer.write(newstat.get(i));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
