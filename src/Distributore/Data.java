package Distributore;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Data {
    private  final String PATHFILE;

    public Data(String pathFile) {
        this.PATHFILE = pathFile;
    }

    /**
     * Funzione che legge da un file esterno i dati su cui bisogna lavorare.
     * @return openedFile: è il file aperto e letto.
     */
    public ArrayList<String[]> readFile() {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(PATHFILE));
            ArrayList<String[]> openedFile = split(bReader);
            bReader.close();

            return openedFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Funzione che riceve i dati copiati dal file e li divide dopo ogni carattere di "Tab".
     * @param bReader è il reader associato al file.
     */
    private ArrayList<String[]> split(BufferedReader bReader) {
        ArrayList<String[]> dataSplit = new ArrayList<>();
        String row;

        try{
            while ((row = bReader.readLine()) != null) {
                if (!row.contains("*") && !row.isEmpty()) {
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

    /**
     * Funzione per la scrittura su file dei dati di interesse.
     * @param scrittura è la stringa da accodare al file contenente le informazioni necessarie.
     */
    protected void writeFile(String scrittura) {
        try {
            FileWriter writer = new FileWriter(PATHFILE, true);
            writer.write("\n" +scrittura + "\t" + getCurrentTimeStamp());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per la scrittura su file dei dati di interesse.
     * @param scrittura è la stringa da accodare al file contenente le informazioni necessarie.
     * @param transaction parametro che mi dice se la transazione avviene o fallisce.
     */
    protected void writeFile(String scrittura, boolean transaction) {
        try {
            FileWriter writer = new FileWriter(PATHFILE, true);

            if (transaction) {
                writer.write(scrittura + "\tTransazione avvenuta il:\t" + getCurrentTimeStamp() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeData(String scrittura) {
        try {
            FileWriter writer = new FileWriter(PATHFILE, true);
            writer.write(scrittura);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per ottenere data e ora locali.
     * @return strDate è la stringa con i dati desiderati.
     */
    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // stringa per data, ora, tempo
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    /**
     * Funzione per la sovrascrittura di una specifica riga di testo.
     * @param currentLine è la riga da aggiornare.
     * @param newLine è la nuova riga che sovrascriverà la riga selezionata.
     */
    protected void overwriteFile(String newLine, String currentLine) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(PATHFILE));
        String line;
        String input = "";

        while ((line = file.readLine()) != null)
            input += line + System.lineSeparator();

        input = input.replace(currentLine, newLine);

        FileOutputStream os = new FileOutputStream(PATHFILE);
        os.write(input.getBytes());

        file.close();
        os.close();
    }

    public void saveFileFromCommand(ArrayList<String> arrayList) throws IOException {
        try {
            FileWriter writer = new FileWriter(PATHFILE, false);
            writer.write(arrayList.get(0) + "\n");
            writer.close();
            writer = new FileWriter(PATHFILE, true);
            //La prima riga è senza append per sovrascirvere tutto
            for (int i = 1; i < arrayList.size(); i++) {
                writer.write(arrayList.get(i));
                if (i < arrayList.size() - 1) {
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
