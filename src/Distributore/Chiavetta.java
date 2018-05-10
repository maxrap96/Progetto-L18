package Distributore;

import PersonalExceptions.FileNotReadable;
import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Double.parseDouble;

public class Chiavetta {
    private String ID;

    private double Saldo = 0;
    private int Linea;
    private Data data = new Data("src/File_Testo/dati_chiavetta.txt");
    private String currentLine;

    private boolean connected = false;

    public Chiavetta() {
        try {
            ArrayList<String[]> chiavText = data.readFile();
            ID = chiavText.get(0)[0];
            Saldo = parseDouble(chiavText.get(0)[1]);
            Linea = 0;
            currentLine = chiavText.get(0)[0]+"\t"+chiavText.get(0)[1];
        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }
        // initChiavetta();
    }

    private void initChiavetta(){
        try{
            ArrayList<String[]> chiavText = data.readFile();

            for(int i = 0; i < chiavText.size(); i++){
                String id = chiavText.get(i)[0];
                 if(ID.equals(id)){
                     Saldo = parseDouble(chiavText.get(i)[1]);
                     Linea = i;
                     currentLine = chiavText.get(i)[0]+"\t"+chiavText.get(i)[1];
                 }
            }

        }catch (FileNotReadable fileNotReadable){
            fileNotReadable.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected() {
        connected = !connected;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void AddSaldo(double importo) {
        Saldo = (Saldo *1000 + importo*1000)/1000;
        String newLine = ID + "\t" + String.format("%.2f", Saldo);
        newLine.replace(",","."); // altrimenti al successivo riavvio non riesco a leggere il file
        try {
            data.overwriteFile(newLine, currentLine);
        } catch (IOException e) {
        }
    }

    public boolean Pay(double Costo){
        if(Saldo > Costo){
            Saldo -= Costo;
            String newLine = ID+"\t"+String.format("%.2f",Saldo);
            try {
                data.overwriteFile(newLine, currentLine);
            } catch (IOException e){
            }
            setConnected(); // presuppongo che dopo l'erogazione si estragga la chivetta
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "ID: "+ID+"\nSaldo: "+Saldo;
    }
}