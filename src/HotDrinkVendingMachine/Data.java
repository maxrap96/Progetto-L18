package HotDrinkVendingMachine;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Data {
    private final String PATH_FILE;

    public Data(String pathFile) {
        this.PATH_FILE = pathFile;
    }

    /**
     * Funzione che legge da un file esterno i dati su cui bisogna lavorare.
     * @return openedFile file aperto e letto.
     */
    public ArrayList<String[]> readFile() {
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(PATH_FILE));
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
     * @param bReader reader associato al file.
     */
    private ArrayList<String[]> split(BufferedReader bReader) {
        ArrayList<String[]> dataSplit = new ArrayList<>();
        String row;

        try {
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
     * @param writing stringa da accodare al file contenente le informazioni necessarie.
     */
    protected void writeFile(String writing) {
        try {
            FileWriter writer = new FileWriter(PATH_FILE, true);
            writer.write("\n" + writing + "\t" + getCurrentTimeStamp());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per la scrittura su file dei dati di interesse.
     * @param writing stringa da accodare al file contenente le informazioni necessarie.
     * @param transaction parametro che dice se la transazione avviene o fallisce.
     */
    protected void writeFile(String writing, boolean transaction) {
        try {
            FileWriter writer = new FileWriter(PATH_FILE, true);

            if (transaction) {
                writer.write(writing + "\tTransazione avvenuta il:\t" + getCurrentTimeStamp() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(String writing) {
        try {
            FileWriter writer = new FileWriter(PATH_FILE, true);
            writer.write(writing);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funzione per ottenere data e ora locali.
     * @return strDate dati desiderati.
     */
    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // Stringa per data, ora, tempo
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    /**
     * Funzione per la sovrascrittura di una specifica riga di testo.
     * @param currentLine riga da aggiornare.
     * @param newLine nuova riga che sovrascriverà la riga selezionata.
     */
    public void overwriteFile(String newLine, String currentLine) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(PATH_FILE));
        String line;
        String input = "";

        while ((line = file.readLine()) != null)
            input += line + System.lineSeparator();

        input = input.replace(currentLine, newLine);

        FileOutputStream outputStream = new FileOutputStream(PATH_FILE);
        outputStream.write(input.getBytes());

        file.close();
        outputStream.close();
    }

    public void saveFileFromCommand(ArrayList<String> arrayList) {
        try {
            FileWriter writer = new FileWriter(PATH_FILE, false);
            writer.write(arrayList.get(0) + "\n");
            writer.close();
            writer = new FileWriter(PATH_FILE, true);
            // La prima riga è senza append per sovrascrivere tutto
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

    public ArrayList<String> readFileNotSplitted () {
        ArrayList<String> arrayListNotSplitted = new ArrayList<>();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(PATH_FILE));
            String tmp;
            while ((tmp = bReader.readLine()) != null) {
                if (!tmp.contains("*")) {
                    arrayListNotSplitted.add(tmp);
                }
            }
            bReader.close();
            return arrayListNotSplitted;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayListNotSplitted;
    }
}
