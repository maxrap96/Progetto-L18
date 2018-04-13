package Distributore;
import Errori.FileNotReadable;
import Errori.FileNotWritable;

import java.util.ArrayList;
import static java.lang.Double.parseDouble;

public class Chiavetta {
    private String ID;
    private double Saldo = 0;
    private int Linea;
    private Data data = new Data("src/File_Testo/dati_chiavetta.txt");

    public Chiavetta(String ID) {
        this.ID = ID;
        initChiavetta();
    }

    public void initChiavetta(){
        try{
            ArrayList<String[]> chiavText = data.readFile();

            for(int i = 0; i < chiavText.size(); i++){
                String id = chiavText.get(i)[0];
                 if(ID.equals(id)){
                     Saldo = parseDouble(chiavText.get(i)[1]);
                     Linea = i;
                 }
            }

        }catch (FileNotReadable fileNotReadable){
            fileNotReadable.printStackTrace();
        }
    }

    //TODO capire come riscrivere le sigole righe di un file.txt

    public void AggSaldo(double importo){
        Saldo += importo;
        String c = ID+"\t"+Saldo;

        try {

            data.writeFile(c);
        }catch (FileNotWritable fileNotWritable){
            fileNotWritable.printStackTrace();
        }
    }

    public boolean Pay(double Costo){
        if(Saldo > Costo){
            Saldo -= Costo;
            String c = ID+"\t"+Saldo;

            try {

                data.writeFile(c);
            }catch (FileNotWritable fileNotWritable){
                fileNotWritable.printStackTrace();
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
