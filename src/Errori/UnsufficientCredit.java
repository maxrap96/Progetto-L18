package Errori;

public class UnsufficientCredit extends Exception{
    public UnsufficientCredit(){
        super("Unsofficient credit. Please insert the right ammount");
    }
}
