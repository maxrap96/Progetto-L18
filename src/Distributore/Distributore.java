package Distributore;

import Bevande.*;
import Errori.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Distributore implements MaxValue{

    private HashMap<String,Bevanda> list;
    private int cup, spoon, selected_sugar;
    private double sugar, milk;
    private Coins coins;
    private Data stats = new Data("src/File_Testo/stats.txt");
    private Data ingredientsData = new Data("src/File_Testo/dati.txt");
    private Data menu = new Data("src/File_Testo/menu.txt");
    private ArrayList<String[]> dati;

    public Distributore() {
        this.list = new HashMap<>();
        this.coins = new Coins();

        try {
            createList(menu.readFile());
            setValues(ingredientsData.readFile());
        } catch (FileNotReadable fileNotReadable) {
            fileNotReadable.printStackTrace();
        }
    }

    /**
     * Funzione che carica le quantità residue leggendole da file.
     */

    public void setValues(ArrayList<String[]> statistics) {
        this.milk = Double.parseDouble(statistics.get(0)[1]);
        this.sugar = Double.parseDouble(statistics.get(1)[1]);
        this.spoon = parseInt(statistics.get(2)[1]);
        this.cup = parseInt(statistics.get(3)[1]);

        dati = statistics;
        // Controllo se c'è bisogno di ricaricare la macchinetta.
        checkIfMachineIsEmpty();
    }

    /**
     * Invece che farli scendere a zero, non sarebbe meglio metterli sotto una certa soglia? Soprattutto dato che
     * il latto non viene scalato in maniera uniforme. Poi se funziona così va bene.
     */

    private void checkIfMachineIsEmpty() {
        if(cup == 0 || spoon == 0 || sugar == 0 || milk == 0) {
            resetToMaxVendingMachine();
        }
    }

    /**
     * Ricarica alcuni ingredienti della macchinetta
     */
    private void resetToMaxVendingMachine() {
        this.sugar = SUGARMAX;
        this.milk = MILKMAX;
        this.cup = CUPMAX;
        this.spoon = SPOONMAX;
    }

    /**
     * Funzione che crea il menu nella macchinetta
     * @param listFromFile arraylist di stringhe fornito all'apertura del file
     */

    private void createList(ArrayList<String[]> listFromFile) {

        for (int i = 0; i < listFromFile.size(); i++){
            Tipo tipo = Tipo.valueOf(listFromFile.get(i)[1]);
            Bevanda bevanda = null;
            switch (tipo.ordinal()) {
                case 0:
                    bevanda = new Macinato(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],bevanda);
                    break;
                case 1:
                    bevanda = new Capsula(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],bevanda);
                    break;
                case 2:
                    bevanda = new Solubile(listFromFile.get(i));
                    list.put(listFromFile.get(i)[0],bevanda);
                    break;
                default:
                    new InvalidType();
                    continue;
            }
        }
    }

    /**
     * Funzione per recepire i comandi testuali ed analizzarli.
     */

    public void textualInput (){
        showList();
        System.out.println("Inserire l'ID della bevanda e la quantità di zucchero richiesta (da 0 a 5)\n" +
                "separate da uno spazio.");
        String input = null;

        try {
            input = keyboard();
        } catch (NoDigit noDigit) {
            noDigit.printStackTrace();
        }

        String[] splitted = input.split("\\s+");
        selected_sugar=parseInt( splitted[1]);
        //mi chiedo se la bevanda è disponibile
        try {
            if (list.get(splitted[0]).isAvailable()) {
                double[] value = coins.getCOINS_VALUE();
                for (int i = 0; i < value.length; i++) {
                    try {
                        System.out.println("Inserire le monete da " + String.format("%.2f", value[i]) + " cent");
                        input = keyboard();
                        coins.addCredit(input, i);
                    } catch (NoDigit noDigit) {
                        noDigit.printStackTrace();
                    }
                }
                //vera e propria funzione da usare nell'interfaccia per l'erogazione della bevanda
                selectBeverage(splitted[0]);
            }
            else{
                new BeverageNotAvailable();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Funzione per recepire input da tastiera e restituirli sotto forma di stringa.
     */

    private String keyboard() throws NoDigit{
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Funzione per selezionare una bevanda. Essa controlla che il credito sia sufficiente.
     * @param ID: è l'id della bevanda selezionata
     */

    public String selectBeverage(String ID){

        if (coins.getCredit() >= list.get(ID).getPrice()
                && list.get(ID).isAvailable()){     // Se il credito è uguale o maggiore singifica che posso
                                                    // potenzialmente acquistare la bevanda
            subtractIngredients(ID,selected_sugar);
            coins.updateBalance(list.get(ID).getPrice());
            resetSugar();

            // Scrittura statistiche su file:

            try {

                //stats.writeFile(statsToText(ID));
                stats.writeFile(statsToText(ID), true);   // MJ: Variante.
                //ingredientsData.overwriteFile(capire cosa metterci dentro...);
                updateDati(ID);
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
            if (coins.getCredit()!= 0) {
                return "bevanda erogata " + coins.giveChange();
            }
            else {
                System.out.println("Bevanda erogata");
                return "Bevanda erogata";
            }
        }
        else {
            try {
                //stats.writeFile(statsToText(ID));  // Nel caso non ci sia credito sufficiente la transazione fallisce.
                stats.writeFile(statsToText(ID), false);    // Nel caso non ci sia credito sufficiente la
                                                                        // transazione fallisce.
            } catch (FileNotWritable fileNotWritable) {
                fileNotWritable.printStackTrace();
            }
            new UnsufficientCredit();
            return "Credito insufficiente o bevanda non disponibile";
        }
    }

    /**
     * Funzione per sottrarre quantità necessarie per preparare la bevanda
     * @param ID della bevanda da cui prendere le dosi
     * @param sugar
     */
    private void subtractIngredients(String ID, int sugar) {
        milk -= list.get(ID).getMilk();
        subtractSugar(sugar);
        cup--;
    }

    /**
     * Funzione che sottrae lo zucchero usato
     * @param qty valore tra 0 e 5
     */

    private void subtractSugar(int qty){
        if (qty != 0){
            sugar -= (double) qty * SUGARDOSE;
            spoon--;
        }
    }

    /**
     * Funziona che mostra la lista delle bevande contenute nel distributore.
     */
    private void showList() {
        for (int i = 1; i < list.size() + 1; i++){
            System.out.println(list.get("0" + i));
        }
    }

    /**
     * Funzione per tener traccia di ciò che accade nella macchinetta
     * @param ID è la bevanda selezionata dal cliente
     * @return s: restituisce una stringa con dei dati
     */

    public String statsToText(String ID) {
        // TODO MJ: Da parametrizzare se possibile.
        return (list.get(ID).getName() + "\t" + cup + "\t" + spoon + "\t" + sugar + "\t" + milk + "\t");
    }

    /**
     * Funziuone da usare nell'interfaccia per aggiungere i soldi
     * @param inserted è il valore associato al tasto di riferimento
     */

    public void addCredit(double inserted){
        double[] value = coins.getCOINS_VALUE();
        //cerco di capire la moneta inserita
        for (int i=0;i<value.length;i++){
            if (inserted == value[i]){
                //significa che ho trovato il valore corrispondente
                coins.addCoin(inserted,i);
            }
        }
    }

    /**
     * Cosa fa? Non come la si usa nel programma, quello va bene come commento al più
     * @param i
     * @return
     */
    public String getLabel(int i){
        return (list.get("0" + i).getName());//+ " Costo: " + list.get("0"+i).getPrice());
    }

    /**
     * // TODO spiegare solo cosa fa
     * @return numero di bevande presenti nel distributore
     */
    public int getListSize(){
        return list.size();
    }

    public double getCredit(){
        return coins.getCredit();
    }

    /**
     * // TODO spiegare solo cosa fa
     * @param id della bevanda
     * @return ritorna l'id della bevanda corrispondente
     */

    public String getID(int id){
        return list.get("0" + id).getId();
    }

    public ArrayList<String[]> getDati() {
        return dati;
    }

    /**
     * //TODO che fa?
     */
    private void resetSugar() {
        selected_sugar = 3;
    }

    /**
     * //TODO che fa?
     */
    public void upSugar(){
        if (selected_sugar < 5){
            selected_sugar++;
        }
    }

    /**
     * //TODO che fa?
     */
    public void downSugar(){
        if (selected_sugar > 0){
            selected_sugar--;
        }
    }

    /**
     * //TODO che fa? TU FAMMELA FINIRE E POI TI DICO GNE GNE
     */
    public void updateDati(String ID) {
        String valDati[] = {"" + milk, "" + sugar, "" + spoon, "" + cup};
        String newLine = "";
        String current = "";
        try {
            for (int i = 0; i < valDati.length; i++) {
                current = dati.get(i)[0] + "\t" + dati.get(i)[1];
                newLine = dati.get(i)[0] + "\t" + valDati[i];
                ingredientsData.overwriteFile(newLine, current);
            }

            current = ID+ "\t" +list.get(ID).getLeftQuantity();
            list.get(ID).subtractDose();
            newLine = ID+ "\t" +list.get(ID).getLeftQuantity();
            ingredientsData.overwriteFile(newLine, current);

        } catch (IOException e) {}
    }
}