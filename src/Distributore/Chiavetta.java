package Distributore;

import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Double.parseDouble;

public class Chiavetta {
    private String ID;
    private double saldo = 0;
    private int linea;
    private Data data = new Data("src/File_Testo/dati_chiavetta.txt");
    private String currentLine;
    private boolean connected = false;

    public Chiavetta() {
        ArrayList<String[]> chiavText = data.readFile();
        ID = chiavText.get(0)[0];
        saldo = parseDouble(chiavText.get(0)[1]);
        linea = 0;
        currentLine = chiavText.get(0)[0] + "\t" + chiavText.get(0)[1];
        // initChiavetta();
    }

    private void initChiavetta() {
        ArrayList<String[]> chiavText = data.readFile();

        for(int i = 0; i < chiavText.size(); i++) {
            String id = chiavText.get(i)[0];
            if(ID.equals(id)) {
                saldo = parseDouble(chiavText.get(i)[1]);
                linea = i;
                currentLine = chiavText.get(i)[0] + "\t" + chiavText.get(i)[1];
            }
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected() {
        connected = !connected;
    }

    public double getSaldo() {
        return saldo;
    }

    public void addSaldo(double importo) {
        saldo = (saldo * 1000 + importo * 1000) / 1000;
        saldo = Math.floor(saldo * 100) / 100;
        String newLine = ID + "\t" + saldo;
        newLine.replace(",","."); // altrimenti al successivo riavvio non si riesce a leggere il file
        try {
            data.overwriteFile(newLine, currentLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean pay(double costo) {
        if(saldo > costo) {
            saldo -= costo;
            saldo = Math.floor(saldo * 100) / 100;
            String newLine = ID + "\t" + saldo;
            try {
                data.overwriteFile(newLine, currentLine);
            } catch (IOException e) {
            }
            setConnected(); // si presuppone che dopo l'erogazione si estragga la chivetta
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nsaldo: " + saldo;
    }
}
