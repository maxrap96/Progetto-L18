package Errori;

public class InsufficientCredit extends Exception{
    public InsufficientCredit(){
        super("Insufficient credit. Please insert more money.");
    }
}
