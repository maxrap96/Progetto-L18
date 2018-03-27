package Errori;

public class BeverageNotAvaible extends Exception{
    public BeverageNotAvaible(){
        super("Product not avaible. Please choose another one.");
    }
}
