package Distributore;
import Errori.FileNotReadable;
import Errori.FileNotWritable;

import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Double.parseDouble;

public class Chiavetta {
    private String ID;
    private double Saldo = 0;
    private int Linea;
    private Data data = new Data("src/File_Testo/dati_chiavetta.txt");
    private String currentLine;

    public Chiavetta(String ID) {
        this.ID = ID;
        initChiavetta();
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

    private void AddSaldo(double importo) {
        Saldo += importo;
        String newLine = ID + "\t" + Saldo;
        try {
            data.overwriteFile(newLine, currentLine);
        } catch (IOException e) {
        }
    }

    private boolean Pay(double Costo){
        if(Saldo > Costo){
            Saldo -= Costo;
            String newLine = ID+"\t"+Saldo;

            try {
                data.overwriteFile(newLine, currentLine);
            } catch (IOException e){
            }
            return true;
        }

        return false;
    }


    @Override
    public String toString() {
        return "ID: "+ID+"\nSaldo: "+Saldo;
    }
}